/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Department;

import configuration.Exceptions.InvalidParameterObjectException;
import controller.Services.DepartmentService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;

/**
 *
 * @author Group9
 */

public class DepartmentServiceTestInsert {
    
    private DepartmentService ds;
    
    public DepartmentServiceTestInsert() {
    }
    
    @Before
    public void setUp() {
        ds = DepartmentService.getDepartmentService();
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
    @Test(expected=SQLException.class)
    public void testInsertDepartment1() throws Exception {
        System.out.println("insertDepartment");
        String area = "Arzano - Service";
        int expResult = 0;
        int result = ds.insertDepartment(area);
        assertEquals(result, expResult);
        
    }
    
    /**
     * Test of insertDepartment method, of class DepartmentService, inserting an
     * invalid Department(empty). 
     */
    @Test(expected=InvalidParameterObjectException.class)
    public void testInsertDepartment2() throws Exception {
        System.out.println("insertDepartment");
        String area = "";
        int expResult = 0;
        int result = ds.insertDepartment(area);
        assertEquals(result, expResult);
        
    }
    
    /**
     * Test of insertDepartment method, of class DepartmentService, inserting an
     * invalid Department(length>30). 
     */
    @Test(expected=InvalidParameterObjectException.class)
    public void testInsertDepartment3() throws Exception {
        System.out.println("insertDepartment");
        String area = "Domodossola - Activity Exchange Center";
        int expResult = 0;
        int result = ds.insertDepartment(area);
        assertEquals(result, expResult);
        
    }
    
}
