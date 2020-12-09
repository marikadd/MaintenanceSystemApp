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
public class PostgressProduct implements DBProduct {
    
    private final String url = "jdbc:postgresql://localhost/MaintenanceSystem";
    private final String user = "group9";
    private final String password = "Group9";

    @Override
    public Connection connectToDB() {

        DBManager dbManager = new DBManager();
        return dbManager.connect(url, user, password);
         
    }
    
    
    
}
