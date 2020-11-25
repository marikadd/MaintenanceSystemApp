package configuration.Database;

import java.sql.Connection;

public class DBFactory {
	
	//TODO queue database
	
	public static Connection connectToDB() {
		
		DBManager dbManager = new DBManager();
		return dbManager.connect();
        }

}