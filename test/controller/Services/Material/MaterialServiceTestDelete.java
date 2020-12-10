/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Material;

import configuration.Database.ConnectionForTest;
import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Services.MaterialService;
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
public class MaterialServiceTestDelete {
    
    private MaterialService ms;
    private ConnectionForTest cft;
    
    public MaterialServiceTestDelete() {
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
     * Test of deleteMaterial method, of class MaterialService, deleting an existing material.
     */
    @Test
    public void testDeleteMaterial() throws Exception {
        System.out.println("deleteMaterial");
        String type = "Marble";
        int expResult = 1;
        int result = ms.deleteMaterial(type);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of deleteMaterial method, of class MaterialService, deleting a material with
     * an unexisting type.
     */
    @Test(expected=UnsuccessfulUpdateException.class)
    public void testDeleteMaterial1() throws Exception {
        System.out.println("deleteMaterial");
        String type = "Steel";
        int expResult = 0;
        int result = ms.deleteMaterial(type);
        assertEquals(expResult, result);
    }
    
    
}
