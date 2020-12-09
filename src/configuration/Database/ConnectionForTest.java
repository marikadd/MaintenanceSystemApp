/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author giusi
 */
public class ConnectionForTest {
    
    private static ConnectionForTest cft;
    private Connection conn;
    private final String fileNameRollback = "DML_POSTGRESSQL.sql";
    
    public static ConnectionForTest init() {
        if(cft == null) {
            synchronized(ConnectionForTest.class) {
                if(cft == null) cft = new ConnectionForTest();
            }
        }
        return cft;
    }

    public void rollbackConnection() {
        
        File file = new File(fileNameRollback);
        
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            
            while((line = br.readLine()) != null) {
            
                if("".equals(line.trim())) continue;
                
                PreparedStatement ps = conn.prepareStatement(line);
                ps.executeUpdate();
                
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
