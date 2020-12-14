/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Department;

import configuration.Database.ConnectionForTest;
import configuration.Database.DBAbstractFactory;
import configuration.Database.DBFactoryContext;
import configuration.Database.DBManager;
import configuration.Database.DBProduct;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Services.DepartmentService;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */
public class DepartmentServiceTestUpdate {

    private DepartmentService ds;
    private ConnectionForTest cft;
    private DBProduct dbProduct;

    public DepartmentServiceTestUpdate() {
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
     * Test of updateDepartment method, of class DepartmentService, updating an
     * existing Department with a new area.
     */
    @Test
    public void testUpdateDepartment() throws Exception {
        System.out.println("updateDepartment");
        String oldArea = "Fisciano - Molding";
        String newArea = "Fisciano - Base";
        int notExpResult = 0;
        int result = ds.updateDepartment(oldArea, newArea);
        assertNotEquals(result, notExpResult);

    }

    /**
     * Test of updateDepartment method, of class DepartmentService, updating an
     * existing Department with a new area that already exists.
     */
    @Test(expected = SQLException.class)
    public void testUpdateDepartment1() throws Exception {
        System.out.println("updateDepartment");
        String oldArea = "Fisciano - Molding";
        String newArea = "Nusco - Carpentry";
        int expResult = 0;
        int result = ds.updateDepartment(oldArea, newArea);
        assertEquals(result, expResult);

    }

    /**
     * Test of updateDepartment method, of class DepartmentService, updating an
     * invalid Department with a new area.
     */
    @Test(expected = UnsuccessfulUpdateException.class)
    public void testUpdateDepartment2() throws Exception {
        System.out.println("updateDepartment");
        String oldArea = "Fisciano - Center";
        String newArea = "Fisciano - Carpentry";
        int expResult = 0;
        int result = ds.updateDepartment(oldArea, newArea);
        assertEquals(result, expResult);

    }

    /**
     * Test of updateDepartment method, of class DepartmentService, updating a
     * valid Department with a new invalid area (empty).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateDepartment3() throws Exception {
        System.out.println("updateDepartment");
        String oldArea = "Fisciano - Molding";
        String newArea = null;
        int expResult = 0;
        int result = ds.updateDepartment(oldArea, newArea);
        assertEquals(result, expResult);

    }

    /**
     * Test of updateDepartment method, of class DepartmentService, updating a
     * valid Department with a new invalid area(length > 30).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateDepartment4() throws Exception {
        System.out.println("updateDepartment");
        String oldArea = "Fisciano - Molding";
        String newArea = "Domodossola - Activity Exchange Center";
        int expResult = 0;
        int result = ds.updateDepartment(oldArea, newArea);
        assertEquals(result, expResult);

    }

}
