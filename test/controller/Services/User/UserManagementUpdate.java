/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.User;

import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Services.UserManagementService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */

public class UserManagementUpdate {

    private UserManagementService ums;

    public UserManagementUpdate() {
    }

    @Before
    public void setUp() {

        ums = UserManagementService.getUserManagementService();

    }

    /**
     * Test of updateProdManager method, of class UserManagementService. Update
     * username,password,email,phone number of a ProdManager The operation of
     * update is equal for all roles
     */
    @Test
    public void testUpdateProdManager() throws Exception {
        System.out.println("updateProdManager");
        String oldUsername = "tcaio";
        String username = "tcaio2";
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
     * Test of updateSystemAdmin method, of class UserManagementService. Update
     * name and surname of a SystemAdmin
     */
    @Test(expected = UnsuccessfulUpdateException.class)
    public void testUpdateSystemAdmin() throws Exception {
        System.out.println("updateSystemAdmin");
        String oldUsername = "gdipaolo";
        String username = null;
        String password = null;
        String name = "Giovanna";
        String surname = "Esposito";
        String email = null;
        String phone = null;
        int result = ums.updateSystemAdmin(oldUsername, username, password, name, surname, email, phone);

        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of updateMaintainer method, of class UserManagementService. Update
     * username,password,email, phone number of a Maintainer, inserting old name
     * and surname
     */
    @Test
    public void testUpdateMaintainer() throws Exception {
        System.out.println("updateMaintainer");
        String oldUsername = "mdelledonne";
        String username = "Marikadd";
        String password = "Delledonne98@";
        String name = "Marika";
        String surname = "Delle Donne";
        String email = "marikadd@gmail.com";;
        String phone = "3334567111";
        int result = ums.updatePlanner(oldUsername, username, password, name, surname, email, phone);

        int ExpectedResult = 1;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of updateMaintainer method, of class UserManagementService. Update a
     * maintainer,searched by an old username that doesn't exist
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
