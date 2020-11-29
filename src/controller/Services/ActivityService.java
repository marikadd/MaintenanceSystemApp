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

    private static ActivityService activService = new ActivityService();

    private ActivityDao activityDao;
    private UsersDao usersDao;

    public static ActivityService getActivityService() {

        if (activService == null) {
            activService = new ActivityService();
            activService.activityDao = ActivityDao.init();
            activService.usersDao = UsersDao.init();
        }
        return activService;
    }

    public List<MaintenanceActivity> findAllActivities() throws SQLException, ActivityNotFoundException {

        List<MaintenanceActivity> activityList = new LinkedList<>();
        activityList = activityDao.findAllActivities();

        return activityList;
    }

    public MaintenanceActivity findActivity(int ID) throws SQLException, ActivityNotFoundException {

        MaintenanceActivity activity = null;

        activity = activityDao.findActivityByID(ID);

        return activity;
    }

    public void insertActivity(String type, String description, int time, ArrayList<Competence> skill)
            throws InvalidPermissionException, SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        MaintenanceActivity activity = new MaintenanceActivity();
        activity.setType(type);
        activity.setDescription(description);
        activity.setTime(time);
        activity.setAssigned(false);
        int activityId = activityDao.insertActivity(activity);
        activityDao.insertCompentecesInActivity(activityId, skill);
        
    }

    public void assignActivity(String usernameMain, List<Integer> listId) throws SQLException, UsernotFoundException, UnsuccessfulUpdateException, InvalidParameterObjectException {

       
        UtilityUser<Maintainer> utilityUser = new UtilityUser<Maintainer>();
        Maintainer maintainer = new Maintainer(); 
        UserModel um = usersDao.findUserByUsername(usernameMain, Role.MAINTAINER);
        utilityUser.setUserModel(um, maintainer);
        
        activityDao.assignActivityToMaintainer(maintainer, listId);
        for(Integer id: listId) {
            activityDao.updateActivity(id);
        }
    }
    
    public void updateActivity(Integer id, String type, int timeActivity) throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {
        
        activityDao.updateActivity(id, timeActivity, type);
        
    }

    public void deleteActivity(Integer activityId) throws SQLException, UnsuccessfulUpdateException {
        
        activityDao.deleteActivity(activityId);
    
    }
    
}
