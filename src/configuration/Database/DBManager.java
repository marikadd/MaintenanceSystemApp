/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package configuration.Database;

import java.sql.*;

/**
 *
 * @author Group9
 */

public class DBManager {
    private final String url = "jdbc:postgresql://localhost/MaintenanceSystem";
    private final String user = "group9";
    private final String password = "Group9";
    
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}