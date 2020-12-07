/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Department;

import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Services.DepartmentService;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */
public class DepartmentServiceTestUpdate {
    
    private DepartmentService ds;
    
    public DepartmentServiceTestUpdate() {
    }
    
    @Before
    public void setUp() {
        ds = DepartmentService.getDepartmentService();
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
        String oldArea = "Fisciano - Base";
        String newArea = "Arzano - Service"; 
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
     * valid Department with a new invalid area(empty).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateDepartment3() throws Exception {
        System.out.println("updateDepartment");
        String oldArea = "Fisciano - Base";
        String newArea = ""; 
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
        String oldArea = "Fisciano - Base";
        String newArea = "Domodossola - Activity Exchange Center"; 
        int expResult = 0;
        int result = ds.updateDepartment(oldArea, newArea);
        assertEquals(result, expResult);
        
    }
    
}
