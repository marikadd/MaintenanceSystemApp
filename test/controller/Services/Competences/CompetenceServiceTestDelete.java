/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Competences;

import controller.Services.CompetenceService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dondi
 */
public class CompetenceServiceTestDelete {
    
    private CompetenceService cps;
    
    @Before
    public void setUp() {
        cps = CompetenceService.getCompetenceService();
    }

    /**
     * Test of deleteCompetence method of class CompetenceService, deleting
     * a Competence passing its ID.
     */
    @Test
    public void testDeleteCompetence() throws Exception {
        System.out.println("deleteCompetence");
        Integer id = 7;
        int notExpResult = 0;
        int result = cps.deleteCompetence(id);
        assertNotEquals(result, notExpResult);
        
    }

    /**
     * Test of deleteCompetence method of class CompetenceService, deleting
     * Competence with an unexisting ID.
     */
    @Test
    public void testDeleteCompetence1() throws Exception {
        System.out.println("deleteCompetence");
        Integer id = 150;
        Integer expResult = 0;
        Integer result = cps.deleteCompetence(id);
        assertEquals(result, expResult);       
    }
    
}
