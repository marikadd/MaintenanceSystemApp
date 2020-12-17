package configuration.Database;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Group9
 */
public class ConnectionForTest {

    private static ConnectionForTest cft;
    private Connection conn;
    //Specifies the name of the file that will be used to restore the db state
    private final String fileNameRollback = "DML_POSTGRESSQL.sql";

    /**
     * Pattern singleton
     */
    private ConnectionForTest() {
    }

    /**
     * Creates a singleton for the current class. In order to avoid conflicts
     * between threads, the method uses the synchronized construct.
     *
     * @return an instance of the current class
     */
    public static ConnectionForTest init() {
        if (cft == null) {
            synchronized (ConnectionForTest.class) {
                if (cft == null) {
                    cft = new ConnectionForTest();
                }
            }
        }
        return cft;
    }

    /**
     * Executes a sort of rollback, executing the SQL commands specified in a
     * file, whose name is contained in the class attribute fileNameRollback.
     */
    public void rollbackConnection() {

        File file = new File(fileNameRollback);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;

            while ((line = br.readLine()) != null) {

                if ("".equals(line.trim())) {
                    continue;
                }

                PreparedStatement ps = conn.prepareStatement(line);
                if (line.toUpperCase().startsWith("SELECT")) {
                    ps.executeQuery();
                } else {
                    ps.executeUpdate();
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConnectionForTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionForTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionForTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

}
