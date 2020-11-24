package controller.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import configuration.Database.DBFactory;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import configuration.Exceptions.UsernotFoundException;
import java.util.LinkedList;
import model.Users.Maintainer;
import model.Users.Planner;
import model.Users.Role;

/**
 *
 * @author Group9
 */

public class MaintainerDao {
	
	public static LinkedList<Maintainer> findMaintainerByUsername(String username) throws SQLException, UsernotFoundException {

		Connection con = DBFactory.connectToDB();

		String query = "select * from Users u " + "where u.User_Role = ? AND " + "u.Username = ?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, Role.MAINTAINER.toString());
		ps.setString(2, username);

		ResultSet rs = ps.executeQuery();

		Maintainer  maintainer = null;
                
               LinkedList<Maintainer> listMaintainer = new LinkedList<>();
                       
                if(!rs.next()) {
                    throw new UsernotFoundException("User with username " + username + " not found");
                } else {
                    while (rs.next()) {
                        maintainer = new Maintainer(rs.getString("Username"), rs.getString("PW"), rs.getString("User_Name"),
                                                    rs.getString("Surname"), rs.getString("Email"), rs.getString("PhoneNumber"));
                        
                        listMaintainer.add(maintainer);
                    }
                }
                
                return listMaintainer;
                
        }


	public static void insertMaintainer(Maintainer maintainer)
			throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

		validateMaintainer(maintainer);

		Connection con = DBFactory.connectToDB();

		String query = "INSERT INTO Users VALUES(?,?,?,?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, maintainer.getName());
		ps.setString(2, maintainer.getSurname());
		ps.setString(3, maintainer.getUsername());
		ps.setString(4, maintainer.getPassword());
		ps.setString(5, maintainer.getEmail());
		ps.setString(6, maintainer.getPhone());
		ps.setString(7, Role.MAINTAINER.toString());

		boolean result = ps.execute();

		if (!result) {
			throw new UnsuccessfulUpdateException("Cannot create this user");
		}
        }

	public static void updateMaintainer(String oldUsername, Maintainer maintainer)
			throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

		validateMaintainer(maintainer);

		Connection con = DBFactory.connectToDB();

		String query = "UPDATE Users SET User_Name = ?, Surname = ?, Username = ?, PW = ?, Email = ?, PhoneNumber = ? "
				+ "where Username = ?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, maintainer.getName());
		ps.setString(2, maintainer.getSurname());
		ps.setString(3, maintainer.getUsername());
		ps.setString(4, maintainer.getPassword());
		ps.setString(5, maintainer.getEmail());
		ps.setString(6, maintainer.getPhone());
		ps.setString(7, oldUsername);

		boolean result = ps.execute();

		if (!result) {
			throw new UnsuccessfulUpdateException("Cannot update user with username " + oldUsername);
		}

	}

	public static void deleteMaintainer(String username) throws SQLException, UnsuccessfulUpdateException {

		Connection con = DBFactory.connectToDB();

		String query = "DELETE FROM Users WHERE Username = ?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, username);

		boolean result = ps.execute();

		if (!result) {
                        throw new UnsuccessfulUpdateException("Cannot delete user with username " + username);
		}

	}

	private static void validateMaintainer(Maintainer maintainer) throws InvalidParameterObjectException {

		if (maintainer == null) {
			throw new InvalidParameterObjectException("The object parameters must be filled");
		}

		if (maintainer.getName() == null) {
			throw new InvalidParameterObjectException("Maintainer's name must be not null");
		}

		if (maintainer.getName().length() > 20) {
			throw new InvalidParameterObjectException("Maintainer's name must be at most 20 characters");
		}
                
                if (maintainer.getSurname() == null) {
			throw new InvalidParameterObjectException("Maintainer's surname must be not null");
		}

		if (maintainer.getSurname().length() > 20) {
			throw new InvalidParameterObjectException("Maintainer's surname must be at most 20 characters");
		}
                
                if (maintainer.getPassword() == null) {
			throw new InvalidParameterObjectException("Maintainer's password must be not null");
		}

		if (maintainer.getPassword().length() > 50) {
			throw new InvalidParameterObjectException("Maintainer's password must be at most 50 characters");
		}
                
                String passFormat = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,50}$";
                if (!maintainer.getPassword().matches(passFormat)){
                    throw new InvalidParameterObjectException("Password must contains at least one number, one uppercase/lowercase letter and one special character. "
                                                              + "White spaces are not allowed and password lenght must be at least 8 characters and at most 50");
                }
                
                if (maintainer.getPhone() == null) {
			throw new InvalidParameterObjectException("Maintainerr's phone number must be not null");
		}

		if (maintainer.getPhone().length() != 10) {
			throw new InvalidParameterObjectException("Maintainer's phone number must be 10 characters");
		}
                
                if (maintainer.getEmail() == null) {
			throw new InvalidParameterObjectException("Maintainer's email must be not null");
		}

		if (maintainer.getEmail().length() > 40) {
			throw new InvalidParameterObjectException("Maintainer's phone email must be at most 10 characters");
		}
                
                String format = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$";
                if (!maintainer.getEmail().matches(format)){
                    throw new InvalidParameterObjectException("Invalid e-mail address format");
                }
                
        }

}