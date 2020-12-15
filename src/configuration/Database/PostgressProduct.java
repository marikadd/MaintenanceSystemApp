package configuration.Database;

import configuration.Exceptions.DatabaseNotFoundException;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Group9 
 * This class implements the interface DBProduct. It represents
 * the cocrete product of the factory method pattern. The concrete product is an
 * instance of a connection to a postgresql database.
 */
public class PostgressProduct implements DBProduct {

    private final String url = "jdbc:postgresql://localhost/MaintenanceSystem";
    private final String user = "group9";
    private final String password = "Group9";

    /**
     * Creates a connection to a postgresql database
     *
     * @return : a Connection object representing the connection to a postgresql
     * database.
     */
    @Override
    public Connection connectToDB() {

        DBManager dbManager = new DBManager();
        Connection con = dbManager.connect(url, user, password);

        if (con != null) {
            return con;
        } else {
            Object[] options = {"OK"};
            int response = JOptionPane.showOptionDialog(null, "Database is down!\n "
                    + "Please, exit from application and restart.", "Fatal Error",
                    JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE,
                    null, options, options[0]);

            if (response == JOptionPane.OK_OPTION) {
                System.exit(0);
            }

            throw new DatabaseNotFoundException("Database is down");
        }
    }

}
