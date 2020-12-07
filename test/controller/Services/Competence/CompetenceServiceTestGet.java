/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Competence;

import configuration.Exceptions.UsernotFoundException;
import controller.Services.CompetenceService;
import java.util.LinkedList;
import java.util.List;
import model.Competences.Competence;
import model.Competences.CompetenceTarget;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */

public class CompetenceServiceTestGet {
    
    private CompetenceService cps;
    
    public CompetenceServiceTestGet() {
    }
       
    @Before
    public void setUp() {
        
        cps = CompetenceService.getCompetenceService();
    }

    /**
     * Test of getAllCompetences method, of class CompetenceService.
     */
    @Test
    public void testGetAllCompetences() throws Exception {
        System.out.println("getAllCompetences");
        
        List<Competence> list = cps.getAllCompetences();
        int result = list.size();
        
        int ExpectedResult = 5;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of getAllCompetenceTarget method, of class CompetenceService, getting
     * all competences of a valid Maintainer.
     */
    @Test
    public void testGetAllCompetenceTarget() throws Exception {
        System.out.println("getAllCompetenceTarget");
        String username = "mrossi";
   
        List<CompetenceTarget> list = new LinkedList<>();
        list = cps.getAllCompetenceTarget(username);
        
        int result = list.size();
        int ExpectedResult = 2;
        assertEquals(result, ExpectedResult);
    }
    
    /**
     * Test of getAllCompetenceTarget method, of class CompetenceService, getting
     * all competences of an invalid Maintainer.
     */
    @Test(expected=UsernotFoundException.class)
    public void testGetAllCompetenceTarget1() throws Exception {
        System.out.println("getAllCompetenceTarget");
        String username = "giulio";
   
        List<CompetenceTarget> list = new LinkedList<>();
        list = cps.getAllCompetenceTarget(username);
        
        int result = list.size();
        int expectedResult = 0;
        assertEquals(result, expectedResult);
    }
    
    /**
     * Test of getAllCompetenceTarget method, of class CompetenceService, getting
     * all competences of an invalid User(not a Maintainer).
     */
    @Test(expected=UsernotFoundException.class)
    public void testGetAllCompetenceTarget2() throws Exception {
        System.out.println("getAllCompetenceTarget");
        String username = "lbianchi";
   
        List<CompetenceTarget> list = new LinkedList<>();
        list = cps.getAllCompetenceTarget(username);
        
        int result = list.size();
        int expectedResult = 0;
        assertEquals(result, expectedResult);
    }
    
    /**
     * Test of getAllCompetenceTarget method, of class CompetenceService, getting
     * all competences of a valid Maintainer with no competences.
     */
    @Test
    public void testGetAllCompetenceTarget3() throws Exception {
        System.out.println("getAllCompetenceTarget");
        String username = "tcaio";
   
        List<CompetenceTarget> list = new LinkedList<>();
        list = cps.getAllCompetenceTarget(username);
        
        int result = getResultNumberFor(list, true);
        int expectedResult = 0;
        assertEquals(result, expectedResult);

    }
    
    private int getResultNumberFor(List<CompetenceTarget> list, boolean type) {
        
        int count = 0;
        for(CompetenceTarget ct: list) {
            
            if(ct.isCompetenceLinked() == type) {
                count++;
            }
        }
        return count;
    }
    
}
