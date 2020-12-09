/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Competence;

import configuration.Database.ConnectionForTest;
import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Services.CompetenceService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */

public class CompetenceServiceTestDelete {
    
    private CompetenceService cps;
     private ConnectionForTest cft;
    
    @Before
    public void setUp() {
        cps = CompetenceService.getCompetenceService();
        cft = ConnectionForTest.init(); 
    }
    
    @After
    public void setAfter() {
        cft.rollbackConnection();
    }
    
    /**
     * Test of deleteCompetence method of class CompetenceService, deleting
     * a Competence passing its ID.
     */
    @Test
    public void testDeleteCompetence() throws Exception {
        System.out.println("deleteCompetence");
        Integer id = 153; //id che esiste nel database
        int ExpResult = 1;
        int result = cps.deleteCompetence(id);
        assertEquals(result, ExpResult);
    }

    /**
     * Test of deleteCompetence method of class CompetenceService, deleting
     * Competence with an unexisting ID.
     */
    @Test(expected=UnsuccessfulUpdateException.class)
    public void testDeleteCompetence1() throws Exception {
        System.out.println("deleteCompetence");
        Integer id = 150; //id che non esiste nel database
        Integer expResult = 0;
        Integer result = cps.deleteCompetence(id);
        assertEquals(result, expResult);       
    } 
}
