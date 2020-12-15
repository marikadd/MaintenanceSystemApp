/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Notification;

import configuration.Database.ConnectionForTest;
import configuration.Database.DBAbstractFactory;
import configuration.Database.DBFactoryContext;
import configuration.Database.DBManager;
import configuration.Database.DBProduct;
import controller.Services.ActivityService;
import controller.Services.NotificationService;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */
public class NotificationServiceTest {
    
    private NotificationService ns;
    private ConnectionForTest cft;
    private DBProduct dbProduct;

    
    public NotificationServiceTest() {
       
    }
    
    
    @Before
    public void setUp() {
        ns =  NotificationService.init();
        DBAbstractFactory dbFactory = new DBFactoryContext();
        cft = ConnectionForTest.init();
        dbProduct = dbFactory.getInstance(DBManager.instanceType);
        cft.setConn(dbProduct.connectToDB()); 
        setAfter();
    }
    
    @After
    public void setAfter() {
        cft.rollbackConnection();
    }

    /**
     * Test of readNotifications method, of class NotificationService, returning 
     * the number of notifications not read yet.
     */
    @Test
    public void testReadNotifications() throws Exception {
        System.out.println("readNotifications");
        int expResult = 2;
        int result = ns.readNotifications().size();
        assertEquals(expResult, result);
    }
    
}
