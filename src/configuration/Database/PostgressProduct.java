/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration.Database;

import configuration.Exceptions.DatabaseNotFoundException;
import java.sql.Connection;
import javax.swing.JOptionPane;


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
        Connection con = dbManager.connect(url, user, password);
         
        if(con != null) {
            return con;
        } else {
            Object[] options = {"OK"};
            int response = JOptionPane.showOptionDialog(null, "Database is down!\n "
                    + "Please, exit from application and restart.","Fatal Error",
                    JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE,
                    null, options, options[0]);
            
            if(response == JOptionPane.OK_OPTION)
                System.exit(0);
           
            throw new DatabaseNotFoundException("Database is down");
        }
    }
    
}
