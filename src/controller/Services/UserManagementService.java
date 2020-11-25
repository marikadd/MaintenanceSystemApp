package controller.Services;

import java.sql.SQLException;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import configuration.Exceptions.UsernotFoundException;
import controller.Dao.*;
import java.util.LinkedList;
import model.Users.*;

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

    public ProdManager findProdManagerByUsername(String username) throws SQLException, UsernotFoundException {

        ProdManager manager = (ProdManager) usersDao.findUserByUsername(username, Role.PROD_MANAGER);
        return manager;
    }

    public void insertProdManager(String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel prodManager = (UserModel) new ProdManager(username, password, name, surname, email, phone);
        usersDao.insertUserModel(prodManager, Role.PROD_MANAGER);

    }

    public void deleteUser(String username) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {
        usersDao.deleteUserModel(username);
    }

    public void updateProdManager(String oldUsername, String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = (UserModel) new ProdManager(username, password, name, surname, email, phone);
        usersDao.updateUserModel(oldUsername, userModel);

    }
    
    public SystemAdmin findSystemAdminByUsername(String username) throws SQLException, UsernotFoundException {

        SystemAdmin sysAdmin = (SystemAdmin) usersDao.findUserByUsername(username, Role.SYSTEM_ADMIN);
        return sysAdmin;
    }

    public void insertSystemAdmin(String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel sysAdmin = (UserModel) new SystemAdmin(username, password, name, surname, email, phone);
        usersDao.insertUserModel(sysAdmin, Role.SYSTEM_ADMIN);

    }

    public void updateSystemAdmin(String oldUsername, String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = (UserModel) new SystemAdmin(username, password, name, surname, email, phone);
        usersDao.updateUserModel(oldUsername, userModel);

    }
    
    public Planner findPlannerByUsername(String username) throws SQLException, UsernotFoundException {

        Planner planner = (Planner) usersDao.findUserByUsername(username, Role.PLANNER);
        return planner;
    }

    public void insertPlanner(String username, String password, String name, String surname, String email, String phone) 
                              throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = (UserModel) new Planner(username, password, name, surname, email, phone);
        usersDao.insertUserModel(userModel, Role.PLANNER);
    }

    public void updatePlanner(String oldUsername, String username, String password, String name, String surname, String email, String phone) 
                              throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = (UserModel) new Planner(username, password, name, surname, email, phone);
        usersDao.updateUserModel(oldUsername, userModel);

    }
    
    public Maintainer findMaintainerByUsername(String username) throws SQLException, UsernotFoundException {

        Maintainer maintener = (Maintainer) usersDao.findUserByUsername(username, Role.MAINTAINER);
        return maintener;
    }

    public void insertMaintainer(String username, String password, String name, String surname, String email, String phone) 
                              throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = (UserModel) new Maintainer(username, password, name, surname, email, phone);
        usersDao.insertUserModel(userModel, Role.MAINTAINER);
    }

    public void updateMaintainer(String oldUsername, String username, String password, String name, String surname, String email, String phone) 
                              throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = (UserModel) new Maintainer(username, password, name, surname, email, phone);
        usersDao.updateUserModel(oldUsername, userModel);

    }
    
}
