/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.User;

import configuration.Database.ConnectionForTest;
import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Services.UserManagementService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */

public class UserManagementTestUpdate {

    private UserManagementService ums;
    private ConnectionForTest cft;

    public UserManagementTestUpdate() {
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
     * Test of updateProdManager method, of class UserManagementService, updating
     * username,password,email,phone number of a ProdManager; The operation of
     * update is equal for all roles.
     */
    @Test
    public void testUpdateProdManager() throws Exception {
        System.out.println("updateProdManager");
        String oldUsername = "lfermi";
        String username = "tcaio";
        String password = "Ciaone!78@";
        String name = null;
        String surname = null;
        String email = "semp@outlook.com";
        String phone = "3392175666";
        int result = ums.updateProdManager(oldUsername, username, password, name, surname, email, phone);
        int notExpectedResult = 0;
        assertNotEquals(result, notExpectedResult);
    }

    /**
     * Test of updateSystemAdmin method, of class UserManagementService, updating 
     * name and surname of a SystemAdmin (it is not possible).
     */
    @Test(expected = UnsuccessfulUpdateException.class)
    public void testUpdateSystemAdmin() throws Exception {
        System.out.println("updateSystemAdmin");
        String oldUsername = "gcoppola";
        String username = null;
        String password = null;
        String name = "Giovanni";
        String surname = "Esposito";
        String email = null;
        String phone = null;
        int result = ums.updateSystemAdmin(oldUsername, username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of updateMaintainer method, of class UserManagementService, updating
     * username,password,email, phone number of a Maintainer by inserting old name
     * and surname.
     */
    @Test
    public void testUpdateMaintainer() throws Exception {
        System.out.println("updateMaintainer");
        String oldUsername = "mrossi";
        String username = "Matinodd";
        String password = "Delledonne98@";
        String name = "Martino";
        String surname = "Delle Donne";
        String email = "martinodd@gmail.com";;
        String phone = "3334567111";
        int result = ums.updatePlanner(oldUsername, username, password, name, surname, email, phone);
        int ExpectedResult = 1;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of updateMaintainer method, of class UserManagementService, updating a
     * maintainer, searched by an old username that doesn't exist.
     */
    @Test(expected = UnsuccessfulUpdateException.class)
    public void testUpdateMaintainer1() throws Exception {
        System.out.println("updateMaintainer");
        String oldUsername = "gmanc";
        String username = "Gmancone";
        String password = "Gmancone99@";
        String name = null;
        String surname = null;
        String email = "gmancone@gmail.com";;
        String phone = "3324567111";
        int result = ums.updatePlanner(oldUsername, username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

} 
