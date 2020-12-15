package controller.Dao;

import configuration.Database.ConnectionForTest;
import configuration.Database.DBAbstractFactory;
import configuration.Database.DBFactoryContext;
import configuration.Database.DBManager;
import configuration.Database.DBProduct;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import configuration.Exceptions.UsernotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.Users.Maintainer;
import model.Users.Planner;
import model.Users.ProdManager;
import model.Users.Role;
import model.Users.SystemAdmin;
import model.Users.UserModel;

/**
 *
 * @author Group9
 */
public class UsersDao {

    private static UsersDao usersDao;
    private DBProduct dbProduct;
    private ConnectionForTest cft;

    /**
     * Pattern Singleton
     */
    private UsersDao() {
    }

    /**
     * Creates a singleton for the current class. In order to avoid conflicts
     * between threads, the method uses the synchronized construct.
     *
     * @return an instance of the current class
     */
    public static UsersDao init() {
        if (usersDao == null) {
            synchronized (UsersDao.class) {
                if (usersDao == null) {
                    usersDao = new UsersDao();
                    usersDao.cft = ConnectionForTest.init();
                    DBAbstractFactory dbFactory = new DBFactoryContext();
                    usersDao.dbProduct = dbFactory.getInstance(DBManager.instanceType);
                }
            }
        }
        return usersDao;
    }

    /**
     * Inserts the UserModel passed in input inside the table Users
     *
     * @param userModel represents the user to insert inside the table Users
     * @param role represents the user's role
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws InvalidParameterObjectException if the UserModel taken as an
     * input is null or its attributes don't respect the constraints specified
     * in the database.
     * @throws SQLException if a database access error occurs
     */
    public int insertUserModel(UserModel userModel, Role role)
            throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        validateUserModel(userModel);

        String query = "insert into Users values(?,?,?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, userModel.getName());
        ps.setString(2, userModel.getSurname());
        ps.setString(3, userModel.getUsername());
        ps.setString(4, userModel.getPassword());
        ps.setString(5, userModel.getEmail());
        ps.setString(6, userModel.getPhone());
        ps.setString(7, role.toString());

        int result = ps.executeUpdate();

