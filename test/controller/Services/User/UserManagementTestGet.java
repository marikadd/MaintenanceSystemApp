/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.User;

import configuration.Database.ConnectionForTest;
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

    public UserManagementTestGet() {
    }

    @Before
    public void setUp() {

        ums = UserManagementService.getUserManagementService();
        cft = ConnectionForTest.init();
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
        String expResult = "";
        String result = ums.getRoleByUsername(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllUsers method, of class UserManagementService, getting a list of
     * all users.
     */
    @Test
    public void testGetAllUsers() throws Exception {
        System.out.println("getAllUsers");
        List<UserModel> list = ums.getAllUsers();
        int result = list.size();
        int ExpectedResult = 5;
        assertEquals(result, ExpectedResult);
    }
}
