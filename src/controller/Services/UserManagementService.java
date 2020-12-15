
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
 * @author Group 9
 * 
 * The public methods of this class encapsulate the methods of the DAO classes. 
 * For this reason the comments relating to the parameters and exceptions 
 * have not been written because they are already in the DAOs.
 * 
 */
public class UserManagementService {

    private static UserManagementService ums;
    private UsersDao usersDao = UsersDao.init();
    private ActivityDao activityDao = ActivityDao.init();
    private NotificationDao notificationDao = NotificationDao.init();

    /**
     * Pattern Singleton.
     */
    private UserManagementService() {
    }

    /**
     * Creates a singleton for the current class. In order to avoid conflicts
     * between threads, the method uses the synchronized construct.
     * @return an instance of the current class.
     */
    public static UserManagementService getUserManagementService() {
         
        if (ums == null) {
            synchronized (UserManagementService.class) {
                if (ums == null) {
                    ums = new UserManagementService();
                }
            }
        }
        return ums;
    }

    /**
     * This method finds the role of the user in question.
     * @param username
     * @return the role played by the user.
     * @throws SQLException
     * @throws UsernotFoundException
     */
    public String getRoleByUsername(String username) throws SQLException, UsernotFoundException {

        String role = usersDao.findRoleByUsername(username);
        return role;
    }

    /**
     * This method creates a list of all users.
     * @return a linkedlist of all users.
     * @throws SQLException
     * @throws UsernotFoundException 
     */
    public List<UserModel> getAllUsers() throws SQLException, UsernotFoundException {

        List<UserModel> userList = new LinkedList<>();
        userList = usersDao.getAllUsers();

        return userList;
    }
    
    /**
     * This method creates a list of all maintainers.
     * @return a linkedlist of all maintainers.
     * @throws SQLException
     * @throws UsernotFoundException 
     */
    public List<UserModel> getAllMaintainers() throws SQLException, UsernotFoundException {

        List<UserModel> userList = new LinkedList<>();
        userList = usersDao.findUsersByRole(Role.MAINTAINER);
        return userList;
    }
    