        return result;
    }

    /**
     * Updates the user's username, password,email or phoneNumber inside the
     * table Users. It is not necessary that every field has to be modified.
     *
     * @param oldUsername represents the user's old username
     * @param userModel represents the user to insert inside the table Users
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws InvalidParameterObjectException if the UserModel taken as an
     * input is null or its attributes don't respect the constraints specified
     * in the database.
     * @throws SQLException if a database access error occurs
     * @throws UnsuccessfulUpdateException if no row has been updated
     */
    public int updateUserModel(String oldUsername, UserModel userModel)
            throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        validateUpdate(userModel);

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "update Users set "
                + "Username = coalesce(?,Username), PW = coalesce(?, PW), "
                + "Email = coalesce(?,Email), PhoneNumber = coalesce(?,PhoneNumber)"
                + "where Username = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, userModel.getUsername());
        ps.setString(2, userModel.getPassword());
        ps.setString(3, userModel.getEmail());
        ps.setString(4, userModel.getPhone());
        ps.setString(5, oldUsername);
        int result = ps.executeUpdate();

        if (result == 0) {
            throw new UnsuccessfulUpdateException("No row update!");
        }

        return result;
    }

    /**
     * Deletes the user specified as an input from the table Users
     *
     * @param username represents the user's username
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     */
    public int deleteUserModel(String username) throws SQLException, UnsuccessfulUpdateException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "DELETE FROM Users WHERE Username = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);

        int result = ps.executeUpdate();

        return result;
    }

    /**
     * Executes a query on the database table Users that, given the user's
     * username, finds his/her role.
     *
     * @param username represents the user's username
     * @return role is a string representing the user's role
     * @throws SQLException if a database access error occurs or this method is
     * called on a closed connection
     * @throws UsernotFoundException if the specified user doesn't exist in the
     * table Users
     */
    public String findRoleByUsername(String username)
            throws SQLException, UsernotFoundException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "select Role_User " + "from Users " + "where Username= ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        String role = null;
        if (rs.next()) {
            role = rs.getString("Role_User");
        } else {
            throw new UsernotFoundException("User " + username + " not found");
        }

        return role;
    }

    /**
     * Creates a specific user model executing a query on the database table
     * Users that, given the user's username and his/her role, returns all of
     * his/her informations.
     *
     * @param username represents the user's username
     * @param role represents the user's role
     * @return a specific instance of the interface UserModel
     * @throws SQLException if the columnLabel is not valid, or if a database
     * access error occurs, or if this method is called on a closed result set
     * @throws UsernotFoundException if the user with the specified role doesn't
     * exist
     */
    public UserModel findUserByUsername(String username, Role role)
            throws SQLException, UsernotFoundException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "select * from Users u " + "where u.Role_User = ? and " + "u.Username = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, role.toString());
        ps.setString(2, username);
        ResultSet rs = ps.executeQuery();

        UserModel userModel = null;
        if (rs.next()) {
            userModel = getSingleUserModel(rs, role);
        } else {
            throw new UsernotFoundException("User " + username + " not found");
        }

        return userModel;
    }

    /**
     * Creates a list of UserModel containing all of the users, inside the
     * database table Users, that have the role specified in input.
     *
     * @param role represents the role of the users that the method has to find
     * @return a LinkedList of UserModel that have the same role
     * @throws SQLException if the columnLabel passed to the method getString is
     * not valid, or if a database access error occurs, or if this method is
     * called on a closed result set
     * @throws UsernotFoundException if the user with the specified role doesn't
     * exist
     */
    public List<UserModel> findUsersByRole(Role role) throws SQLException, UsernotFoundException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "select * from Users u " + "where u.Role_User = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, role.toString());
        ResultSet rs = ps.executeQuery();

        List<UserModel> users = new LinkedList<UserModel>();
        while (rs.next()) {
            UserModel userModel = getSingleUserModel(rs, role);
            users.add(userModel);
        }

        return users;
    }
    
    /**
     * Checks if an user is a maintainer.
     *
     * @param username represents the user's username
     * @throws SQLException if the columnLabel is not valid, or if a database
     * access error occurs, or if this method is called on a closed result set
     * @throws UsernotFoundException if the user with the username taken as an
     * input is not found, or if the user is not a maintainer
     */
    public void checkuserMaintainer(String username) throws SQLException, UsernotFoundException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "select * from Users u " + "where u.Username = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        UserModel userModel = null;
        if (rs.next()) {
            String role = rs.getString("role_user");
            if (!Role.MAINTAINER.toString().equals(role)) {
                throw new UsernotFoundException(String.format("The user %s is not a maintainer", username));
            }
        } else {
            throw new UsernotFoundException(String.format("The user with username %s not found", username));
        }
    }

    /**
     * Creates a list of UserModel containing all of the users inside the
     * database table Users.
     *
     * @return a LinkedList of UserModel
     * @throws SQLException if the columnLabel passed to the method getString is
     * not valid, or if a database access error occurs, or if this method is
     * called on a closed result set
     * @throws UsernotFoundException if the user with the specified role doesn't
     * exist
     */
    public List<UserModel> getAllUsers() throws SQLException, UsernotFoundException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);
        String query = "select * from Users";

        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<UserModel> users = new LinkedList<>();

        while (rs.next()) {
            UserModel userModel = getAllUserModel(rs, rs.getString("Role_User"));
            users.add(userModel);
        }

        return users;
    }

    /**
     * Creates a specific UserModel from the result of a query.
     *
     * @param rs is the ResultSet obtained from the execution of a query
     * @param role is the role of the user
     * @return a specific instance of the interface UserModel
     * @throws SQLException if the columnLabel passed to the method getString is
     * not valid, or if a database access error occurs, or if this method is
     * called on a closed result set
     * @throws UsernotFoundException if the user with the specified role doesn't
     * exist
     */
    private UserModel getSingleUserModel(ResultSet rs, Role role)
            throws SQLException, UsernotFoundException {

        UserModel userModel = null;

        switch (role) {
            case MAINTAINER: {
                userModel = new Maintainer(rs.getString("Username"), rs.getString("PW"), rs.getString("Name_User"),
                        rs.getString("Surname"), rs.getString("Email"), rs.getString("PhoneNumber"));
                break;
            }

            case PROD_MANAGER: {
                userModel = new ProdManager(rs.getString("Username"), rs.getString("PW"), rs.getString("Name_User"),
                        rs.getString("Surname"), rs.getString("Email"), rs.getString("PhoneNumber"));
                break;
            }

            case PLANNER: {
                userModel = new Planner(rs.getString("Username"), rs.getString("PW"), rs.getString("Name_User"),
                        rs.getString("Surname"), rs.getString("Email"), rs.getString("PhoneNumber"));

                break;
            }

            case SYSTEM_ADMIN: {
                userModel = new SystemAdmin(rs.getString("Username"), rs.getString("PW"), rs.getString("Name_User"),
                        rs.getString("Surname"), rs.getString("Email"), rs.getString("PhoneNumber"));

                break;
            }

            default: {
                throw new UsernotFoundException("User with role " + role.toString() + " not found");
            }
        }

        return userModel;
    }

    /**
     * Creates a specific UserModel from the result of a query.
     *
     * @param rs is the ResultSet obtained from the execution of a query
     * @param role is a string representing the role of the user
     * @return a specific instance of the interface UserModel
     * @throws SQLException if the columnLabel passed to the method getString is
     * not valid, or if a database access error occurs, or if this method is
     * called on a closed result set
     * @throws UsernotFoundException if the user with the specified role doesn't
     * exist
     */
    private UserModel getAllUserModel(ResultSet rs, String role)
            throws SQLException, UsernotFoundException {

        UserModel userModel = null;

        switch (role) {
            case "MAINTAINER": {
                userModel = new Maintainer(rs.getString("Username"), rs.getString("PW"), rs.getString("Name_User"),
                        rs.getString("Surname"), rs.getString("Email"), rs.getString("PhoneNumber"));
                break;
            }

            case "PROD_MANAGER": {
                userModel = new ProdManager(rs.getString("Username"), rs.getString("PW"), rs.getString("Name_User"),
                        rs.getString("Surname"), rs.getString("Email"), rs.getString("PhoneNumber"));
                break;
            }

            case "PLANNER": {
                userModel = new Planner(rs.getString("Username"), rs.getString("PW"), rs.getString("Name_User"),
                        rs.getString("Surname"), rs.getString("Email"), rs.getString("PhoneNumber"));

                break;
            }

            case "SYSTEM_ADMIN": {
                userModel = new SystemAdmin(rs.getString("Username"), rs.getString("PW"), rs.getString("Name_User"),
                        rs.getString("Surname"), rs.getString("Email"), rs.getString("PhoneNumber"));

                break;
            }
        }

        return userModel;
    }

    /**
     * Checks if the UserModel taken in input is null, if its attributes are
     * null or if the attributes don't respect the constraints defined in the
     * database tables.
     *
     * @param userModel represents the user
     * @throws InvalidParameterObjectException if the user's attributes are null
     * or don't respect the constraints
     */
    private void validateUserModel(UserModel userModel) throws InvalidParameterObjectException {

        if (userModel == null) {
            throw new InvalidParameterObjectException("The object parameters must be filled");
        }

        if (userModel.getUsername() == null || userModel.getUsername().isBlank()) {
            throw new InvalidParameterObjectException("User's username must be required");
        }

        if (userModel.getUsername().length() > 20) {
            throw new InvalidParameterObjectException("User's username must be at most 20 characters");
        }

        if (userModel.getName() == null || userModel.getName().isBlank()) {
            throw new InvalidParameterObjectException("User's name must be required");
        }

        if (userModel.getName().length() > 20) {
            throw new InvalidParameterObjectException("User's name must be at most 20 characters");
        }

        if (userModel.getSurname() == null || userModel.getSurname().isBlank()) {
            throw new InvalidParameterObjectException("User's surname must be required");
        }

        if (userModel.getSurname().length() > 20) {
            throw new InvalidParameterObjectException("User's surname must be at most 20 characters");
        }

        if (userModel.getPassword() == null || userModel.getPassword().isBlank()) {
            throw new InvalidParameterObjectException("User's password must be required");
        }

        String passFormat = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,50}$";
        if (!userModel.getPassword().matches(passFormat)) {
            throw new InvalidParameterObjectException("Password must contains at least one number, one uppercase/lowercase letter and one special character. "
                    + "White spaces are not allowed and password lenght must be at least 8 characters and at most 50");
        }

        if (userModel.getPhone() == null) {
            throw new InvalidParameterObjectException("User's phone number must be required");
        }

        if (userModel.getPhone().length() != 10) {
            throw new InvalidParameterObjectException("User's phone number must be 10 characters");
        }

        if (!userModel.getPhone().matches("[0-9]+")) {
            throw new InvalidParameterObjectException("User's phone number must be numeric");
        }

        if (userModel.getEmail() == null) {
            throw new InvalidParameterObjectException("User's email must be required");
        }

        if (userModel.getEmail().length() > 40) {
            throw new InvalidParameterObjectException("User's email must be at most 40 characters");
        }

        String format = "^(.+)@(.+)$";
        if (!userModel.getEmail().matches(format)) {
            throw new InvalidParameterObjectException("Invalid e-mail address format");
        }
    }

    /**
     * Checks if the UserModel taken in input is null, if its attributes are
     * null or if the attributes don't respect the constraints defined in the
     * database tables.
     *
     * @param userModel represents the user
     * @throws InvalidParameterObjectException if the user's attributes are null
     * or don't respect the constraints
     */
    private void validateUpdate(UserModel userModel) throws InvalidParameterObjectException {

        if (userModel == null) {
            throw new InvalidParameterObjectException("The object parameters must be filled");
        }

        if (userModel.getUsername() != null) {
            if (userModel.getUsername().length() > 20) {
                throw new InvalidParameterObjectException("User's username must be at most 20 characters");
            }
        }

        if (userModel.getName() != null) {
            if (userModel.getName().length() > 20) {
                throw new InvalidParameterObjectException("User's name must be at most 20 characters");
            }
        }

        if (userModel.getSurname() != null) {
            if (userModel.getSurname().length() > 20) {
                throw new InvalidParameterObjectException("User's surname must be at most 20 characters");
            }
        }

        if (userModel.getPassword() != null) {
            String passFormat = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,50}$";
            if (!userModel.getPassword().matches(passFormat)) {
                throw new InvalidParameterObjectException("Password must contains at least one number, one uppercase/lowercase letter and one special character. "
                        + "White spaces are not allowed and password lenght must be at least 8 characters and at most 50");
            }
        }

        if (userModel.getPhone() != null) {
            if (userModel.getPhone().length() != 10) {
                throw new InvalidParameterObjectException("User's phone number must be 10 characters");
            }
        }

        if (userModel.getPhone() != null) {
            if (!userModel.getPhone().matches("[0-9]+")) {
                throw new InvalidParameterObjectException("User's phone number must be numeric");
            }
        }

        if (userModel.getEmail() != null) {
            if (userModel.getEmail().length() > 40) {
                throw new InvalidParameterObjectException("User's email must be at most 40 characters");
            }
        }

        if (userModel.getEmail() != null) {
            String format = "^(.+)@(.+)$";
            if (!userModel.getEmail().matches(format)) {
                throw new InvalidParameterObjectException("Invalid e-mail address format");
            }
        }
    }

}