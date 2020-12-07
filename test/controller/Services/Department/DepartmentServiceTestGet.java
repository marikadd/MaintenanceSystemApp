/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Department;

import configuration.Exceptions.DepartmentnotFoundException;
import controller.Services.DepartmentService;
import java.sql.SQLException;
import java.util.List;
import model.Department.Department;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */
public class DepartmentServiceTestGet {
    
    private DepartmentService ds;
    
    public DepartmentServiceTestGet() {
    }
    
    @Before
    public void setUp() {
        ds = DepartmentService.getDepartmentService();
    }

    /**
     * Test of getAllDepartments method, of class DepartmentService.
     */
    @Test
    public void testGetAllDepartments() throws Exception {
        System.out.println("getAllDepartments");
        int expResult = 2;
        List<Department> list = ds.getAllDepartments();
        int result = list.size();
        assertEquals(result, expResult);

    }

    /**
     * Test of getDepartment method, of class DepartmentService, getting
     * the area from a valid Department.
     */
    @Test
    public void testGetDepartment() throws Exception {
        System.out.println("getDepartment");       
        String area = "Fisciano - Molding";       
        String result = ds.getDepartment(area).getArea();
        
        assertEquals(result, area);
        
    }
    
    /**
     * Test of getDepartment method, of class DepartmentService, getting
     * the area from an invalid Department.
     */
    @Test(expected = DepartmentnotFoundException.class)
    public void testGetDepartment1() throws Exception {
        System.out.println("getDepartment");       
        String area = "Milano - Foundries";       
        Department dep = ds.getDepartment(area);     
        
    }
    
}