    /**
     * This method deletes an user.
     * @param username
     * @return either the row count for SQL Data Manipulation Language (DML) statements or
     * 0 for SQL statements that return nothing.
     * @throws InvalidParameterObjectException
     * @throws SQLException
     * @throws UnsuccessfulUpdateException 
     */
    public int deleteUser(String username) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        unassignedActivityAndNotifyPlanner(username);
        return usersDao.deleteUserModel(username);
    }
    
    /**
     * This method finds a ProdManager. 
     * @param username
     * @return a prodManager.
     * @throws SQLException
     * @throws UsernotFoundException 
     */
    public ProdManager findProdManagerByUsername(String username) throws SQLException, UsernotFoundException {

        UtilityUser<ProdManager> utility = new UtilityUser<ProdManager>();
        UserModel um = usersDao.findUserByUsername(username, Role.PROD_MANAGER);
        ProdManager pm = new ProdManager();
        utility.setUserModel(um, pm);
        return pm;
    }

    /**
     * This method inserts a ProdManager.
     * @param username
     * @param password
     * @param name
     * @param surname
     * @param email
     * @param phone
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing.
     * @throws InvalidParameterObjectException
     * @throws SQLException
     * @throws UnsuccessfulUpdateException 
     */
    public int insertProdManager(String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel prodManager = new ProdManager(username, password, name, surname, email, phone);
        return usersDao.insertUserModel(prodManager, Role.PROD_MANAGER);

    }

    /**
     * This method updates the data of a ProdManager, except his name and surname.
     * @param oldUsername
     * @param username
     * @param password
     * @param name
     * @param surname
     * @param email
     * @param phone
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing.
     * @throws InvalidParameterObjectException
     * @throws SQLException
     * @throws UnsuccessfulUpdateException
     * @throws UsernotFoundException 
     */
    public int updateProdManager(String oldUsername, String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException, UsernotFoundException {

        UserModel userModel = new ProdManager(username, password, name, surname, email, phone);
        validateNameAndSurname(oldUsername, name, surname, Role.PROD_MANAGER);
        return usersDao.updateUserModel(oldUsername, userModel);

    }
    
    /**
     * This method finds a SystemAdmin.
     * @param username
     * @return a SystemAdmin.
     * @throws SQLException
     * @throws UsernotFoundException 
     */
    public SystemAdmin findSystemAdminByUsername(String username) throws SQLException, UsernotFoundException {

        UtilityUser<SystemAdmin> utility = new UtilityUser<SystemAdmin>();
        UserModel um = usersDao.findUserByUsername(username, Role.SYSTEM_ADMIN);
        SystemAdmin sa = new SystemAdmin();
        utility.setUserModel(um, sa);
        return sa;

    }

    /**
     * The method inserts a SystemAdmin.
     * @param username
     * @param password
     * @param name
     * @param surname
     * @param email
     * @param phone
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing.
     * @throws InvalidParameterObjectException
     * @throws SQLException
     * @throws UnsuccessfulUpdateException 
     */
    public int insertSystemAdmin(String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel sysAdmin = new SystemAdmin(username, password, name, surname, email, phone);
        return usersDao.insertUserModel(sysAdmin, Role.SYSTEM_ADMIN);

    }
    /**
     * This method updates data of SystemAdmin, except his name and surname.
     * @param oldUsername
     * @param username
     * @param password
     * @param name
     * @param surname
     * @param email
     * @param phone
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing.
     * @throws InvalidParameterObjectException
     * @throws SQLException
     * @throws UnsuccessfulUpdateException
     * @throws UsernotFoundException 
     */
    public int updateSystemAdmin(String oldUsername, String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException, UsernotFoundException {

        validateNameAndSurname(oldUsername, name, surname, Role.SYSTEM_ADMIN);
        UserModel userModel = new SystemAdmin(username, password, name, surname, email, phone);
        return usersDao.updateUserModel(oldUsername, userModel);

    }
    
    /**
     * This method finds a Planner.
     * @param username
     * @return a Planner.
     * @throws SQLException
     * @throws UsernotFoundException 
     */
    public Planner findPlannerByUsername(String username) throws SQLException, UsernotFoundException {

        UtilityUser<Planner> utility = new UtilityUser<Planner>();
        UserModel um = usersDao.findUserByUsername(username, Role.PLANNER);
        Planner planner = new Planner();
        utility.setUserModel(um, planner);
        return planner;
    }

    /**
     * This method inserts a Planner.
     * @param username
     * @param password
     * @param name
     * @param surname
     * @param email
     * @param phone
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing.
     * @throws InvalidParameterObjectException
     * @throws SQLException
     * @throws UnsuccessfulUpdateException 
     */
    public int insertPlanner(String username, String password, String name, String surname, String email, String phone)
            throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = new Planner(username, password, name, surname, email, phone);
        return usersDao.insertUserModel(userModel, Role.PLANNER);
    }
    
    /**
     * This method updates data of a Planner, except his name and surname.
     * @param oldUsername
     * @param username
     * @param password
     * @param name
     * @param surname
     * @param email
     * @param phone
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing.
     * @throws InvalidParameterObjectException
     * @throws SQLException
     * @throws UnsuccessfulUpdateException
     * @throws UsernotFoundException 
     */
    public int updatePlanner(String oldUsername, String username, String password, String name, String surname, String email, String phone)
            throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException, UsernotFoundException {

        validateNameAndSurname(oldUsername, name, surname, Role.PLANNER);
        UserModel userModel = new Planner(username, password, name, surname, email, phone);
        return usersDao.updateUserModel(oldUsername, userModel);
    }

    /**
     * This method finds a Maintainer.
     * @param username
     * @return a Maintainer.
     * @throws SQLException
     * @throws UsernotFoundException 
     */
    public Maintainer findMaintainerByUsername(String username) throws SQLException, UsernotFoundException {

        UtilityUser<Maintainer> utility = new UtilityUser<Maintainer>();
        UserModel um = usersDao.findUserByUsername(username, Role.MAINTAINER);
        Maintainer maintainer = new Maintainer();
        utility.setUserModel(um, maintainer);
        return maintainer;
    }
    /**
     * This method inserts a Maintainer.
     * @param username
     * @param password
     * @param name
     * @param surname
     * @param email
     * @param phone
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing.
     * @throws InvalidParameterObjectException
     * @throws SQLException
     * @throws UnsuccessfulUpdateException 
     */
    public int insertMaintainer(String username, String password, String name, String surname, String email, String phone)
            throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        UserModel userModel = new Maintainer(username, password, name, surname, email, phone);
        return usersDao.insertUserModel(userModel, Role.MAINTAINER);
    }

    /**
     * This method updates data of a Maintainer, except his name and surname.
     * @param oldUsername
     * @param username
     * @param password
     * @param name
     * @param surname
     * @param email
     * @param phone
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing.
     * @throws InvalidParameterObjectException
     * @throws SQLException
     * @throws UnsuccessfulUpdateException
     * @throws UsernotFoundException 
     */
    public int updateMaintainer(String oldUsername, String username, String password, String name, String surname, String email, String phone)
            throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException, UsernotFoundException {

        validateNameAndSurname(oldUsername, name, surname, Role.MAINTAINER);
        UserModel userModel = new Maintainer(username, password, name, surname, email, phone);
        return usersDao.updateUserModel(oldUsername, userModel);
    }

     /**
     * This method unassigns an activity to a maintainer and notifies the planner.
     * @param username represents the username of the maintainer to whom unassign the activity.
     * @throws SQLException if the columnLabel is not valid, or if a database access error occurs,
     * or if this method is called on a closed result set.
     * @throws UnsuccessfulUpdateException if no row has been updated inside the table.
     * @throws InvalidParameterObjectException if user's attributes are null or don't respect the constraints.
     */
    private void unassignedActivityAndNotifyPlanner(String username) throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        List<MaintenanceActivity> activities = activityDao.findActivityByMaintainer(username);

        if (activities.size() > 0) {

            List<String> notifications = new ArrayList<>();

            for (MaintenanceActivity activity : activities) {
                notifications.add(String.format("Activity %s has been not assigned to %s yet", activity.getDescription(), username));
                activityDao.deassignActivity(activity.getID());
            }

            for (String message : notifications) {
                notificationDao.insertMessageNotificationPlanner(message);

            }

        }

    }
    
    /**
     * This method checks if the new name and surname match or not with those of the user in question.
     * @param oldUsername represents the old username of the user.
     * @param name represents the new name of the user.
     * @param surname represents the new surname of the user. 
     * @param role represents the role of the user. 
     * @throws SQLException if the columnLabel is not valid, or if a database access error occurs,
     * or if this method is called on a closed result set.
     * @throws UsernotFoundException if the user with the username taken as an input is not found.
     * @throws UnsuccessfulUpdateException if no row has been updated inside the table.
     */
    private void validateNameAndSurname(String oldUsername, String name, String surname, Role role) throws SQLException, UsernotFoundException, UnsuccessfulUpdateException {

        if (name == null || surname == null) {
            return;
        }

        UserModel user = usersDao.findUserByUsername(oldUsername, role);

        if (!user.getName().equals(name) || !user.getSurname().equals(surname)) {

            throw new UnsuccessfulUpdateException("The name or "
                    + "surname inserted don't match with those in database");

        }

    }

}
