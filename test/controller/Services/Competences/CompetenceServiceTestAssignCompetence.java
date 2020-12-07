/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Competences;

import configuration.Exceptions.UnsuccessfulUpdateException;
import configuration.Exceptions.UsernotFoundException;
import controller.Services.CompetenceService;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.Competences.Competence;
import model.Competences.CompetenceTarget;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dondi
 */
public class CompetenceServiceTestAssignCompetence {
    
    private CompetenceService cps;
    
    @Before
    public void setUp() {
        cps = CompetenceService.getCompetenceService();
    }

    
    /**
     * Test of assignCompetence method, of class CompetenceService, assignin 
     * an existing Competence to a valid Maintainer.
     */
    @Test
    public void testAssignCompetence() throws Exception {
        System.out.println("assignCompetence");
        String usernameMain = "mrossi";
        List<Integer> listId = new LinkedList<>();
        listId.add(2);
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
        listId.add(5);
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
        listId.add(5);
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
     * an existing Competence to a valid Maintainer 
     * who already has that Competence.
     */
    @Test(expected=SQLException.class)
    public void testAssignCompetence4() throws Exception {
        System.out.println("assignCompetence");
        String usernameMain = "mrossi";
        List<Integer> listId = new LinkedList<>();
        listId.add(5);
        int notExpResult = 0;
        int result = cps.assignCompetence(usernameMain, listId);
        assertNotEquals(result, notExpResult);       
    }
    
}
