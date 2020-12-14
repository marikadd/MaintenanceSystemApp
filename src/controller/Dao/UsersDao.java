/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public static UsersDao init() {
        if (usersDao == null) {
            synchronized(UsersDao.class) {
                if(usersDao == null) {
                    usersDao = new UsersDao();
                    usersDao.cft = ConnectionForTest.init();
                    DBAbstractFactory dbFactory = new DBFactoryContext();
                    usersDao.dbProduct = dbFactory.getInstance(DBManager.instanceType);
                }
            }
        }
        return usersDao;
    }

    public String findRoleByUsername(String username) throws SQLException, UsernotFoundException {

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

    public UserModel findUserByUsername(String username, Role role) 
            throws SQLException, UsernotFoundException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "select * from Users u " + "where u.Role_User = ? AND " + "u.Username = ?";
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

    public List<UserModel> findUsersByRole(Role role) throws SQLException, UsernotFoundException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "SELECT * FROM Users u " + "WHERE u.Role_User = ?";
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

    public int insertUserModel(UserModel userModel, Role role)
            throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);
        
        validateUserModel(userModel);

        String query = "INSERT INTO Users VALUES(?,?,?,?,?,?,?)";

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

    public int updateUserModel(String oldUsername, UserModel userModel)
            throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        validateUpdate(userModel);

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query =   "UPDATE Users SET "
                       + "Username = coalesce(?,Username), PW = coalesce(?, PW), "
                       + "Email = coalesce(?,Email), PhoneNumber = coalesce(?,PhoneNumber)" 
                       + "WHERE Username = ?";

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
            if(!Role.MAINTAINER.toString().equals(role)) {
                throw new UsernotFoundException(String.format("The user %s is not a maintainer", username));
            }
        } else {
             throw new UsernotFoundException(String.format("The user with username %s not found", username));
        }
        
    }

    public int deleteUserModel(String username) throws SQLException, UnsuccessfulUpdateException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "DELETE FROM Users WHERE Username = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);

        int result = ps.executeUpdate();

        return result;
    }

    private UserModel getSingleUserModel(ResultSet rs, Role role) throws SQLException, UsernotFoundException {

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

    private UserModel getAllUserModel(ResultSet rs, String role) throws SQLException, UsernotFoundException {

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
            /*^\\d{10}$*/
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
