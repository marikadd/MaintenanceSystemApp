/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.User;

import controller.Services.UserManagementService;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */
public class UserManagementDelete {
    
    private UserManagementService ums;
    
    public UserManagementDelete() {
    }
    
   
    @Before
    public void setUp() {
        
       ums = UserManagementService.getUserManagementService();
    }


    /**
     * Test of deleteUser method, of class UserManagementService.
     * Delete an user serached by his username
    */
    @Test
    public void testDeleteUser() throws Exception {
        System.out.println("deleteUser");
        String username = "tcaio2";
        int result= ums.deleteUser(username);
 
        int notExpectedResult = 0;
        assertNotEquals(result, notExpectedResult);
        
    }
    
    /**
     * Test of deleteUser method, of class UserManagementService.
     * Delete an user serached by an username that doesn't exist
    */
    @Test(expected=SQLException.class)
    public void testDeleteUser1() throws Exception {
        System.out.println("deleteUser");
        String username = "gmancone";
        int result= ums.deleteUser(username);
 
        int ExpectedResult = 0;
        assertEquals(result,ExpectedResult);
        
    }
        
    /**
     * Test of deleteUser method, of class UserManagementService.
     * Delete an user without passing an username
    */
    @Test(expected=SQLException.class)
    public void testDeleteUser2() throws Exception {
        System.out.println("deleteUser");
        String username = "";
        int result= ums.deleteUser(username);
 
        int ExpectedResult = 0;
        assertEquals(result,ExpectedResult);
    }

}
