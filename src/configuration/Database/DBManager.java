package configuration.Database;

import java.sql.*;

/**
 *
 * @author Group9 
 * The purpose of the class is to provide a method that creates a
 * connection to a specific database.
 */
public class DBManager {

    /**
     * The constant DBInstanceType has an informative purpose, which is to
     * declare which database is used at the moment. 
     * In case the user wants to use another DB, it is required to change the 
     * value of this constant value(after adding another value to the enum 
     * DBInstanceType).
     */
    public final static DBInstanceType instanceType = DBInstanceType.POSTGRESS;

    /**
     * Creates a connection to a specific database
     *
     * @param url represents a database url (if using a postgresql database, the
     * url must be of the form jdbc:subprotocol:subname)
     * @param user represents the database user on whose behalf the connection
     * is being made
     * @param password represents the user's password
     * @return a connection to the URL
     */
    public Connection connect(String url, String user, String password) {
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
