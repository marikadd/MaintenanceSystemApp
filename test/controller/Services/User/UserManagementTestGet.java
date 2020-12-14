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
import configuration.Exceptions.UsernotFoundException;
import java.util.List;
import model.Users.UserModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */
public class UserManagementTestGet {

    private UserManagementService ums;
    private ConnectionForTest cft;
    private DBProduct dbProduct;

    public UserManagementTestGet() {
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
     * Test of getRoleByUsername method, of class UserManagementService, getting
     * role associated to username given as parameter.
     */
    @Test
    public void testGetRoleByUsername() throws Exception {
        System.out.println("getRoleByUsername");
        String username = "mrossi";
        String expResult = "MAINTAINER";
        String result = ums.getRoleByUsername(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of getRoleByUsername method, of class UserManagementService, getting
     * role associated to username given as parameter, that doesn't exist.
     */
    @Test(expected = UsernotFoundException.class)
    public void testGetRoleByUsername1() throws Exception {
        System.out.println("getRoleByUsername");
        String username = "pippo";
        String expResult = null;
        String result = ums.getRoleByUsername(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllUsers method, of class UserManagementService, getting a
     * list of all users.
     */
    @Test
    public void testGetAllUsers() throws Exception {
        System.out.println("getAllUsers");
        List<UserModel> list = ums.getAllUsers();
        int result = list.size();
        int ExpectedResult = 5;
        assertEquals(result, ExpectedResult);
    }
    
    /**
     * Test of getAllMaintainers method, of class UserManagementService getting
     * a list of all maintainers.
     */
    @Test
    public void testGetAllMaintainers() throws Exception {
        System.out.println("getAllMaintainers");
        UserManagementService instance = null;
        List<UserModel> list = ums.getAllMaintainers();
        int result = list.size();
        int ExpectedResult = 2;
        assertEquals(result, ExpectedResult);
    }
}
