/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Competence;

import configuration.Database.ConnectionForTest;
import configuration.Exceptions.UsernotFoundException;
import controller.Services.CompetenceService;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */
public class CompetenceServiceTestAssignCompetence {
    
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
     * Test of assignCompetence method, of class CompetenceService, assigning
     * an existing Competence to a valid Maintainer.
     */
    @Test
    public void testAssignCompetence() throws Exception {
        System.out.println("assignCompetence");
        String usernameMain = "mrossi";
        List<Integer> listId = new LinkedList<>();
        listId.add(3);
        int notExpResult = 0;
        int result = cps.assignCompetence(usernameMain, listId);
        assertNotEquals(result, notExpResult);       
    }
    
    /**
     * Test of assignCompetence method, of class CompetenceService, assigning 
     * an existing Competence to an invalid Maintainer.
     */
    @Test(expected=UsernotFoundException.class)
    public void testAssignCompetence1() throws Exception {
        System.out.println("assignCompetence");
        String usernameMain = "giulio";
        List<Integer> listId = new LinkedList<>();
        listId.add(1);
        int expResult = 0;
        int result = cps.assignCompetence(usernameMain, listId);
        assertEquals(result, expResult);       
    }
    
    /**
     * Test of assignCompetence method, of class CompetenceService, assigning 
     * an existing Competence to an invalid User(not a Maintainer).
     */
    @Test(expected=UsernotFoundException.class)
    public void testAssignCompetence2() throws Exception {
        System.out.println("assignCompetence");
        String usernameMain = "lbianchi";
        List<Integer> listId = new LinkedList<>();
        listId.add(1);
        int expResult = 0;
        int result = cps.assignCompetence(usernameMain, listId);
        assertEquals(result, expResult);       
    }
    
    /**
     * Test of assignCompetence method, of class CompetenceService, assigning 
     * an invalid Competence to a valid Maintainer.
     */
    @Test(expected=SQLException.class)
    public void testAssignCompetence3() throws Exception {
        System.out.println("assignCompetence");
        String usernameMain = "mrossi";
        List<Integer> listId = new LinkedList<>();
        listId.add(15); 
        int expResult = 0;
        int result = cps.assignCompetence(usernameMain, listId);
        assertEquals(result, expResult);       
    }

    /**
     * Test of assignCompetence method, of class CompetenceService, assigning 
     * an existing Competence to a valid Maintainer, who already has that Competence.
     */
    @Test(expected=SQLException.class)
    public void testAssignCompetence4() throws Exception {
        System.out.println("assignCompetence");
        String usernameMain = "mrossi";
        List<Integer> listId = new LinkedList<>();
        listId.add(1);
        int ExpResult = 0;
        int result = cps.assignCompetence(usernameMain, listId);
        assertEquals(result, ExpResult);       
    }
    
     /**
     * Test of assignCompetence method, of class CompetenceService, assigning 
     * an existing Competence without passing username.
     */
    @Test(expected=UsernotFoundException.class)
    public void testAssignCompetence5() throws Exception {
        System.out.println("assignCompetence");
        String usernameMain = null;
        List<Integer> listId = new LinkedList<>();
        listId.add(1);
        int ExpResult = 0;
        int result = cps.assignCompetence(usernameMain, listId);
        assertEquals(result, ExpResult);       
    }
    
    /**
     * Test of DeassignCompetence method, of class CompetenceService, deassigning 
     * to a valid maintainer an existing Competence.
     */
    @Test
    public void testDeassignCompetence() throws Exception {
        System.out.println("deassignCompetence");
        String usernameMain = "mrossi";
        List<Integer> listId = new LinkedList<>(); 
        listId.add(1);
        int ExpResult = 1;
        int result = cps.deassignCompetence(usernameMain, listId);
        assertEquals(result, ExpResult);       
    }
    
    /**
     * Test of DeassignCompetence method, of class CompetenceService, deassigning 
     *  to an invalid Maintainer an existing Competence.
     */
    @Test(expected=UsernotFoundException.class)
    public void testDeassignCompetence1() throws Exception {
        System.out.println("deassignCompetence");
        String usernameMain = "giulio";
        List<Integer> listId = new LinkedList<>();
        listId.add(1);
        int expResult = 0;
        int result = cps.deassignCompetence(usernameMain, listId);
        assertEquals(result, expResult);       
    }
    
    /**
     * Test of DeassignCompetence method, of class CompetenceService, deassigning 
     * to an invalid User (not a Maintainer) an existing Competence.
     */
    @Test(expected=UsernotFoundException.class)
    public void testDeassignCompetence2() throws Exception {
        System.out.println("deassignCompetence");
        String usernameMain = "lbianchi";
        List<Integer> listId = new LinkedList<>();
        listId.add(1);
        int expResult = 0;
        int result = cps.deassignCompetence(usernameMain, listId);
        assertEquals(result, expResult);       
    }
    
    /**
     * Test of DeassignCompetence method, of class CompetenceService, deassigning 
     * to a valid Maintainer an invalid Competence.
     */
    @Test
    public void testDeassignCompetence3() throws Exception {
        System.out.println("deassignCompetence");
        String usernameMain = "mrossi";
        List<Integer> listId = new LinkedList<>();
        listId.add(15); 
        int expResult = 0;
        int result = cps.deassignCompetence(usernameMain, listId);
        assertEquals(result, expResult);       
    }

    /**
     * Test of DeassignCompetence method, of class CompetenceService, deassigning 
     * an existing Competence to a valid Maintainer, who has not that competence.
     */
    @Test
    public void testDeassignCompetence4() throws Exception {
        System.out.println("deassignCompetence");
        String usernameMain = "mrossi";
        List<Integer> listId = new LinkedList<>();
        // Deassign to mrossi compotence with id 2
        listId.add(2);
        int ExpResult = 0;
        int result = cps.deassignCompetence(usernameMain, listId);
        assertEquals(result, ExpResult);       
    }
    
     /**
     * Test of DeassignCompetence method, of class CompetenceService, deassigning 
     * an existing competence without passing maintainer's username.
     */
    @Test(expected=UsernotFoundException.class)
    public void testDeassignCompetence5() throws Exception {
        System.out.println("deassignCompetence");
        String usernameMain = null;
        List<Integer> listId = new LinkedList<>();
        listId.add(1);
        int ExpResult = 0;
        int result = cps.deassignCompetence(usernameMain, listId);
        assertEquals(result, ExpResult);       
    }
    
}
