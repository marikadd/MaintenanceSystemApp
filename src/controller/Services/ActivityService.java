/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;

import configuration.Exceptions.ActivityNotFoundException;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.InvalidPermissionException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import configuration.Exceptions.UsernotFoundException;
import controller.Dao.ActivityDao;
import controller.Dao.DepartmentDao;
import controller.Dao.UsersDao;
import controller.Utility.UtilityUser;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.Activity.ActivityLinked;
import model.Users.Maintainer;
import model.Users.Role;
import model.Activity.MaintenanceActivity;
import model.Competences.Competence;
import model.Department.Department;
import model.Users.UserModel;
import model.Activity.ActivityInterface;

/**
 *
 * @author Group9
 */

public class ActivityService {

    private static ActivityService activService;
    private ActivityDao activityDao;
    private UsersDao usersDao;
    private DepartmentDao depDao;

    //Singleton
    private ActivityService() {
    }

    public static ActivityService getActivityService() {
        if (activService == null) {
            synchronized(ActivityService.class) {
                if(activService == null) {
                    activService = new ActivityService();
                    activService.activityDao = ActivityDao.init();
                    activService.usersDao = UsersDao.init();
                    activService.depDao = DepartmentDao.init();
                }
            }
        }
        return activService;
    }

    public int insertActivity(String type, String description, Integer time, ArrayList<Competence> skill, Integer week_num, Department dep)
            throws InvalidPermissionException, SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        MaintenanceActivity activity = new MaintenanceActivity();
        activity.setType(type);
        activity.setDescription(description);
        activity.setTime(time);
        activity.setAssigned(false);
        int activityId = activityDao.insertActivity(type, description, time, week_num, dep);
        return activityDao.insertCompentecesInActivity(activityId, skill);
    }

    public int assignActivity(String usernameMain, List<Integer> listId)
            throws SQLException, UsernotFoundException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        // Da eliminare, la lista è infallibile
        UtilityUser<Maintainer> utilityUser = new UtilityUser<Maintainer>();
        Maintainer maintainer = new Maintainer();
        UserModel um = usersDao.findUserByUsername(usernameMain, Role.MAINTAINER);
        utilityUser.setUserModel(um, maintainer);

        int result = activityDao.assignActivityToMaintainer(maintainer, listId);
        for (Integer id : listId) {
            activityDao.assignmentActivity(id);
        }

        return result;
    }

    public int updateActivity(Integer id, String type, String description, int timeActivity, Integer week_num, Department dep)
            throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        return activityDao.updateActivity(id, type, description, timeActivity, week_num, dep);
    }

    public int deleteActivity(Integer activityId) throws SQLException, UnsuccessfulUpdateException {

        return activityDao.deleteActivity(activityId);
    }

    public List<MaintenanceActivity> getAllActivities() throws SQLException, ActivityNotFoundException {

        List<MaintenanceActivity> activityList = new LinkedList<>();

        activityList = activityDao.findAllActivities();

        return activityList;
    }

    public List<ActivityInterface> getAllActivityTarget(String username)
            throws SQLException, UsernotFoundException {

        validateMaintainer(username);
        List<MaintenanceActivity> activitiesInMaintener = activityDao.findActivitiesInMaintainer(username);
        List<MaintenanceActivity> activitiesNotInMaintener = activityDao.findActivitiesNotInMaintainer(username);

        List<ActivityInterface> targets = new ArrayList<ActivityInterface>();
        targets.addAll(getListTargetMaintainer(activitiesInMaintener, true));
        targets.addAll(getListTargetMaintainer(activitiesNotInMaintener, false));

        return targets;
    }

    private List<ActivityInterface> getListTargetMaintainer(List<MaintenanceActivity> activities, boolean linked) {

        List<ActivityInterface> targets = new ArrayList<ActivityInterface>();

        for (MaintenanceActivity ma : activities) {
            ActivityInterface at = new ActivityLinked(linked, ma.getID(), ma.getType(), ma.getDescription(), ma.getTime(), ma.getAssigned(), ma.getWeekNum(), ma.getDepartment());
            targets.add(at);
        }

        return targets;
    }

    private void validateMaintainer(String username) throws SQLException, UsernotFoundException {

        UserModel um = usersDao.findUserByUsername(username, Role.MAINTAINER);
    }

}
