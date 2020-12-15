
package controller.Services;

import configuration.Exceptions.ActivityAlreadyAssignedException;
import configuration.Exceptions.ActivityNotFoundException;
import configuration.Exceptions.DayNotValidException;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.InvalidPermissionException;
import configuration.Exceptions.TimeExpiredException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import configuration.Exceptions.UsernotFoundException;
import controller.Dao.ActivityDao;
import controller.Dao.DepartmentDao;
import controller.Dao.NotificationDao;
import controller.Dao.UsersDao;
import controller.Utility.UtilityUser;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import model.Activity.ActivityLinked;
import model.Users.Maintainer;
import model.Users.Role;
import model.Activity.MaintenanceActivity;
import model.Competences.Competence;
import model.Department.Department;
import model.Users.UserModel;
import model.Activity.ActivityInterface;
import model.Activity.ActivityNumRecord;
import model.Activity.UsernameResultActivity;
import model.Material.Material;

/**
 *
 * @author Group 9
 * 
 * The public methods of this class encapsulate the methods of the DAO classes. 
 * For this reason the comments relating to the parameters and exceptions 
 * have not been written because they are already in the DAOs.
 * 
 */
public class ActivityService { 

    private static ActivityService activService;
    private ActivityDao activityDao;
    private UsersDao usersDao;
    private DepartmentDao depDao;
    private NotificationDao notDao;
    private final double maxInDay = 420;
 
    /**
     * Pattern Singleton.
     */
    private ActivityService() {
    }

    /**
     * Creates a singleton for the current class. In order to avoid conflicts
     * between threads, the method uses the synchronized construct.
     * @return an instance of the current class.
     */
    public static ActivityService getActivityService() {
        
        if (activService == null) {
            synchronized (ActivityService.class) {
                if (activService == null) {
                    activService = new ActivityService();
                    activService.activityDao = ActivityDao.init();
                    activService.usersDao = UsersDao.init();
                    activService.notDao = NotificationDao.init();
                    activService.depDao = DepartmentDao.init();
                }
            }
        }
        return activService;
    }

    /**
     * This method inserts a valid activity, making sure that at least one skill is associated with it.
     * @param type
     * @param description
     * @param time
     * @param skill
     * @param materials
     * @param week_num
     * @param dep
     * @return 1 if the activity has been inserted, otherwise 0.
     * @throws InvalidPermissionException
     * @throws SQLException
     * @throws UnsuccessfulUpdateException
     * @throws InvalidParameterObjectException
     */
    public int insertActivity(String type, String description, Integer time, ArrayList<Competence> skill, ArrayList<Material> materials, Integer week_num, Department dep)
            throws InvalidPermissionException, SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        if (skill == null || skill.isEmpty()) {
            throw new UnsuccessfulUpdateException("The activity must have at least one skill");
        }

        MaintenanceActivity activity = new MaintenanceActivity();
        activity.setType(type);
        activity.setDescription(description);
        activity.setTime(time);
        activity.setAssigned(false);

        ActivityNumRecord numRecord = new ActivityNumRecord();

        int activityId = activityDao.insertActivity(type, description, time, week_num, dep, numRecord);
        int competenceId = activityDao.insertCompentecesInActivity(activityId, skill);
        int resultMaterial = activityDao.insertMaterialsInActivity(activityId, materials);

