/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package controller.Services.User;

import configuration.Database.ConnectionForTest;
import configuration.Database.DBAbstractFactory;
import configuration.Database.DBFactoryContext;
import configuration.Database.DBManager;
import configuration.Database.DBProduct;
import controller.Services.UserManagementService;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */
public class UserManagementTestDelete {

    private UserManagementService ums;
    private ConnectionForTest cft;
    private DBProduct dbProduct;

    public UserManagementTestDelete() {
    }

    @Before
    public void setUp() {
        ums = UserManagementService.getUserManagementService();
        DBAbstractFactory dbFactory = new DBFactoryContext();
        cft = ConnectionForTest.init();
        dbProduct = dbFactory.getInstance(DBManager.instanceType);
        cft.setConn(dbProduct.connectToDB());
        setAfter();
        cft.rollbackConnection();
    }

    @After
    public void setAfter() {
        cft.rollbackConnection();
    }

    /**
     * Test of deleteUser method, of class UserManagementService, deleting an
     * user serached by his username.
     */
    @Test
    public void testDeleteUser() throws Exception {
        System.out.println("deleteUser");
        String username = "mrossi";
        int result = ums.deleteUser(username);
        int notExpectedResult = 0;
        assertNotEquals(result, notExpectedResult);

    }

    /**
     * Test of deleteUser method, of class UserManagementService, deleting an
     * user serached by an username that doesn't exist.
     */
    @Test
    public void testDeleteUser1() throws Exception {
        System.out.println("deleteUser");
        String username = "gmancone";
        int result = ums.deleteUser(username);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);

    }

    /**
     * Test of deleteUser method, of class UserManagementService, deleting an
     * user without passing an username.
     */
    @Test
    public void testDeleteUser2() throws Exception {
        System.out.println("deleteUser");
        String username = null;
        int result = ums.deleteUser(username);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

}
