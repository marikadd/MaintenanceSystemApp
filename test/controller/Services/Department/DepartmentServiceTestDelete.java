package controller.Services.Department;

import configuration.Database.ConnectionForTest;
import configuration.Database.DBAbstractFactory;
import configuration.Database.DBFactoryContext;
import configuration.Database.DBManager;
import configuration.Database.DBProduct;
import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Services.DepartmentService;
import java.util.List;
import model.Department.Department;
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
public class DepartmentServiceTestDelete {

    private DepartmentService ds;
    private ConnectionForTest cft;
    private DBProduct dbProduct;

    public DepartmentServiceTestDelete() {
    }

    @Before
    public void setUp() {
        ds = DepartmentService.getDepartmentService();
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
     * Test of deleteDepartment method, of class DepartmentService, deleting an
     * existing Department.
     */
    @Test
    public void testDeleteDepartment() throws Exception {
        System.out.println("deleteDepartment");
        String area = "Fisciano - Molding";
        int notExpResult = 0;
        int result = ds.deleteDepartment(area);
        assertNotEquals(result, notExpResult);
    }

    /**
     * Test of deleteDepartment method, of class DepartmentService, deleting a
     * Department with an unexisting area.
     */
    @Test(expected = UnsuccessfulUpdateException.class)
    public void testDeleteDepartment1() throws Exception {
        System.out.println("deleteDepartment");
        String area = "Viareggio - Service";
        int expResult = 0;
        int result = ds.deleteDepartment(area);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteDepartment method, of class DepartmentService, deleting a
     * Department with an unexisting area (empty).
     */
    @Test(expected = UnsuccessfulUpdateException.class)
    public void testDeleteDepartment2() throws Exception {
        System.out.println("deleteDepartment");
        String area = null;
        int expResult = 0;
        int result = ds.deleteDepartment(area);
        assertEquals(expResult, result);
    }

}