        return numRecord.getNumRecord();

    }

    /**
     * This method assigns an acitivity to a maintainer if the activity in
     * question has not yet been assigned and if minutes needed to carry out the
     * activity do not exceed the minutes of availability that the maintainer
     * has on the requested day.
     * @param usernameMain
     * @param activityId
     * @param listIdDay
     * @param time
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing.
     * @throws SQLException
     * @throws UsernotFoundException
     * @throws UnsuccessfulUpdateException
     * @throws InvalidParameterObjectException
     * @throws TimeExpiredException
     * @throws ActivityAlreadyAssignedException
     * @throws DayNotValidException
     */
    public int assignActivity(String usernameMain, Integer activityId, List<Integer> listIdDay, double time)
            throws SQLException, UsernotFoundException, UnsuccessfulUpdateException, InvalidParameterObjectException, TimeExpiredException, ActivityAlreadyAssignedException, DayNotValidException {

        validateNumberDay(listIdDay);
        checkActivityAlreadyAssigned(activityId, usernameMain);
        checkTimeInDay(usernameMain, listIdDay.get(0), time);

        UtilityUser<Maintainer> utilityUser = new UtilityUser<>();
        Maintainer maintainer = new Maintainer();
        UserModel um = usersDao.findUserByUsername(usernameMain, Role.MAINTAINER);
        utilityUser.setUserModel(um, maintainer);

        List<Integer> activitiesId = new ArrayList<>();
        activitiesId.add(activityId);
        int result = activityDao.assignActivityToMaintainer(maintainer, activitiesId);

        activityDao.assignmentActivity(activityId);
        activityDao.setMaintainerActivityDays(usernameMain, activityId, listIdDay);

        return result;
    }

    /**
     * This method unassigns an activity to a maintainer.
     * @param activityId
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing.
     * @throws SQLException
     * @throws UnsuccessfulUpdateException
     * @throws InvalidParameterObjectException
     */
    public int unassignActivity(Integer activityId) throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        notifyActivityDelete(activityId);
        activityDao.deassignDayActivity(activityId);
        int result = activityDao.deassignActivity(activityId);

        return result;
    }

    /**
     * This method updates characteristics of an activity.
     * @param id
     * @param type
     * @param description
     * @param timeActivity
     * @param week_num
     * @param dep
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing.
     * @throws SQLException
     * @throws UnsuccessfulUpdateException
     * @throws InvalidParameterObjectException
     */
    public int updateActivity(Integer id, String type, String description, Integer timeActivity, Integer week_num, Department dep)
            throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        return activityDao.updateActivity(id, type, description, timeActivity, week_num, dep);
    }

    /**
     * This method deletes an activity.
     * @param activityId
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing.
     * @throws SQLException
     * @throws UnsuccessfulUpdateException
     */
    public int deleteActivity(Integer activityId) throws SQLException, UnsuccessfulUpdateException {

        notifyActivityDelete(activityId);
        return activityDao.deleteActivity(activityId);
    }

    /**
     * This method gets the activity corresponding to a specified id.
     * @param ID
     * @return the activity found.
     * @throws SQLException
     * @throws ActivityNotFoundException
     * @throws InvalidParameterObjectException
     */
    public MaintenanceActivity getActivity(Integer ID) throws SQLException, ActivityNotFoundException, InvalidParameterObjectException {

        return activityDao.findActivityByID(ID);
    }

    /**
     * This method creates a list of all activities.
     * @return a linkedlist of all activities.
     * @throws SQLException
     * @throws ActivityNotFoundException
     * @throws InvalidParameterObjectException
     */
    public List<MaintenanceActivity> getAllActivities() throws SQLException, ActivityNotFoundException, InvalidParameterObjectException {

        List<MaintenanceActivity> activityList = new LinkedList<>();

        activityList = activityDao.findAllActivities();

        return activityList;
    }

    /**
     * This method creates a list of all activities corresponding to a specific week.
     * @param week_num
     * @return a linkedlist of activities corresponding to a specific week.
     * @throws SQLException
     * @throws ActivityNotFoundException
     * @throws InvalidParameterObjectException
     */
    public List<MaintenanceActivity> getAllActivitiesInWeek(Integer week_num) throws SQLException, ActivityNotFoundException, InvalidParameterObjectException {

        List<MaintenanceActivity> activityList = new LinkedList<>();

        activityList = activityDao.findActivitiesByWeekNum(week_num);

        return activityList;
    }

    /**
     * This method calculates the availability of a specific maintainer on a specific day.
     * @param username
     * @param day
     * @return the percentage of availability of a specific maintainer on a specific day.
     * @throws SQLException
     * @throws UsernotFoundException
     * @throws DayNotValidException
     */
    public int getDailyAvailability(String username, int day) throws SQLException, UsernotFoundException, DayNotValidException {

        validateNumberDay(new ArrayList<Integer>(Arrays.asList(day)));

        validateMaintainer(username);
        double sumNumDay = activityDao.getSumActivityDay(username, day);

        if (sumNumDay == 0.0) {
            return 100;
        }

        BigDecimal ratioDay = new BigDecimal(((this.maxInDay - (sumNumDay)) / this.maxInDay) * 100);
        int percDay = ratioDay.intValue();

        if (percDay > 0) {
            return percDay;
        } else {
            return 0;
        }
    }

    /**
     * This method gets activities that have been assigned to maintainers.
     * @return a tree map, whose key is the id of the activity that has been
     * assigned to a maintainer while the value is the username of the
     * maintainer in question.
     * @throws SQLException
     */
    public TreeMap<Integer, String> getAssignedActivities() throws SQLException {

        TreeMap<Integer, String> activityMap = new TreeMap<>();
        activityMap = activityDao.findAssignedActivities();

        return activityMap;

    }

    /**
     * This method notifies the deletion of a specified activity.
     * @param activityId respresents the id of the activity to delete.
     * @throws SQLException if the columnLabel is not valid, or if a database
     * access error occurs, or if this method is called on a closed result set.
     */
    private void notifyActivityDelete(Integer activityId) throws SQLException {

        if (activityId == null) {
            return;
        }

        String username = activityDao.findMaintainerByActivityId(activityId);

        if (username != null) {
            notDao.insertMessageNotificationPlanner(String.
                    format("The user %s is available again", username));
        }

    }

    /**
     * This method checks if a maintainer's availability a specific day is
     * enough to carry out an activity on that day.
     * @param username represents the username of a maintainer.
     * @param day represents the specified day.
     * @param time represents the time needed to carry out the activity in question.
     * @throws SQLException if the columnLabel is not valid, or if a database
     * access error occurs, or if this method is called on a closed result set.
     * @throws TimeExpiredException if the activity time is greater than the
     * the maintainer's availability in a specific day.
     */
    private void checkTimeInDay(String username, int day, double time) throws SQLException, TimeExpiredException {

        double sumNumDay = activityDao.getSumActivityDay(username, day);

        if ((sumNumDay + time) > this.maxInDay) {    
            throw new TimeExpiredException(String.format("Cannot assign to %s %.0f minutes in day %d.\nShow others maintainer's availability in the selected day.", username, time, day));

        }
    }

    /**
     * This method checks if an activity has been assigned to a maintainer or not.
     * @param maintainerActivityId represents the id of an activity.
     * @param username represents the username of a maintainer.
     * @throws SQLException if the columnLabel is not valid, or if a database
     * access error occurs, or if this method is called on a closed result set.
     * @throws ActivityAlreadyAssignedException if the specified activity has
     * already been assigned.
     */
    private void checkActivityAlreadyAssigned(int maintainerActivityId, String username) throws SQLException, ActivityAlreadyAssignedException {

        UsernameResultActivity ura = activityDao.checkActivityAssigned(maintainerActivityId);

        if (ura.isResult()) {
            if (!username.equals(ura.getUsername())) {
                throw new ActivityAlreadyAssignedException("Activity selected already assigned to another maintainer");
            } else {
                throw new ActivityAlreadyAssignedException("Activity selected already assigned to this maintainer");
            }
        }

    } 

    /**
     * This method makes sure that the user you are looking for is actually a maintainer.
     * @param username represents the username of a maintainer.
     * @throws SQLException if the columnLabel is not valid, or if a database
     * access error occurs, or if this method is called on a closed result set.
     * @throws UsernotFoundException if the user with the specified role doesn't exist.
     */
    private void validateMaintainer(String username) throws SQLException, UsernotFoundException {

        UserModel um = usersDao.findUserByUsername(username, Role.MAINTAINER);
    }

    /**
     * This method makes sure that the day you choose to assign the activity is between 1 and 7.
     * @param listDay is a list of integers representing the number of days of the week.
     * @throws DayNotValidException if the chosen day exceeds the range.
     */
    private void validateNumberDay(List<Integer> listDay) throws DayNotValidException {

        for (Integer day : listDay) {
            if (day < 1 || day > 7) {
                throw new DayNotValidException(String.format("Day %d is invalid", day));
            }
        }
    }

}
