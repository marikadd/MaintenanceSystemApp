package controller.Services.User;

import configuration.Database.ConnectionForTest;
import configuration.Database.DBAbstractFactory;
import configuration.Database.DBFactoryContext;
import configuration.Database.DBManager;
import configuration.Database.DBProduct;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
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
public class UserManagementTestUpdate {

    private UserManagementService ums;
    private ConnectionForTest cft;
    private DBProduct dbProduct;

    public UserManagementTestUpdate() {
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
     * Test of updateProdManager method, of class UserManagementService,
     * updating username, password, email, phone number of a ProdManager; The
     * operation of update is equal for all roles, for this reason test cases
     * are distributed among the various roles in a random way. (N.B: Name and
     * surname are null because it is not possible to modify them inside
     * updateProdManager method. However, in UserManagmentServicethe the method
     * updateProdManager takes as input name and surname because the
     * incapsulated DAO method needs an istance of UserModel (in this case
     * ProdManager).
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
     * Test of updateMaintainer method, of class UserManagementService, updating
     * username, password, email, phone number, name and surname of a Maintainer
     * by using old username (new name and surname are equal to the previous
     * ones).
     */
    @Test
    public void testUpdateMaintainer() throws Exception {
        System.out.println("updateMaintainer");
        String oldUsername = "mrossi";
        String username = "Matinodd";
        String password = "Delledonne98@";
        String name = "Mario";
        String surname = "Rossi";
        String email = "martinodd@gmail.com";
        String phone = "3334567111";
        int result = ums.updateMaintainer(oldUsername, username, password, name, surname, email, phone);
        int ExpectedResult = 1;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of updateMaintainer method, of class UserManagementService, updating
     * username, password, email, phone number, name and surname of a Maintainer
     * by inserting old username (new name and surname are not equal to the
     * previous ones, but this is not possible).
     */
    @Test(expected = UnsuccessfulUpdateException.class)
    public void testUpdateMaintainer1() throws Exception {
        System.out.println("updateMaintainer");
        String oldUsername = "mrossi";
        String username = "Matinodd";
        String password = "Delledonne98@";
        String name = "Martino";
        String surname = "Delle Donne";
        String email = "martinodd@gmail.com";;
        String phone = "3334567111";
        int result = ums.updateMaintainer(oldUsername, username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of updateMaintainer method, of class UserManagementService, updating
     * a maintainer, searched by an old username that doesn't exist.
     */
    @Test(expected = UnsuccessfulUpdateException.class)
    public void testUpdateMaintainer2() throws Exception {
        System.out.println("updateMaintainer");
        String oldUsername = "gmanc";
        String username = "Gmancone";
        String password = "Gmancone99@";
        String name = null;
        String surname = null;
        String email = "gmancone@gmail.com";;
        String phone = "3324567111";
        int result = ums.updateMaintainer(oldUsername, username, password, name, surname, email, phone);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of updateMaintainer method, of class UserManagementService, without
     * updating any parameter( With "coalesce" expression the row is changed to
     * the previous values in database, for this reason ExpectedResult = 1 ).
     */
    @Test(expected = UnsuccessfulUpdateException.class)
    public void testUpdateMaintainer3() throws Exception {
        System.out.println("updateMaintainer");
        String oldUsername = null;
        String username = null;
        String password = null;
        String name = null;
        String surname = null;
        String email = null;
        String phone = null;
        int result = ums.updateMaintainer(oldUsername, username, password, name, surname, email, phone);
        int ExpectedResult = 1;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of updateMaintainer method, of class UserManagementService, updating
     * username, password, email, phone number of a Maintainer (username doesn't
     * respect the length).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateMaintainer4() throws Exception {
        System.out.println("updateMaintainer");
        String oldUsername = "mrossi";
        String username = "TizianoLuigiCostanzo20";
        String password = "Ciaone!78@";
        String name = null;
        String surname = null;
        String email = "semp@outlook.com";
        String phone = "3392175666";
        int result = ums.updateMaintainer(oldUsername, username, password, name, surname, email, phone);
        int notExpectedResult = 0;
        assertNotEquals(result, notExpectedResult);
    }

    /**
     * Test of updateMaintainer method, of class UserManagementService, updating
     * username, password, email, phone number of a Maintainer (password doesn't
     * respect the format).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateMaintainer5() throws Exception {
        System.out.println("updateMaintainer");
        String oldUsername = "mrossi";
        String username = "MRossi98";
        String password = "Ciaone";
        String name = null;
        String surname = null;
        String email = "semp@outlook.com";
        String phone = "3392175666";
        int result = ums.updateMaintainer(oldUsername, username, password, name, surname, email, phone);
        int notExpectedResult = 0;
        assertNotEquals(result, notExpectedResult);
    }

    /**
     * Test of updateMaintainer method, of class UserManagementService, updating
     * username, password, email, phone number of a Maintainer (name doesn't
     * respect the length).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateMaintainer6() throws Exception {
        System.out.println("updateMaintainer");
        String oldUsername = "mrossi";
        String username = "MRossi98";
        String password = "Ciaone78@";
        String name = "Gabriele Antonio Carmelo";
        String surname = null;
        String email = "semp@outlook.com";
        String phone = "3392175666";
        int result = ums.updateMaintainer(oldUsername, username, password, name, surname, email, phone);
        int notExpectedResult = 0;
        assertNotEquals(result, notExpectedResult);
    }

    /**
     * Test of updateMaintainer method, of class UserManagementService, updating
     * username, password, email, phone number of a Maintainer (surname doesn't
     * respect the length).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateMaintainer7() throws Exception {
        System.out.println("updateMaintainer");
        String oldUsername = "mrossi";
        String username = "MRossi98";
        String password = "Ciaone78@";
        String name = null;
        String surname = "Esposito Chiacchiararelli";
        String email = "semp@outlook.com";
        String phone = "3392175666";
        int result = ums.updateMaintainer(oldUsername, username, password, name, surname, email, phone);
        int notExpectedResult = 0;
        assertNotEquals(result, notExpectedResult);
    }

    /**
     * Test of updateMaintainer method, of class UserManagementService, updating
     * username, password, email, phone number of a Maintainer (email doesn't
     * respect the length).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateMaintainer8() throws Exception {
        System.out.println("updateMaintainer");
        String oldUsername = "mrossi";
        String username = "MRossi98";
        String password = "Ciaone78@";
        String name = null;
        String surname = null;
        String email = "Saretta_Chiacchiarerelli_1998@outlook.com";
        String phone = "3392175666";
        int result = ums.updateMaintainer(oldUsername, username, password, name, surname, email, phone);
        int notExpectedResult = 0;
        assertNotEquals(result, notExpectedResult);
    }

    /**
     * Test of updateMaintainer method, of class UserManagementService, updating
     * username, password, email, phone number of a Maintainer (email doesn't
     * respect the format).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateMaintainer9() throws Exception {
        System.out.println("updateMaintainer");
        String oldUsername = "mrossi";
        String username = "MRossi98";
        String password = "Ciaone78@";
        String name = null;
        String surname = null;
        String email = "sara98gmail.com";
        String phone = "3392175666";
        int result = ums.updateMaintainer(oldUsername, username, password, name, surname, email, phone);
        int notExpectedResult = 0;
        assertNotEquals(result, notExpectedResult);
    }

    /**
     * Test of updateMaintainer method, of class UserManagementService, updating
     * username, password, email, phone number of a Maintainer (phone number
     * doesn't respect the length beacuse is less than 10).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateMaintainer10() throws Exception {
        System.out.println("updateMaintainer");
        String oldUsername = "mrossi";
        String username = "MRossi98";
        String password = "Ciaone78@";
        String name = null;
        String surname = null;
        String email = "sara98@gmail.com";
        String phone = "012345678";
        int result = ums.updateMaintainer(oldUsername, username, password, name, surname, email, phone);
        int notExpectedResult = 0;
        assertNotEquals(result, notExpectedResult);
    }

    /**
     * Test of updateMaintainer method, of class UserManagementService, updating
     * username, password, email, phone number of a Maintainer (phone number
     * doesn't respect the length beacuse is more than 10).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateMaintainer11() throws Exception {
        System.out.println("updateMaintainer");
        String oldUsername = "mrossi";
        String username = "MRossi98";
        String password = "Ciaone78@";
        String name = null;
        String surname = null;
        String email = "sara98@gmail.com";
        String phone = "0123456789110";
        int result = ums.updateMaintainer(oldUsername, username, password, name, surname, email, phone);
        int notExpectedResult = 0;
        assertNotEquals(result, notExpectedResult);
    }

    /**
     * Test of updateMaintainer method, of class UserManagementService, updating
     * username, password, email, phone number of a Maintainer (phone number
     * doesn't respect the format).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateMaintainer12() throws Exception {
        System.out.println("updateMaintainer");
        String oldUsername = "mrossi";
        String username = "MRossi98";
        String password = "Ciaone78@";
        String name = null;
        String surname = null;
        String email = "sara98@gmail.com";
        String phone = "ciao";
        int result = ums.updateMaintainer(oldUsername, username, password, name, surname, email, phone);
        int notExpectedResult = 0;
        assertNotEquals(result, notExpectedResult);
    }
}
