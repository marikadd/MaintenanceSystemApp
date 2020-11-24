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

		String query = "select * from Users u " + "where u.Ruolo = ? AND " + "u.Username = ?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, Role.PROD_MANAGER.toString());
		ps.setString(2, username);

		ResultSet rs = ps.executeQuery();

		ProdManager prodManager = null;

		if (rs.next()) {

			prodManager = new ProdManager(rs.getString("Username"), rs.getString("PW"), rs.getString("Nome"),
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

		String query = "UPDATE Users SET Nome = ?, Surname = ?, Username = ?, PW = ?, Email = ?, PhoneNumber = ? "
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

		if (prodManager.getName() == null) {
			throw new InvalidParameterObjectException("Production Manager's name must be not null");
		}

		if (prodManager.getName().length() > 20) {
			throw new InvalidParameterObjectException("Production Manager's name must be at most 20 characters");
		}
		
		//TODO Task complete validate production manager

	}

}
