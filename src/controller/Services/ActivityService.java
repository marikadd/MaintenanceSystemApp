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
import controller.Dao.UsersDao;
import controller.Utility.UtilityUser;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Activity.ActivityAdapter;
import model.Activity.ActivityTarget;
import model.Users.Maintainer;
import model.Users.Role;
import model.Activity.MaintenanceActivity;
import model.Competences.Competence;
import model.Users.UserModel;

/**
 *
 * @author Group9
 */

public class ActivityService {

    private static ActivityService activService;
    private ActivityDao activityDao;
    private UsersDao usersDao;

    //Singleton
    private ActivityService() {
    }
    
    public static ActivityService getActivityService() {
        if (activService == null) {
            activService = new ActivityService();
            activService.activityDao = ActivityDao.init();
            activService.usersDao = UsersDao.init();
        }
        return activService;
    }

    public int insertActivity(String type, String description, Integer time, ArrayList<Competence> skill)
            throws InvalidPermissionException, SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        MaintenanceActivity activity = new MaintenanceActivity();
        activity.setType(type);
        activity.setDescription(description);
        activity.setTime(time);
        activity.setAssigned(false);
        int activityId = activityDao.insertActivity(type, description, time);
        return activityDao.insertCompentecesInActivity(activityId, skill);
    }

    public void assignActivity(String usernameMain, List<Integer> listId) 
            throws SQLException, UsernotFoundException, UnsuccessfulUpdateException, InvalidParameterObjectException {
        
        UtilityUser<Maintainer> utilityUser = new UtilityUser<Maintainer>();
        Maintainer maintainer = new Maintainer(); 
        UserModel um = usersDao.findUserByUsername(usernameMain, Role.MAINTAINER);
        utilityUser.setUserModel(um, maintainer);
        
        activityDao.assignActivityToMaintainer(maintainer, listId);
        for(Integer id: listId) {
            activityDao.assignmentActivity(id);
        }
    }
    
    public void updateActivity(Integer id, String type, String description, int timeActivity) 
            throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {
        
        activityDao.updateActivity(id, type, description, timeActivity);
    }

    public int deleteActivity(Integer activityId) throws SQLException, UnsuccessfulUpdateException {
        
        return activityDao.deleteActivity(activityId);
    }
    
    public List<MaintenanceActivity> getAllActivities() throws SQLException, ActivityNotFoundException{
        
        List<MaintenanceActivity> activityList = new LinkedList<>();
       
        activityList = activityDao.findAllActivities();
 
        return activityList;
    }
    
    public List<ActivityTarget> getAllActivityTarget(String username) 
            throws SQLException, UsernotFoundException {
        
        validateMaintainer(username);
        List<MaintenanceActivity> activitiesInMaintener = activityDao.findActivitiesInMaintainer(username);
        List<MaintenanceActivity> activitiesNotInMaintener = activityDao.findActivitiesNotInMaintainer(username);
        
        List<ActivityTarget> targets = new ArrayList<ActivityTarget>();
        targets.addAll(getListTargetMaintainer(activitiesInMaintener, true));
        targets.addAll(getListTargetMaintainer(activitiesNotInMaintener, false));
        
        return targets;
    }
     
    private List<ActivityTarget> getListTargetMaintainer(List<MaintenanceActivity> activities, boolean linked) {
        
        List<ActivityTarget> targets = new ArrayList<ActivityTarget>();
        
        for(MaintenanceActivity ma: activities) {
            ActivityTarget at = new ActivityAdapter(linked, ma.getID(), ma.getType(), ma.getDescription(), ma.getTime(), ma.getAssigned());
            targets.add(at);
        }
        
        return targets;
    }
    
    private void validateMaintainer(String username) throws SQLException, UsernotFoundException {
        
        UserModel um = usersDao.findUserByUsername(username, Role.MAINTAINER);
    }
    
}