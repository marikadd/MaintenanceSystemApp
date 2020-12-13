/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Competence;

import configuration.Database.ConnectionForTest;
import configuration.Exceptions.UsernotFoundException;
import controller.Services.CompetenceService;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import model.Competences.Competence;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import model.Competences.CompetenceInterface;
import org.junit.After;

/**
 *
 * @author Group9
 */

public class CompetenceServiceTestGet {
    
    private CompetenceService cps;
    private ConnectionForTest cft;
    
    
    public CompetenceServiceTestGet() {
    }
       
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
     * Test of getAllCompetences method, of class CompetenceService, getting
     * all competences.
     */
    @Test
    public void testGetAllCompetences() throws Exception {
        System.out.println("getAllCompetences");
        List<Competence> list = cps.getAllCompetences();
        int result = list.size();
        int ExpectedResult = 4; 
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of getAllCompetenceTarget method, of class CompetenceService, getting
     * all competences of a valid Maintainer.(This method return the sum of competences assigned to the 
     * maintainer plus the number of activities that have not been assigned to him).
     */
    @Test
    public void testGetAllCompetenceTarget() throws Exception {
        System.out.println("getAllCompetenceTarget");
        String username = "mrossi";
        List<CompetenceInterface> list = new LinkedList<>();
        list = cps.getAllCompetenceTarget(username);
        int result = list.size();
        int ExpectedResult = 4; 
        assertEquals(result, ExpectedResult);
    }
    
    /**
     * Test of getAllCompetenceTarget method, of class CompetenceService, getting
     * all competences of an invalid Maintainer.
     */
    @Test(expected=UsernotFoundException.class)
    public void testGetAllCompetenceTarget1() throws Exception {
        System.out.println("getAllCompetenceTarget");
        String username = "lgiulio";
        List<CompetenceInterface> list = new LinkedList<>();
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
        List<CompetenceInterface> list = new LinkedList<>();
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
        String username = "fcerruti";
        List<CompetenceInterface> list = new LinkedList<>();
        list = cps.getAllCompetenceTarget(username);
        int result = getResultNumberFor(list, true);
        int expectedResult = 0;
        assertEquals(result, expectedResult);

    }
      
    /**
     * Test of getCommonSkills method, of class CompetenceService, getting how much competences
     * of those taken as input, has the maintainer.
     */
    @Test
    public void testGetCommonSkills() throws Exception {
        System.out.println("getCommonSkills");
        Competence cPAV = new Competence(1, "PAV Certification");
        Competence cMM = new Competence(2, "Mechanical Maintenance");
        List<Competence> activityComp = new LinkedList(Arrays.asList(cPAV, cMM));
        String username = "mrossi";
        String expResult = "1/2";
        String result = cps.getCommonSkills(activityComp, username);
        assertEquals(expResult, result);
    }
 
    /**
     * Test of getCommonSkills method, of class CompetenceService, getting how much competences
     * of those taken as input, has an user who is not a maintainer.
     */
    @Test(expected = UsernotFoundException.class)
    public void testGetCommonSkills1() throws Exception {
        System.out.println("getCommonSkills");
        Competence cPAV = new Competence(1, "PAV Certification");
        Competence cMM = new Competence(2, "Mechanical Maintenance");
        List<Competence> activityComp = new LinkedList(Arrays.asList(cPAV, cMM));
        String username = "lbianchi";
        String expResult = "0/2";
        String result = cps.getCommonSkills(activityComp, username);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of getCommonSkills method, of class CompetenceService, getting how much competences
     * of those taken as input, has an user who doesn't exist.
     */
    @Test(expected= UsernotFoundException.class)
    public void testGetCommonSkills2() throws Exception {
        System.out.println("getCommonSkills");
        Competence cPAV = new Competence(1, "PAV Certification");
        Competence cMM = new Competence(2, "Mechanical Maintenance");
        List<Competence> activityComp = new LinkedList(Arrays.asList(cPAV, cMM));
        String username = "mdelledonne";
        String expResult = null;
        String result = cps.getCommonSkills(activityComp, username);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAllSkills method, of class CompetenceService, getting all skills
     associated to the activity taken as an input.
     */
    @Test
    public void testGetAllSkills() throws Exception {
        System.out.println("getAllSkills");
        int activityID = 1;
        List<Competence> list = cps.getAllSkills(activityID);
        int result = list.size();
        int  expResult = 1;
        assertEquals(expResult, result);
    }
    
     /**
     * Test of getAllSkills method, of class CompetenceService, getting all skills
     associated to an unexisting activity taken as an input.
     */
    @Test
    public void testGetAllSkills1() throws Exception {
        System.out.println("getAllSkills");
        int activityID = 8;
        List<Competence> list = cps.getAllSkills(activityID);
        int result = list.size();
        int  expResult = 0;
        assertEquals(expResult, result);
    }
    
    
    private int getResultNumberFor(List<CompetenceInterface> list, boolean type) { 
        
        int count = 0;
        for(CompetenceInterface ct: list) {
            
            if(ct.isCompetenceLinked() == type) {
                count++;
            }
        }
        return count;
    }
    
}
