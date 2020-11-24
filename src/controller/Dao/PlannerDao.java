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
import model.Users.Planner;
import model.Users.Role;

/**
 *
 * @author Group9
 * holaaaa
 */

public class PlannerDao {
	
	public static LinkedList<Planner> findPlannerByUsername(String username) throws SQLException, UsernotFoundException {

		Connection con = DBFactory.connectToDB();

		String query = "select * from Users u " + "where u.User_Role = ? AND " + "u.Username = ?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, Role.PLANNER.toString());
		ps.setString(2, username);
                
		ResultSet rs = ps.executeQuery();

		Planner planner = null;
                
                LinkedList<Planner> listPlanner = new LinkedList<>();
                       
                if(!rs.next()) {
                    throw new UsernotFoundException("User with username " + username + " not found");
                } else {
                    while (rs.next()) {
                        planner = new Planner(rs.getString("Username"), rs.getString("PW"), rs.getString("User_Name"),
                                              rs.getString("Surname"), rs.getString("Email"), rs.getString("PhoneNumber"));
                        
                        listPlanner.add(planner);
                    }
                }    
                    
                return listPlanner;
        }

	public static void insertPlanner (Planner planner)
			throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

		validatePlanner(planner);

		Connection con = DBFactory.connectToDB();

		String query = "INSERT INTO Users VALUES(?,?,?,?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, planner.getName());
		ps.setString(2, planner.getSurname());
		ps.setString(3, planner.getUsername());
		ps.setString(4, planner.getPassword());
		ps.setString(5, planner.getEmail());
		ps.setString(6, planner.getPhone());
		ps.setString(7, Role.PLANNER.toString());

		boolean result = ps.execute();

		if (!result) {
			throw new UnsuccessfulUpdateException("Cannot create this user");
		}
        }

	public static void updatePlanner(String oldUsername, Planner planner)
			throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

		validatePlanner(planner);

		Connection con = DBFactory.connectToDB();

		String query = "UPDATE Users SET User_Name = ?, Surname = ?, Username = ?, PW = ?, Email = ?, PhoneNumber = ? "
				+ "where Username = ?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, planner.getName());
		ps.setString(2, planner.getSurname());
		ps.setString(3, planner.getUsername());
		ps.setString(4, planner.getPassword());
		ps.setString(5, planner.getEmail());
		ps.setString(6, planner.getPhone());
		ps.setString(7, oldUsername);

		boolean result = ps.execute();

		if (!result) {
			throw new UnsuccessfulUpdateException("Cannot update user with username " + oldUsername);
		}

	}

	public static void deletePlanner(String username) throws SQLException, UnsuccessfulUpdateException {

		Connection con = DBFactory.connectToDB();

		String query = "DELETE FROM Users WHERE Username = ?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, username);

		boolean result = ps.execute();

		if (!result) {
			throw new UnsuccessfulUpdateException("Cannot delete user with username " + username);
		}

	}

	private static void validatePlanner(Planner planner) throws InvalidParameterObjectException {

		if (planner == null) {
			throw new InvalidParameterObjectException("The object parameters must be filled");
		}
                
                if (planner.getUsername() == null) {
			throw new InvalidParameterObjectException("Planner's username must be not null");
		}

		if (planner.getUsername().length() > 20) {
			throw new InvalidParameterObjectException("Planner's username must be at most 20 characters");
		}

		if (planner.getName() == null) {
			throw new InvalidParameterObjectException("Planner's name must be not null");
		}

		if (planner.getName().length() > 20) {
			throw new InvalidParameterObjectException("Planner's name must be at most 20 characters");
		}
                
                if (planner.getSurname() == null) {
			throw new InvalidParameterObjectException("Planner's surname must be not null");
		}

		if (planner.getSurname().length() > 20) {
			throw new InvalidParameterObjectException("Planner's surname must be at most 20 characters");
		}
                
                if (planner.getPassword() == null) {
			throw new InvalidParameterObjectException("Planner's password must be not null");
		}

		if (planner.getPassword().length() > 50) {
			throw new InvalidParameterObjectException("Planner's password must be at most 50 characters");
		}
                
                String passFormat = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,50}$";
                if (!planner.getPassword().matches(passFormat)){
                    throw new InvalidParameterObjectException("Password must contains at least one number, one uppercase/lowercase letter and one special character. "
                                                              + "White spaces are not allowed and password lenght must be at least 8 characters and at most 50");
                }
                
                if (planner.getPhone() == null) {
			throw new InvalidParameterObjectException("Planner's phone number must be not null");
		}

		if (planner.getPhone().length() != 10) {
			throw new InvalidParameterObjectException("Planner's phone number must be 10 characters");
                }
                
                if (planner.getEmail() == null) {
			throw new InvalidParameterObjectException("Production Manager's email must be not null");
		}

		if (planner.getEmail().length() > 40) {
			throw new InvalidParameterObjectException("Production Manager's email must be at most 40 characters");
		}
                
                String format = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$";
                if (!planner.getEmail().matches(format)){
                    throw new InvalidParameterObjectException("Invalid e-mail address format");
                }
        
        }

}