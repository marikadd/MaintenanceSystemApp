/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Dao;

import configuration.Database.DBFactory;
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
 * @author Group 9
 */

public class UsersDao{
    
    private static UsersDao usersDao;
    
    public static UsersDao init() {
        if(usersDao == null) usersDao = new UsersDao();
        return usersDao;
    }
    
    public UserModel findUserByUsername(String username, Role role) throws SQLException, UsernotFoundException {

		Connection con = DBFactory.connectToDB();

		String query = "select * from Users u " + "where u.Role_User = ? AND " + "u.Username = ?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, role.toString());
		ps.setString(2, username);

		ResultSet rs = ps.executeQuery();

                UserModel userModel = null; 

		if (rs.next()) {

                    userModel = getSingleUserModel(rs, role);
			

		} else {
			throw new UsernotFoundException("User with username " + username + " not found");
		}

		return userModel;

	}
    
    public List<UserModel> findUsersByRole(Role role) throws SQLException, UsernotFoundException {
        
        Connection con = DBFactory.connectToDB();

	String query = "select * from Users u " + "where u.Role_User = ?";

	PreparedStatement ps = con.prepareStatement(query);
	ps.setString(1, role.toString());
        
        ResultSet rs = ps.executeQuery();
        List<UserModel> users = new LinkedList<UserModel>();
        
        while(rs.next()) {
            
            UserModel userModel = getSingleUserModel(rs, role);
            users.add(userModel);
            
        }
        
        return users;
        
    }
    
    public void insertUserModel(UserModel userModel, Role role)
            throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        validateUserModel(userModel);

        Connection con = DBFactory.connectToDB();

        String query = "INSERT INTO Users VALUES(?,?,?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, userModel.getName());
        ps.setString(2, userModel.getSurname());
        ps.setString(3, userModel.getUsername());
        ps.setString(4, userModel.getPassword());
        ps.setString(5, userModel.getEmail());
        ps.setString(6, userModel.getPhone());
        ps.setString(7, role.toString());

        boolean result = ps.execute();

        if (!result) {
            throw new UnsuccessfulUpdateException("Cannot create this user");
        }
    }

    public void updateUserModel(String oldUsername, UserModel userModel)
            throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        validateUserModel(userModel);

        Connection con = DBFactory.connectToDB();

        String query = "UPDATE Users SET Name_User = ?, Surname = ?, Username = ?, PW = ?, Email = ?, PhoneNumber = ? "
                + "where Username = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, userModel.getName());
        ps.setString(2, userModel.getSurname());
        ps.setString(3, userModel.getUsername());
        ps.setString(4, userModel.getPassword());
        ps.setString(5, userModel.getEmail());
        ps.setString(6, userModel.getPhone());
        ps.setString(7, oldUsername);

        boolean result = ps.execute();

        if (!result) {
            throw new UnsuccessfulUpdateException("Cannot update user with username " + oldUsername);
        }

    }

    public void deleteUserModel(String username) throws SQLException, UnsuccessfulUpdateException {

        Connection con = DBFactory.connectToDB();

        String query = "DELETE FROM Users WHERE Username = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);

        boolean result = ps.execute();

        if (!result) {
            throw new UnsuccessfulUpdateException("Cannot delete user with username " + username);
        }

    }
    
    private UserModel getSingleUserModel(ResultSet rs, Role role) throws SQLException, UsernotFoundException {
        
        UserModel userModel = null;
        
        switch(role) {
                        case MAINTAINER: {
                            userModel = new Maintainer(rs.getString("Username"), rs.getString("PW"), rs.getString("Name_User"),
                                                    rs.getString("Surname"), rs.getString("Email"), rs.getString("PhoneNumber"));
                            break;
                        }
                        case PROD_MANAGER : {
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
    
    private void validateUserModel(UserModel userModel) throws InvalidParameterObjectException {

        if (userModel == null) {
            throw new InvalidParameterObjectException("The object parameters must be filled");
        }

        if (userModel.getUsername() == null) {
            throw new InvalidParameterObjectException("User's username must be not null");
        }

        if (userModel.getUsername().length() > 20) {
            throw new InvalidParameterObjectException("User's username must be at most 20 characters");
        }

        if (userModel.getName() == null) {
            throw new InvalidParameterObjectException("User's name must be not null");
        }

        if (userModel.getName().length() > 20) {
            throw new InvalidParameterObjectException("User's name must be at most 20 characters");
        }

        if (userModel.getSurname() == null) {
            throw new InvalidParameterObjectException("User's surname must be not null");
        }

        if (userModel.getSurname().length() > 20) {
            throw new InvalidParameterObjectException("User's surname must be at most 20 characters");
        }

        if (userModel.getPassword() == null) {
            throw new InvalidParameterObjectException("User's password must be not null");
        }

        if (userModel.getPassword().length() > 50) {
            throw new InvalidParameterObjectException("User's password must be at most 50 characters");
        }

        // Check valid password format
        String passFormat = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,50}$";
        if (!userModel.getPassword().matches(passFormat)) {
            throw new InvalidParameterObjectException("Password must contains at least one number, one uppercase/lowercase letter and one special character. "
                    + "White spaces are not allowed and password lenght must be at least 8 characters and at most 50");
        }

        if (userModel.getPhone() == null) {
            throw new InvalidParameterObjectException("User's phone number must be not null");
        }

        if (userModel.getPhone().length() != 10) {
            throw new InvalidParameterObjectException("User's phone number must be 10 characters");
        }
        
        if(!userModel.getPhone().matches("^[0-9]$")) {
            throw new InvalidParameterObjectException("User's phone number must be numeric");
        }

        if (userModel.getEmail() == null) {
            throw new InvalidParameterObjectException("User's email must be not null");
        }

        if (userModel.getEmail().length() > 40) {
            throw new InvalidParameterObjectException("User's email must be at most 40 characters");
        }

        // Check valid e-mail format (xxx@xxx.xxx)
        String format = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$";
        if (!userModel.getEmail().matches(format)) {
            throw new InvalidParameterObjectException("Invalid e-mail address format");
        }
    }
    
}
