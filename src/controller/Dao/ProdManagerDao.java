package controller.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import configuration.Database.DBFactory;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import configuration.Exceptions.UsernotFoundException;
import model.Users.ProdManager;
import model.Users.Role;

/**
 *
 * @author Group9
 */

public class ProdManagerDao {
	
	public static ProdManager findProdManagerByUsername(String username) throws SQLException, UsernotFoundException {

		Connection con = DBFactory.connectToDB();

		String query = "select * from Users u " + "where u.User_Role = ? AND " + "u.Username = ?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, Role.PROD_MANAGER.toString());
		ps.setString(2, username);

		ResultSet rs = ps.executeQuery();

		ProdManager prodManager = null;

		if (rs.next()) {

			prodManager = new ProdManager(rs.getString("Username"), rs.getString("PW"), rs.getString("User_Name"),
					rs.getString("Surname"), rs.getString("Email"), rs.getString("PhoneNumber"));

		} else {
			throw new UsernotFoundException("User with username " + username + " not found");
		}

		return prodManager;

	}

	public static void insertProdManager(ProdManager prodManager)
			throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

		validateProdManager(prodManager);

		Connection con = DBFactory.connectToDB();

		String query = "INSERT INTO Users VALUES(?,?,?,?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, prodManager.getName());
		ps.setString(2, prodManager.getSurname());
		ps.setString(3, prodManager.getUsername());
		ps.setString(4, prodManager.getPassword());
		ps.setString(5, prodManager.getEmail());
		ps.setString(6, prodManager.getPhone());
		ps.setString(7, Role.PROD_MANAGER.toString());

		boolean result = ps.execute();

		if (!result) {
			throw new UnsuccessfulUpdateException("Cannot create this user");
		}
        }

	public static void updateProdManager(String oldUsername, ProdManager prodManager)
			throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

		validateProdManager(prodManager);

		Connection con = DBFactory.connectToDB();

		String query = "UPDATE Users SET User_Name = ?, Surname = ?, Username = ?, PW = ?, Email = ?, PhoneNumber = ? "
				+ "where Username = ?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, prodManager.getName());
		ps.setString(2, prodManager.getSurname());
		ps.setString(3, prodManager.getUsername());
		ps.setString(4, prodManager.getPassword());
		ps.setString(5, prodManager.getEmail());
		ps.setString(6, prodManager.getPhone());
		ps.setString(7, oldUsername);

		boolean result = ps.execute();

		if (!result) {
			throw new UnsuccessfulUpdateException("Cannot update user with username " + oldUsername);
		}

	}

	public static void deleteProdManager(String username) throws SQLException, UnsuccessfulUpdateException {

		Connection con = DBFactory.connectToDB();

		String query = "DELETE FROM Users WHERE Username = ?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, username);

		boolean result = ps.execute();

		if (!result) {
			throw new UnsuccessfulUpdateException("Cannot delete user with username " + username);
		}

	}

	private static void validateProdManager(ProdManager prodManager) throws InvalidParameterObjectException {

		if (prodManager == null) {
			throw new InvalidParameterObjectException("The object parameters must be filled");
		}
                
                if (prodManager.getUsername() == null) {
			throw new InvalidParameterObjectException("Production Manager's username must be not null");
		}

		if (prodManager.getUsername().length() > 20) {
			throw new InvalidParameterObjectException("Production Manager's username must be at most 20 characters");
		}

		if (prodManager.getName() == null) {
			throw new InvalidParameterObjectException("Production Manager's name must be not null");
		}

		if (prodManager.getName().length() > 20) {
			throw new InvalidParameterObjectException("Production Manager's name must be at most 20 characters");
		}
                
                if (prodManager.getSurname() == null) {
			throw new InvalidParameterObjectException("Production Manager's surname must be not null");
		}

		if (prodManager.getSurname().length() > 20) {
			throw new InvalidParameterObjectException("Production Manager's surname must be at most 20 characters");
		}
                
                if (prodManager.getPassword() == null) {
			throw new InvalidParameterObjectException("Production Manager's password must be not null");
		}

		if (prodManager.getPassword().length() > 50) {
			throw new InvalidParameterObjectException("Production Manager's password must be at most 50 characters");
		}
               
                String passFormat = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,50}$";
                if (!prodManager.getPassword().matches(passFormat)){
                    throw new InvalidParameterObjectException("Password must contains at least one number, one uppercase/lowercase letter and one special character. "
                                                              + "White spaces are not allowed and password lenght must be at least 8 characters and at most 50");
                }
                
                if (prodManager.getPhone() == null) {
			throw new InvalidParameterObjectException("Production Manager's phone number must be not null");
		}

		if (prodManager.getPhone().length() != 10) {
			throw new InvalidParameterObjectException("Production Manager's phone number must be 10 characters");
		}
                
                if (prodManager.getEmail() == null) {
			throw new InvalidParameterObjectException("Production Manager's email must be not null");
		}

		if (prodManager.getEmail().length() > 40) {
			throw new InvalidParameterObjectException("Production Manager's email must be at most 40 characters");
		}
                
                String format = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$";
                if (!prodManager.getEmail().matches(format)){
                    throw new InvalidParameterObjectException("Invalid e-mail address format");
                }
        
        }

}