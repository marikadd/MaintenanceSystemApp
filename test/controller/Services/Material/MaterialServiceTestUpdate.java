package controller.Services.Material;

import configuration.Database.ConnectionForTest;
import configuration.Database.DBAbstractFactory;
import configuration.Database.DBFactoryContext;
import configuration.Database.DBManager;
import configuration.Database.DBProduct;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Services.CompetenceService;
import controller.Services.MaterialService;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */
public class MaterialServiceTestUpdate {

    private MaterialService ms;
    private ConnectionForTest cft;
    private DBProduct dbProduct;

    public MaterialServiceTestUpdate() {
    }

    @Before
    public void setUp() {
        ms = MaterialService.getMaterialService();
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
     * Test of updateMaterial method, of class MaterialService, updating an
     * exisiting material with a new type.
     */
    @Test
    public void testUpdateMaterial() throws Exception {
        System.out.println("updateMaterial");
        String oldType = "Marble";
        String newType = "Plastic";
        int expResult = 1;
        int result = ms.updateMaterial(oldType, newType);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateMaterial method, of class MaterialService, updating an
     * exisiting material with a new type that already exists.
     */
    @Test(expected = SQLException.class)
    public void testUpdateMaterial1() throws Exception {
        System.out.println("updateMaterial");
        String oldType = "Marble";
        String newType = "Wood";
        int expResult = 0;
        int result = ms.updateMaterial(oldType, newType);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateMaterial method, of class MaterialService, updating an
     * unexisting material with a new type.
     */
    @Test(expected = UnsuccessfulUpdateException.class)
    public void testUpdateMaterial2() throws Exception {
        System.out.println("updateMaterial");
        String oldType = "Plastic";
        String newType = "Steel";
        int expResult = 0;
        int result = ms.updateMaterial(oldType, newType);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateMaterial method, of class MaterialService, updating an
     * existing material with a new invalid type (empty).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateMaterial3() throws Exception {
        System.out.println("updateMaterial");
        String oldType = "Wood";
        String newType = null;
        int expResult = 0;
        int result = ms.updateMaterial(oldType, newType);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateMaterial method, of class MaterialService, updating an
     * existing material with a new invalid type (length > 20).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateMaterial4() throws Exception {
        System.out.println("updateMaterial");
        String oldType = "Wood";
        String newType = "Conductive material of heat and electricity";
        int expResult = 0;
        int result = ms.updateMaterial(oldType, newType);
        assertEquals(expResult, result);
    }

}