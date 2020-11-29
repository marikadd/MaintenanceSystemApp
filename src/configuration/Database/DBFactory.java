/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package configuration.Database;

import java.sql.Connection;

/**
 *
 * @author Group9
 */

public class DBFactory {
	
    public static Connection connectToDB() {
		
	DBManager dbManager = new DBManager();
	return dbManager.connect();
    }
}