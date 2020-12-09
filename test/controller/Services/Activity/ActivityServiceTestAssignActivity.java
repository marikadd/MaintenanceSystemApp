/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Activity;

import configuration.Database.ConnectionForTest;
import configuration.Exceptions.UsernotFoundException;
import java.sql.SQLException;
import controller.Services.ActivityService;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */
public class ActivityServiceTestAssignActivity {
    private ActivityService as;
    private ConnectionForTest cft;
    
    public ActivityServiceTestAssignActivity() {
    }
    
    @Before
    public void setUp() {
        as = ActivityService.getActivityService();
        cft = ConnectionForTest.init();
    }
    
    @After
    public void setAfter() {
        cft.rollbackConnection();
    }

    /**
     * Test of assignActivity method, of class ActivityService, assigning
     * a MaintenanceActivity to a valid Maintainer.
     */
    @Test
    public void testAssignActivity() throws Exception {
        System.out.println("assignActivity");
        String usernameMain = "Marikadd";
        List<Integer> listId = new ArrayList<>();
        listId.add(7);
        int NotExpectedResult = 0;
        int result = as.assignActivity(usernameMain, listId);
        
        assertNotEquals(result,NotExpectedResult);
    }
    
     /**
     * Test of assignActivity method, of class ActivityService, assigning
     * a MaintenanceActivity to an invalid User.
     */
    @Test(expected=UsernotFoundException.class)
    public void testAssignActivity1() throws Exception {
        System.out.println("assignActivity");
        String usernameMain = "icantalupo";
        List<Integer> listId = new ArrayList<>();
        listId.add(7);
        int ExpectedResult = 0;
        int result = as.assignActivity(usernameMain, listId);
        
        assertEquals(result,ExpectedResult);
    }

    /**
     * Test of assignActivity method, of class ActivityService, assigning
     * a MaintenanceActivity to a valid Maintainer who already has it.
     */
    @Test(expected=SQLException.class)
    public void testAssignActivity2() throws Exception {
        System.out.println("assignActivity");
        String usernameMain = "Marikadd";
        List<Integer> listId = new ArrayList<>();
        listId.add(7);
        int ExpectedResult = 0;
        int result = as.assignActivity(usernameMain, listId);
        
        assertEquals(result,ExpectedResult);
    }
    
    /**
     * Test of assignActivity method, of class ActivityService, assigning
     * an invalid MaintenanceActivity to a valid Maintainer.
     */
    @Test(expected=SQLException.class)
    public void testAssignActivity3() throws Exception {
        System.out.println("assignActivity");
        String usernameMain = "Marikadd";
        List<Integer> listId = new ArrayList<>();
        listId.add(55);
        int ExpectedResult = 0;
        int result = as.assignActivity(usernameMain, listId);
        
        assertEquals(result,ExpectedResult);
    }
    
}
