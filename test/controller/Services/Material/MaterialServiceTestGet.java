package controller.Services.Material;

import configuration.Database.ConnectionForTest;
import configuration.Database.DBAbstractFactory;
import configuration.Database.DBFactoryContext;
import configuration.Database.DBManager;
import configuration.Database.DBProduct;
import controller.Services.MaterialService;
import java.util.List;
import model.Material.Material;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */
public class MaterialServiceTestGet {

    private MaterialService ms;
    private ConnectionForTest cft;
    private DBProduct dbProduct;

    public MaterialServiceTestGet() {
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
     * Test of getAllMaterials method, of class MaterialService, getting all
     * materials.
     */
    @Test
    public void testGetAllMaterials() throws Exception {
        System.out.println("getAllMaterials");
        int expResult = 3;
        List<Material> list = ms.getAllMaterials();
        int result = list.size();
        assertEquals(expResult, result);
    }
}
