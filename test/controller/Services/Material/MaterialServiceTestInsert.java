/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Material;

import configuration.Database.ConnectionForTest;
import configuration.Exceptions.InvalidParameterObjectException;
import controller.Services.MaterialService;
import java.sql.SQLException;
import java.util.List;
import model.Material.Material;
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
public class MaterialServiceTestInsert {
     
    private MaterialService ms;
    private ConnectionForTest cft;
    
    
    public MaterialServiceTestInsert() {
    }
    
    @Before
    public void setUp() {
        ms = MaterialService.getMaterialService();
        cft = ConnectionForTest.init(); 
    }
    
    @After
    public void setAfter() {
        cft.rollbackConnection();
    }


    /**
     * Test of insertMaterial method, of class MaterialService, inserting a new material.
     */
    @Test
    public void testInsertMaterial() throws Exception {
        System.out.println("insertMaterial");
        String type = "Plastic";
        int expResult = 1;
        int result = ms.insertMaterial(type);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of insertMaterial method, of class MaterialService, inserting a material 
     * who already exists.
     */
    @Test(expected=SQLException.class)
    public void testInsertMaterial1() throws Exception {
        System.out.println("insertMaterial");
        String type = "Marble";
        int expResult = 0;
        int result = ms.insertMaterial(type);
        assertEquals(expResult, result);
    }
  
    /**
     * Test of insertMaterial method, of class MaterialService, inserting a new material with
     * an uncorrect type (empty).
     */
    @Test(expected=InvalidParameterObjectException.class)
    public void testInsertMaterial2() throws Exception {
        System.out.println("insertMaterial");
        String type = null;
        int expResult = 0;
        int result = ms.insertMaterial(type);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of insertMaterial method, of class MaterialService, inserting a new material with
     * an uncorrect type (lenght > 20).
     */
    @Test(expected=InvalidParameterObjectException.class)
    public void testInsertMaterial3() throws Exception {
        System.out.println("insertMaterial");
        String type = "Conductive material of heat and electricity";
        int expResult = 0;
        int result = ms.insertMaterial(type);
        assertEquals(expResult, result);
    }
    
    
}
