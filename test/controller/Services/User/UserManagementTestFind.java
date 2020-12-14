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
import configuration.Exceptions.UsernotFoundException;
import controller.Services.UserManagementService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */
public class UserManagementTestFind {

    private UserManagementService ums;
    private ConnectionForTest cft;
    private DBProduct dbProduct;

    public UserManagementTestFind() {

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
     * Test of findProdManagerByUsername method, of class UserManagementService,
     * finding a ProdManager by his correct username.
     */
    @Test
    public void testFindProdManagerByUsername() throws Exception {
        System.out.println("findProdManagerByUsername");
        String username = "lfermi";
        String expResult = "PROD_MANAGER";
        String result = ums.findProdManagerByUsername(username).getRole();
        assertEquals(expResult, result);
    }

    /**
     * Test of findProdManagerByUsername method, of class UserManagementService,
     * finding a ProdManager by an username that doesn't exist.
     */
    @Test(expected = UsernotFoundException.class)
    public void testFindProdManagerByUsername1() throws Exception {
        System.out.println("findProdManagerByUsername");
        String username = "icantalupo";
        String expResult = null;
        String result = ums.findProdManagerByUsername(username).getRole();
        assertEquals(expResult, result);
    }

    /**
     * Test of findProdManagerByUsername method, of class UserManagementService,
     * finding a ProdManager by an username that belongs to another user with a
     * different role.
     */
    @Test(expected = UsernotFoundException.class)
    public void testFindProdManagerByUsername2() throws Exception {
        System.out.println("findProdManagerByUsername");
        String username = "gcoppola";
        String expResult = null;
        String result = ums.findProdManagerByUsername(username).getRole();
        assertEquals(expResult, result);
    }

    /**
     * Test of findMaintainerByUsername method, of class UserManagementService,
     * finding a Maintainer by his correct username.
     */
    @Test
    public void testFindMaintainerByUsername() throws Exception {
        System.out.println("findMaintainerByUsername");
        String username = "mrossi";
        String expResult = "MAINTAINER";
        String result = ums.findMaintainerByUsername(username).getRole();
        assertEquals(expResult, result);
    }

    /**
     * Test of findProdMaintainerByUsername method, of class
     * UserManagementService, finding a Maintainer by an username that doesn't
     * exist.
     */
    @Test(expected = UsernotFoundException.class)
    public void testFindMaintainerByUsername1() throws Exception {
        System.out.println("findMaintainerByUsername");
        String username = "gdiapolo";
        String expResult = null;
        String result = ums.findMaintainerByUsername(username).getRole();
        assertEquals(expResult, result);
    }

    /**
     * Test of findProdMaintainerByUsername method, of class
     * UserManagementService, finding a Maintainer by an username that belongs
     * to another user with a different role.
     */
    @Test(expected = UsernotFoundException.class)
    public void testFindMaintainerByUsername2() throws Exception {
        System.out.println("findMaintainerByUsername");
        String username = "lfermi";
        String expResult = null;
        String result = ums.findMaintainerByUsername(username).getRole();
        assertEquals(expResult, result);
    }

    /**
     * Test of findPlannerByUsername method, of class UserManagementService,
     * finding a Planner by his correct username.
     */
    @Test
    public void testFindPlannerByUsername() throws Exception {
        System.out.println("findPlannerByUsername");
        String username = "lbianchi";
        String expResult = "PLANNER";
        String result = ums.findPlannerByUsername(username).getRole();
        assertEquals(expResult, result);

    }

    /**
     * Test of findPlannerByUsername method, of class UserManagementService,
     * finding a Planner by an username that doesn't exist.
     */
    @Test(expected = UsernotFoundException.class)
    public void testFindPlannerByUsername1() throws Exception {
        System.out.println("findPlannerByUsername");
        String username = "gmancone";
        String expResult = null;
        String result = ums.findPlannerByUsername(username).getRole();
        assertEquals(expResult, result);

    }

    /**
     * Test of findPlannerByUsername method, of class UserManagementService,
     * finding a Planner by an username that belongs to another user with a
     * different role.
     */
    @Test(expected = UsernotFoundException.class)
    public void testFindPlannerByUsername2() throws Exception {
        System.out.println("findPlannerByUsername");
        String username = "mrossi";
        String expResult = null;
        String result = ums.findPlannerByUsername(username).getRole();
        assertEquals(expResult, result);

    }

    /**
     * Test of findSystemAdminByUsername method, of class UserManagementService,
     * finding a SystemAdmin by his correct username.
     */
    @Test
    public void testFindSystemAdminByUsername() throws Exception {
        System.out.println("findSystemAdminByUsername");
        String username = "gcoppola";
        String expResult = "SYSTEM_ADMIN";
        String result = ums.findSystemAdminByUsername(username).getRole();
        assertEquals(expResult, result);

    }

    /**
     * Test of findSystemAdminByUsername method, of class UserManagementService,
     * finding a SystemAdmin by an username that doesn't exist.
     */
    @Test(expected = UsernotFoundException.class)
    public void testFindSystemAdminByUsername1() throws Exception {
        System.out.println("findSystemAdminByUsername");
        String username = "tcaio";
        String expResult = null;
        String result = ums.findSystemAdminByUsername(username).getRole();
        assertEquals(expResult, result);

    }

    /**
     * Test of findSystemAdminByUsername method, of class UserManagementService,
     * finding a SystemAdmin by an username that belongs to another user with a
     * different role.
     */
    @Test(expected = UsernotFoundException.class)
    public void testFindSystemAdminByUsername2() throws Exception {
        System.out.println("findSystemAdminByUsername");
        String username = "mrossi";
        String expResult = null;
        String result = ums.findSystemAdminByUsername(username).getRole();
        assertEquals(expResult, result);

    }

}
