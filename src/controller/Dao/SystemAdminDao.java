/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import configuration.Database.DBFactory;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import configuration.Exceptions.UsernotFoundException;
import model.Users.SystemAdmin;
import model.Users.Role;

/**
 *
 * @author Group9
 */
public class SystemAdminDao {

    public static SystemAdmin findSystemAdminByUsername(String username) throws SQLException, UsernotFoundException {

        Connection con = DBFactory.connectToDB();

        String query = "select * from Users u " + "where u.User_Role = ? AND " + "u.Username = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, Role.SYSTEM_ADMIN.toString());
        ps.setString(2, username);

        ResultSet rs = ps.executeQuery();

        SystemAdmin sysAdmin = null;

        if (rs.next()) {

            sysAdmin = new SystemAdmin(rs.getString("Username"), rs.getString("PW"), rs.getString("User_Name"),
                    rs.getString("Surname"), rs.getString("Email"), rs.getString("PhoneNumber"));

        } else {
            throw new UsernotFoundException("User with username " + username + " not found");
        }

        return sysAdmin;

    }

    public static void insertSystemAdmin(SystemAdmin sysAdmin)
            throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        validateSystemAdmin(sysAdmin);

        Connection con = DBFactory.connectToDB();

        String query = "INSERT INTO Users VALUES(?,?,?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, sysAdmin.getName());
        ps.setString(2, sysAdmin.getSurname());
        ps.setString(3, sysAdmin.getUsername());
        ps.setString(4, sysAdmin.getPassword());
        ps.setString(5, sysAdmin.getEmail());
        ps.setString(6, sysAdmin.getPhone());
        ps.setString(7, Role.SYSTEM_ADMIN.toString());

        boolean result = ps.execute();

        if (!result) {
            throw new UnsuccessfulUpdateException("Cannot create this user");
        }
    }

    public static void updateSystemAdmin(String oldUsername, SystemAdmin sysAdmin)
            throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        validateSystemAdmin(sysAdmin);

        Connection con = DBFactory.connectToDB();

        String query = "UPDATE Users SET User_Name = ?, Surname = ?, Username = ?, PW = ?, Email = ?, PhoneNumber = ? "
                + "where Username = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, sysAdmin.getName());
        ps.setString(2, sysAdmin.getSurname());
        ps.setString(3, sysAdmin.getUsername());
        ps.setString(4, sysAdmin.getPassword());
        ps.setString(5, sysAdmin.getEmail());
        ps.setString(6, sysAdmin.getPhone());
        ps.setString(7, oldUsername);

        boolean result = ps.execute();

        if (!result) {
            throw new UnsuccessfulUpdateException("Cannot update user with username " + oldUsername);
        }

    }

    public static void deleteSystemAdmin(String username) throws SQLException, UnsuccessfulUpdateException {

        Connection con = DBFactory.connectToDB();

        String query = "DELETE FROM Users WHERE Username = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);

        boolean result = ps.execute();

        if (!result) {
            throw new UnsuccessfulUpdateException("Cannot delete user with username " + username);
        }

    }

    private static void validateSystemAdmin(SystemAdmin sysAdmin) throws InvalidParameterObjectException {

        if (sysAdmin == null) {
            throw new InvalidParameterObjectException("The object parameters must be filled");
        }

        if (sysAdmin.getUsername() == null) {
            throw new InvalidParameterObjectException("Admin's username must be not null");
        }

        if (sysAdmin.getUsername().length() > 20) {
            throw new InvalidParameterObjectException("Admin's username must be at most 20 characters");
        }

        if (sysAdmin.getName() == null) {
            throw new InvalidParameterObjectException("Admin's name must be not null");
        }

        if (sysAdmin.getName().length() > 20) {
            throw new InvalidParameterObjectException("Admin's name must be at most 20 characters");
        }

        if (sysAdmin.getSurname() == null) {
            throw new InvalidParameterObjectException("Admin's surname must be not null");
        }

        if (sysAdmin.getSurname().length() > 20) {
            throw new InvalidParameterObjectException("Production Manager's surname must be at most 20 characters");
        }

        if (sysAdmin.getPassword() == null) {
            throw new InvalidParameterObjectException("Admin's password must be not null");
        }

        if (sysAdmin.getPassword().length() > 50) {
            throw new InvalidParameterObjectException("Admin's password must be at most 50 characters");
        }

        // Check valid password format
        String passFormat = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,50}$";
        if (!sysAdmin.getPassword().matches(passFormat)) {
            throw new InvalidParameterObjectException("Password must contains at least one number, one uppercase/lowercase letter and one special character. "
                    + "White spaces are not allowed and password lenght must be at least 8 characters and at most 50");
        }

        if (sysAdmin.getPhone() == null) {
            throw new InvalidParameterObjectException("Admin's phone number must be not null");
        }

        if (sysAdmin.getPhone().length() != 10) {
            throw new InvalidParameterObjectException("Admin's phone number must be 10 characters");
        }

        if (sysAdmin.getEmail() == null) {
            throw new InvalidParameterObjectException("Admin's email must be not null");
        }

        if (sysAdmin.getEmail().length() > 40) {
            throw new InvalidParameterObjectException("Admin's email must be at most 40 characters");
        }

        // Check valid e-mail format (xxx@xxx.xxx)
        String format = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$";
        if (!sysAdmin.getEmail().matches(format)) {
            throw new InvalidParameterObjectException("Invalid e-mail address format");
        }
    }
}
