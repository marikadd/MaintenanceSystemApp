/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.User;

import configuration.Exceptions.UsernotFoundException;
import controller.Services.UserManagementService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */
    public class UserManagementFind {
    
    private UserManagementService ums;
    
    
    public UserManagementFind() {
    
    }
       
    @Before
    public void setUp() {
        
        ums = UserManagementService.getUserManagementService();
        
    }
    
    
     /**
     * Test of findProdManagerByUsername method, of class UserManagementService.
     * Find a ProdManager by his correct username
     */  
    @Test
    public void testFindProdManagerByUsername() throws Exception {
        System.out.println("findProdManagerByUsername");
        String username = "tcaio";
        String expResult = "PROD_MANAGER";
        String result = ums.findProdManagerByUsername(username).getRole();
        assertEquals(expResult, result);
    }
    
     /**
     * Test of findProdManagerByUsername method, of class UserManagementService.
     * Find a ProdManager by an username that doesn't exist
     */  
    @Test(expected=UsernotFoundException.class)
    public void testFindProdManagerByUsername1() throws Exception {
        System.out.println("findProdManagerByUsername");
        String username = "mrossi";
        String expResult = "";
        String result = ums.findProdManagerByUsername(username).getRole();
        assertEquals(expResult, result);
    }
    
     /**
     * Test of findProdManagerByUsername method, of class UserManagementService.
     * Find a ProdManager by an username that belongs to another user with a different role
     */  
    @Test(expected=UsernotFoundException.class)
    public void testFindProdManagerByUsername2() throws Exception {
        System.out.println("findProdManagerByUsername");
        String username = "gdipaolo";
        String expResult = "";
        String result = ums.findProdManagerByUsername(username).getRole();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of findMaintainerByUsername method, of class UserManagementService.
     * Find a Maintainer by his correct username
     */  
    @Test
    public void testFindMaintainerByUsername() throws Exception {
        System.out.println("findMaintainerByUsername");
        String username = "mdelledonne";
        String expResult = "MAINTAINER";
        String result = ums.findMaintainerByUsername(username).getRole();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of findProdMaintainerByUsername method, of class UserManagementService.
     * Find a Maintainer by an username that doesn't exist
     */  
    @Test(expected=UsernotFoundException.class)
    public void testFindMaintainerByUsername1() throws Exception {
        System.out.println("findMaintainerByUsername");
        String username = "lbianchi";
        String expResult = "";
        String result = ums.findMaintainerByUsername(username).getRole();
        assertEquals(expResult, result);
    }
    
     /**
     * Test of findProdMaintainerByUsername method, of class UserManagementService.
     * Find a Maintainer by an username that belongs to another user with a different role
     */  
    @Test(expected=UsernotFoundException.class)
    public void testFindMaintainerByUsername2() throws Exception {
        System.out.println("findMaintainerByUsername");
        String username = "icantalupo";
        String expResult = "";
        String result = ums.findMaintainerByUsername(username).getRole();
        assertEquals(expResult, result);
    }

    /**
     * Test of findPlannerByUsername method, of class UserManagementService.
     * Find a Planner by his correct username
     */
    @Test
    public void testFindPlannerByUsername() throws Exception {
        System.out.println("findPlannerByUsername");
        String username = "icantalupo";
        String expResult = "PLANNER";
        String result = ums.findPlannerByUsername(username).getRole();
        assertEquals(expResult, result);
       
    }
     
    /**
     * Test of findPlannerByUsername method, of class UserManagementService.
     * Find a Planner by an username that doesn't exist
     */
    @Test(expected=UsernotFoundException.class) 
    public void testFindPlannerByUsername1() throws Exception {
        System.out.println("findPlannerByUsername");
        String username = "gmancone";
        String expResult = "";
        String result = ums.findPlannerByUsername(username).getRole();
        assertEquals(expResult, result);
       
    }
   
     /**
     * Test of findPlannerByUsername method, of class UserManagementService.
     * Find a Planner by an username that belongs to another user with a different role
     */
    @Test(expected=UsernotFoundException.class) 
    public void testFindPlannerByUsername2() throws Exception {
        System.out.println("findPlannerByUsername");
        String username = "tcaio";
        String expResult = "";
        String result = ums.findPlannerByUsername(username).getRole();
        assertEquals(expResult, result);
       
    }
    
    /**
     * Test of findSystemAdminByUsername method, of class UserManagementService.
     * Find a SystemAdmin by his correct username
     */
    @Test
    public void testFindSystemAdminByUsername() throws Exception {
        System.out.println("findSystemAdminByUsername");
        String username = "gdipaolo";
        String expResult = "SYSTEM_ADMIN";
        String result = ums.findSystemAdminByUsername(username).getRole();
        assertEquals(expResult, result);
       
    }
    
     /**
     * Test of findSystemAdminByUsername method, of class UserManagementService.
     * Find a SystemAdmin by an username that doesn't exist
     */
    @Test(expected=UsernotFoundException.class) 
    public void testFindSystemAdminByUsername1() throws Exception {
        System.out.println("findSystemAdminByUsername");
        String username = "mrossi";
        String expResult = "";
        String result = ums.findSystemAdminByUsername(username).getRole();
        assertEquals(expResult, result);
       
    }
    
     /**
     * Test of findSystemAdminByUsername method, of class UserManagementService.
     * Find a SystemAdmin by an username that belongs to another user with a different role
     */
    @Test(expected=UsernotFoundException.class) 
    public void testFindSystemAdminByUsername2() throws Exception {
        System.out.println("findSystemAdminByUsername");
        String username = "mdelledonne";
        String expResult = "";
        String result = ums.findSystemAdminByUsername(username).getRole();
        assertEquals(expResult, result);
       
    }
   
}