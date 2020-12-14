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
import controller.Services.DepartmentService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.After;

/**
 *
 * @author Group9
 */
public class DepartmentServiceTestInsert {

    private DepartmentService ds;
    private ConnectionForTest cft;
    private DBProduct dbProduct;

    public DepartmentServiceTestInsert() {
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
     * Test of insertDepartment method, of class DepartmentService, inserting a
     * new valid Department.
     */
    @Test
    public void testInsertDepartment() throws Exception {
        System.out.println("insertDepartment");
        String area = "Arzano - Service";
        int notExpResult = 0;
        int result = ds.insertDepartment(area);
        assertNotEquals(result, notExpResult);

    }

    /**
     * Test of insertDepartment method, of class DepartmentService, inserting a
     * Department who already exists.
     */
    @Test(expected = SQLException.class)
    public void testInsertDepartment1() throws Exception {
        System.out.println("insertDepartment");
        String area = "Fisciano - Molding";
        int expResult = 0;
        int result = ds.insertDepartment(area);
        assertEquals(result, expResult);

    }

    /**
     * Test of insertDepartment method, of class DepartmentService, inserting an
     * invalid Department(empty).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertDepartment2() throws Exception {
        System.out.println("insertDepartment");
        String area = null;
        int expResult = 0;
        int result = ds.insertDepartment(area);
        assertEquals(result, expResult);

    }

    /**
     * Test of insertDepartment method, of class DepartmentService, inserting an
     * invalid Department(length > 30).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertDepartment3() throws Exception {
        System.out.println("insertDepartment");
        String area = "Domodossola - Activity Exchange Center";
        int expResult = 0;
        int result = ds.insertDepartment(area);
        assertEquals(result, expResult);
 
    }

}
