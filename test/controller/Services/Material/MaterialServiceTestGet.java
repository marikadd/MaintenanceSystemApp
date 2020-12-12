/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Material;

import configuration.Database.ConnectionForTest;
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
public class MaterialServiceTestGet {
    
       
    private MaterialService ms;
    private ConnectionForTest cft;
    
    
    public MaterialServiceTestGet() {
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
     * Test of getAllMaterials method, of class MaterialService, getting all materials..
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