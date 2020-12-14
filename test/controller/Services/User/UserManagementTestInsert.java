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
import configuration.Exceptions.InvalidParameterObjectException;
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
public class UserManagementTestInsert {

    private UserManagementService ums;
    private ConnectionForTest cft;
    private DBProduct dbProduct;

    public UserManagementTestInsert() {
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
     * Test of insertPlanner method, of class UserManagementService, inserting a
     * Planner with correct parameters; The operation of insert is equal for all
     * roles, for this reason test cases are distributed among the various roles
     * in a random way.
     */
    @Test
    public void testInsertPlanner() throws Exception {
        System.out.println("insertPlanner");
        String username = "icantalupo";
        String password = "Irmacanta98@";
        String name = "Irma";
        String surname = "Cantalupo";
        String email = "irma98@gmail.com";
        String phone = "3334565336";
        int result = ums.insertPlanner(username, password, name, surname, email, phone);
        int notExpectedResult = 0;
        assertNotEquals(result, notExpectedResult);
    }

    /**
     * Test of insertProdManager method, of class UserManagementService,
     * inserting a ProdManager without username.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertProdManager() throws Exception {
        System.out.println("insertProdManager");
        String username = null;
        String password = "Ciaociao0@";
        String name = "Tino";
        String surname = "Costanzo";
        String email = "tcostanzo@gmail.com";
        String phone = "20987654321";
        int result = ums.insertProdManager(username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);

    }

    /**
     * Test of insertProdManager method, of class UserManagementService,
     * inserting a ProdManager whose username doesn't respect length.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertProdManager1() throws Exception {
        System.out.println("insertProdManager");
        String username = "TizianoLuigiCostanzo20";
        String password = "Ciaociao0@";
        String name = "Tino";
        String surname = "Costanzo";
        String email = "tcostanzo@gmail.com";
        String phone = "20987654321";
        int result = ums.insertProdManager(username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);

    }

    /**
     * Test of insertProdManager method, of class UserManagementService,
     * inserting a PrdoManager whose username already exists.
     */
    @Test(expected = SQLException.class)
    public void testInsertProdManager2() throws Exception {
        System.out.println("insertPlanner");
        String username = "lbianchi";
        String password = "Giardino9@";
        String name = "Luca";
        String surname = "Bianchi";
        String email = "gerardo98@gmail.com";
        String phone = "3334565344";
        int result = ums.insertProdManager(username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of insertPlanner method, of class UserManagementService, inserting a
     * Planner without name.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertPlanner1() throws Exception {
        System.out.println("insertPlanner");
        String username = "imma881";
        String password = "Giocattolo8@";
        String name = null;
        String surname = "Cantalupo";
        String email = "immac881@gmail.com";
        String phone = "3334565314";
        int result = ums.insertPlanner(username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of insertPlanner method, of class UserManagementService, inserting a
     * Planner whose name doesn't respect length.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertPlanner2() throws Exception {
        System.out.println("insertPlanner");
        String username = "imma881";
        String password = "Giocattolo8@";
        String name = "Immacolata Anna Carmela";
        String surname = "Esposito";
        String email = "imma881@gmail.com";
        String phone = "3334565314";
        int result = ums.insertPlanner(username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of insertPlanner method, of class UserManagementService, inserting a
     * Planner without surname.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertPlanner3() throws Exception {
        System.out.println("insertPlanner");
        String username = "imma881";
        String password = "Giocattolo8@";
        String name = "Imma";
        String surname = null;
        String email = "immac881@gmail.com";
        String phone = "3334565314";
        int result = ums.insertPlanner(username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of insertPlanner method, of class UserManagementService, inserting a
     * Planner whose surname doesn't respect length.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertPlanner4() throws Exception {
        System.out.println("insertPlanner");
        String username = "imma881";
        String password = "Giocattolo8@";
        String name = "Immacolata";
        String surname = "Esposito Chiacchiararelli";
        String email = "imma881@gmail.com";
        String phone = "3334565314";
        int result = ums.insertPlanner(username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of insertSystemAdmin method, of class UserManagementService,
     * inserting a SystemAdmin without password.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertSystemAdmin1() throws Exception {
        System.out.println("insertSystemAdmin");
        String username = "giovanni87";
        String password = null;
        String name = "Giovanni";
        String surname = "Turco";
        String email = "gturco@gmail.com";
        String phone = "3334567424";
        int result = ums.insertSystemAdmin(username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);

    }

    /**
     * Test of insertSystemAdmin method, of class UserManagementService,
     * inserting a SystemAdmin whose password doesn't respect format.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertSystemAdmin2() throws Exception {
        System.out.println("insertSystemAdmin");
        String username = "giovanni87";
        String password = "ciao98";
        String name = "Giovanni";
        String surname = "Turco";
        String email = "gturco@gmail.com";
        String phone = "3334567424";
        int result = ums.insertSystemAdmin(username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);

    }

    /**
     * Test of insertMaintainer method, of class UserManagementService,
     * inserting a Maintainer whitout phone number.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertMaintainer1() throws Exception {
        System.out.println("insertMaintainer");
        String username = "Maria98";
        String password = "Mariacosta98@";
        String name = "Maria";
        String surname = "Costa";
        String email = "mariacosta@gmail.com";;
        String phone = null;
        int result = ums.insertMaintainer(username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of insertMaintainer method, of class UserManagementService,
     * inserting a Maintainer whose phone number is more than 10 numbers.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertMaintainer2() throws Exception {
        System.out.println("insertMaintainer");
        String username = "Maria98";
        String password = "Mariacosta98@";
        String name = "Maria";
        String surname = "Costa";
        String email = "mariacosta@gmail.com";;
        String phone = "12345678910";
        int result = ums.insertMaintainer(username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of insertMaintainer method, of class UserManagementService,
     * inserting a Maintainer whose phone number is less than 10 numbers.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertMaintainer3() throws Exception {
        System.out.println("insertMaintainer");
        String username = "Maria98";
        String password = "Mariacosta98@";
        String name = "Maria";
        String surname = "Costa";
        String email = "mariacosta@gmail.com";;
        String phone = "123456789";
        int result = ums.insertMaintainer(username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of insertMaintainer method, of class UserManagementService,
     * inserting a Maintainer whose phone number doesn't respect the format.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertMaintainer4() throws Exception {
        System.out.println("insertMaintainer");
        String username = "Maria98";
        String password = "Mariacosta98@";
        String name = "Maria";
        String surname = "Costa";
        String email = "mariacosta@gmail.com";;
        String phone = "ciao";
        int result = ums.insertMaintainer(username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of insertMaintainer method, of class UserManagementService,
     * inserting a Maintainer without email.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertMaintainer5() throws Exception {
        System.out.println("insertMaintainer");
        String username = "sara98";
        String password = "Saretta98@";
        String name = "Sara";
        String surname = "Rossi";
        String email = null;
        String phone = "3345645778";
        int result = ums.insertMaintainer(username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);

    }

    /**
     * Test of insertMaintainer method, of class UserManagementService,
     * inserting a Maintainer whose email already exists.
     */
    @Test(expected = SQLException.class)
    public void testInsertMaintainer6() throws Exception {
        System.out.println("insertMaintainer");
        String username = "sara98";
        String password = "Saretta98@";
        String name = "Sara";
        String surname = "Rossi";
        String email = "lbianchi@gmail.it";
        String phone = "3345645778";
        int result = ums.insertMaintainer(username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of insertMaintainer method, of class UserManagementService,
     * inserting a Maintainer whose email is more than 40 characters.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertMaintainer7() throws Exception {
        System.out.println("insertMaintainer");
        String username = "sara98";
        String password = "Saretta98@";
        String name = "Sara";
        String surname = "Chiacchiarerelli";
        String email = "Saretta_Chiacchiarerelli_1998@outlook.com";
        String phone = "3345645778";
        int result = ums.insertMaintainer(username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of insertMaintainer method, of class UserManagementService,
     * inserting a Maintainer whose email doesn't respect format.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertMaintainer8() throws Exception {
        System.out.println("insertMaintainer");
        String username = "sara98";
        String password = "Saretta98@";
        String name = "Sara";
        String surname = "Rossi";
        String email = "sara98gmail.com";
        String phone = "3345645778";
        int result = ums.insertMaintainer(username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

}
