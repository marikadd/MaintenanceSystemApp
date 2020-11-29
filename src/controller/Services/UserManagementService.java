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
import java.util.LinkedList;
import java.util.List;
import model.Users.*;

/**
 *
 * @author Group9
 */

public class UserManagementService {

    private static UserManagementService ums;
    private UsersDao usersDao;

    // Singleton
    public static UserManagementService getUserManagementService() {
        if (ums == null) {
            ums = new UserManagementService();
        }
        return ums;
    }
    
    public UserManagementService() {
        usersDao = UsersDao.init();
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

    public void deleteUser(String username) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {
        usersDao.deleteUserModel(username);
    }
    
    public ProdManager findProdManagerByUsername(String username) throws SQLException, UsernotFoundException {

        UtilityUser<ProdManager> utility = new UtilityUser<ProdManager>();
        UserModel um = usersDao.findUserByUsername(username, Role.PROD_MANAGER);
        ProdManager pm = new ProdManager();
        utility.setUserModel(um, pm);
        return pm;
    }

    public void insertProdManager(String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel prodManager = new ProdManager(username, password, name, surname, email, phone);
        usersDao.insertUserModel(prodManager, Role.PROD_MANAGER);

    }

    public void updateProdManager(String oldUsername, String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = new ProdManager(username, password, name, surname, email, phone);
        usersDao.updateUserModel(oldUsername, userModel);

    }
    
    public SystemAdmin findSystemAdminByUsername(String username) throws SQLException, UsernotFoundException {
        
        UtilityUser<SystemAdmin> utility = new UtilityUser<SystemAdmin>();
        UserModel um = usersDao.findUserByUsername(username, Role.SYSTEM_ADMIN);
        SystemAdmin sa = new SystemAdmin();
        utility.setUserModel(um, sa);
        return sa;
        
    }

    public void insertSystemAdmin(String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel sysAdmin = new SystemAdmin(username, password, name, surname, email, phone);
        usersDao.insertUserModel(sysAdmin, Role.SYSTEM_ADMIN);

    }

    public void updateSystemAdmin(String oldUsername, String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = new SystemAdmin(username, password, name, surname, email, phone);
        usersDao.updateUserModel(oldUsername, userModel);

    }
    
    public Planner findPlannerByUsername(String username) throws SQLException, UsernotFoundException {

        UtilityUser<Planner> utility = new UtilityUser<Planner>();
        UserModel um = usersDao.findUserByUsername(username, Role.PLANNER);
        Planner planner = new Planner();
        utility.setUserModel(um, planner);
        return planner;
    }

    public void insertPlanner(String username, String password, String name, String surname, String email, String phone) 
                              throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = new Planner(username, password, name, surname, email, phone);
        usersDao.insertUserModel(userModel, Role.PLANNER);
    }

    public void updatePlanner(String oldUsername, String username, String password, String name, String surname, String email, String phone) 
                              throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = new Planner(username, password, name, surname, email, phone);
        usersDao.updateUserModel(oldUsername, userModel);
    }
    
    public Maintainer findMaintainerByUsername(String username) throws SQLException, UsernotFoundException {

        UtilityUser<Maintainer> utility = new UtilityUser<Maintainer>();
        UserModel um =  usersDao.findUserByUsername(username, Role.MAINTAINER);
        Maintainer maintainer = new Maintainer();
        utility.setUserModel(um, maintainer);
        return maintainer;
    }

    public void insertMaintainer(String username, String password, String name, String surname, String email, String phone) 
                              throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = new Maintainer(username, password, name, surname, email, phone);
        usersDao.insertUserModel(userModel, Role.MAINTAINER);
    }

    public void updateMaintainer(String oldUsername, String username, String password, String name, String surname, String email, String phone) 
                              throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = new Maintainer(username, password, name, surname, email, phone);
        usersDao.updateUserModel(oldUsername, userModel);
    }
    
}