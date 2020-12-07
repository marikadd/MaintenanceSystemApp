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
import java.sql.SQLException;

/**
 *
 * @author dondi
 */
public class CompetenceServiceTestInsert {
    
    private CompetenceService cps;
    
    @Before
    public void setUp() {
        cps = CompetenceService.getCompetenceService();
    }


    /**
     * Test of insertCompetence method, inserting a new Competence
     * with his description.
     */
    @Test
    public void testInsertCompetence() throws Exception {
        System.out.println("insertCompetence");
        String description = "Repair Tubes";       
        int result = cps.insertCompetence(description);
        int notExpectedResult = 0;
        assertNotEquals(result, notExpectedResult);
       
    }
    
    /**
     * Test of insertCompetence method, inserting a Competence
     * with a description that already exists.
     */
    @Test(expected=SQLException.class)
    public void testInsertCompetence1() throws Exception {
        System.out.println("insertCompetence");
        String description = "Repair Tubes";
        
        int result = cps.insertCompetence(description);
        int expectedResult = 0;
        assertNotEquals(result, expectedResult);
        
    }
    
    /**
     * Test of insertCompetence method, inserting a Competence
     * with an invalid description (empty).
     */
    @Test
    public void testInsertCompetence2() throws Exception {
        System.out.println("insertCompetence");
        String description = "";
        
        int result = cps.insertCompetence(description);
        int expectedResult = 0;
        assertEquals(result, expectedResult);
        
    }
    /**
     * Test of insertCompetence method, inserting a Competence
     * with an invalid description (lenght>50).
     */
    @Test(expected=SQLException.class)
    public void testInsertCompetence3() throws Exception {
        System.out.println("insertCompetence");
        String description = "Samalamadumaloomayoureassumingimahumanwhatigottadotogetitthroughtoyouimasuperhuman";
        
        int result = cps.insertCompetence(description);
        int expectedResult = 0;
        assertNotEquals(result, expectedResult);
        
    }
    
}
