package controller.Services;

import java.sql.SQLException;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import configuration.Exceptions.UsernotFoundException;
import controller.Dao.ProdManagerDao;
import model.Users.ProdManager;

public class UserManagementService {

	private static UserManagementService ums;

        //Singleton
	public static UserManagementService getUserManagementService() {
		if (ums == null)
			ums = new UserManagementService();
		return ums;
	}

	public void insertProdManager(String username, String password, String name, String surname, String email,
			String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

		ProdManager prodManager = new ProdManager(username, password, name, surname, email, phone);
		ProdManagerDao.insertProdManager(prodManager);

	}

	//TODO Task develop method to get, update and delete a prod manager

}
