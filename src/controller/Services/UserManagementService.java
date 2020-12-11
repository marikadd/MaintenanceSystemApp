/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;

import java.sql.SQLException;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import configuration.Exceptions.UsernotFoundException;
import controller.Dao.*;
import controller.Utility.UtilityUser;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.Activity.MaintenanceActivity;
import model.Users.*;

/**
 *
 * @author Group9
 */

public class UserManagementService {

    private static UserManagementService ums;
    private UsersDao usersDao = UsersDao.init();
    private ActivityDao activityDao = ActivityDao.init();
    private NotificationDao notificationDao = NotificationDao.init();

    //Singleton
    private UserManagementService() {
    }
    
    public static UserManagementService getUserManagementService() {
        if (ums == null) {
            synchronized(UserManagementService.class) {
                if(ums == null) ums = new UserManagementService();
            }
        }
        return ums;
    }
     
    public String getRoleByUsername(String username) throws SQLException, UsernotFoundException{
        String role= usersDao.findRoleByUsername(username);
        return role;
    }
    
    public List<UserModel> getAllUsers() throws SQLException, UsernotFoundException{
        
        List<UserModel> userList = new LinkedList<>();
        userList = usersDao.getAllUsers();
        
        return userList;
    }
    
    public List<UserModel> getAllMaintainers() throws SQLException, UsernotFoundException{
        
        List<UserModel> userList = new LinkedList<>();
        userList = usersDao.findUsersByRole(Role.MAINTAINER);
        return userList;
    }

    public int deleteUser(String username) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {
        
        unassignedActivityAndNotifyPlanner(username);
        return usersDao.deleteUserModel(username);
    }
    
    private void unassignedActivityAndNotifyPlanner(String username) throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {
        
        List<MaintenanceActivity> activities = activityDao.findActivityByMaintainer(username);
        
        if(activities.size() > 0) {
            
            List<String> notifications = new ArrayList<>();
            
            for(MaintenanceActivity activity: activities) {
                notifications.add(String.format("Activity %s has been not assigned to %s yet", activity.getDescription(), username));
                activityDao.deassignActivity(activity.getID());
            }
            
            for(String message: notifications) {
                
                notificationDao.insertMessageNotificationPlanner(message);
                
            }
            
        }
        
    }
    
    public ProdManager findProdManagerByUsername(String username) throws SQLException, UsernotFoundException {

        UtilityUser<ProdManager> utility = new UtilityUser<ProdManager>();
        UserModel um = usersDao.findUserByUsername(username, Role.PROD_MANAGER);
        ProdManager pm = new ProdManager();
        utility.setUserModel(um, pm);
        return pm;
    }

    public int insertProdManager(String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel prodManager = new ProdManager(username, password, name, surname, email, phone);
        return usersDao.insertUserModel(prodManager, Role.PROD_MANAGER);

    }

    public int updateProdManager(String oldUsername, String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = new ProdManager(username, password, name, surname, email, phone);
        return usersDao.updateUserModel(oldUsername, userModel);

    }
    
    public SystemAdmin findSystemAdminByUsername(String username) throws SQLException, UsernotFoundException {
        
        UtilityUser<SystemAdmin> utility = new UtilityUser<SystemAdmin>();
        UserModel um = usersDao.findUserByUsername(username, Role.SYSTEM_ADMIN);
        SystemAdmin sa = new SystemAdmin();
        utility.setUserModel(um, sa);
        return sa;
        
    }

    public int insertSystemAdmin(String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel sysAdmin = new SystemAdmin(username, password, name, surname, email, phone);
        return usersDao.insertUserModel(sysAdmin, Role.SYSTEM_ADMIN);

    }

    public int updateSystemAdmin(String oldUsername, String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = new SystemAdmin(username, password, name, surname, email, phone);
        return usersDao.updateUserModel(oldUsername, userModel);

    }
    
    public Planner findPlannerByUsername(String username) throws SQLException, UsernotFoundException {

        UtilityUser<Planner> utility = new UtilityUser<Planner>();
        UserModel um = usersDao.findUserByUsername(username, Role.PLANNER);
        Planner planner = new Planner();
        utility.setUserModel(um, planner);
        return planner;
    }

    public int insertPlanner(String username, String password, String name, String surname, String email, String phone) 
                              throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = new Planner(username, password, name, surname, email, phone);
        return usersDao.insertUserModel(userModel, Role.PLANNER);
    }

    public int updatePlanner(String oldUsername, String username, String password, String name, String surname, String email, String phone) 
                              throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = new Planner(username, password, name, surname, email, phone);
        return usersDao.updateUserModel(oldUsername, userModel);
    }
    
    public Maintainer findMaintainerByUsername(String username) throws SQLException, UsernotFoundException {

        UtilityUser<Maintainer> utility = new UtilityUser<Maintainer>();
        UserModel um =  usersDao.findUserByUsername(username, Role.MAINTAINER);
        Maintainer maintainer = new Maintainer();
        utility.setUserModel(um, maintainer);
        return maintainer;
    }

    public int insertMaintainer(String username, String password, String name, String surname, String email, String phone) 
                              throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = new Maintainer(username, password, name, surname, email, phone);
        return usersDao.insertUserModel(userModel, Role.MAINTAINER);
    }

    public int updateMaintainer(String oldUsername, String username, String password, String name, String surname, String email, String phone) 
                              throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = new Maintainer(username, password, name, surname, email, phone);
        return usersDao.updateUserModel(oldUsername, userModel);
    }
    
}