/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Competences;

import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Services.CompetenceService;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dondi
 */
public class CompetenceServiceTestUpdate {
   
    private CompetenceService cps;
    
    @Before
    public void setUp() {
        cps = CompetenceService.getCompetenceService();
    }
   
    /**
     * Test of updateCompetence method, of class CompetenceService, updating
     * an existing Competence with a valid description.
     */
    @Test
    public void testUpdateCompetence() throws Exception {
        System.out.println("updateCompetence");
        Integer id = 5;
        String newDescription = "Repair Broken Tubes";
     
        int notExpResult = 0;
        int result = cps.updateCompetence(id, newDescription);
        assertNotEquals(result, notExpResult);
    
    }
    
    /**
     * Test of updateCompetence method, of class CompetenceService, updating
     * an invalid Competence with a valid description.
     */
    @Test(expected=UnsuccessfulUpdateException.class)
    public void testUpdateCompetence1() throws Exception {
        System.out.println("updateCompetence");
        Integer id = 150;
        String newDescription = "Repair Computers";
     
        int expResult = 0;
        int result = cps.updateCompetence(id, newDescription);
        assertEquals(result, expResult);
    
    }
    
    /**
     * Test of updateCompetence method, of class CompetenceService, updating
     * an invalid Competence with an invalid description.
     */
    @Test(expected=SQLException.class)
    public void testUpdateCompetence2() throws Exception {
        System.out.println("updateCompetence");
        Integer id = 5;
        String newDescription = "Samaloomadumaloomayoureassumingimahumanwhatigottadotogetitthroughtoyouimasuperhuman";
     
        int expResult = 0;
        int result = cps.updateCompetence(id, newDescription);
        assertEquals(result, expResult);
    
    }

    
}
