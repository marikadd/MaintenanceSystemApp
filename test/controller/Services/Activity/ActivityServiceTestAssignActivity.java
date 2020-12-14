/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Activity;

import configuration.Database.ConnectionForTest;
import configuration.Database.DBAbstractFactory;
import configuration.Database.DBFactoryContext;
import configuration.Database.DBManager;
import configuration.Database.DBProduct;
import configuration.Exceptions.ActivityAlreadyAssignedException;
import configuration.Exceptions.TimeExpiredException;
import configuration.Exceptions.UnsuccessfulUpdateException;
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
    private DBProduct dbProduct;

    public ActivityServiceTestAssignActivity() {
    }

    @Before
    public void setUp() {
        as = ActivityService.getActivityService();
        DBAbstractFactory dbFactory = new DBFactoryContext();
        cft = ConnectionForTest.init();
        dbProduct = dbFactory.getInstance(DBManager.instanceType);
        cft.setConn(dbProduct.connectToDB());
        setAfter();
    }

    @After
    public void setAfter() {
        cft.rollbackConnection();
    }

    /**
     * Test of assignActivity method, of class ActivityService, assigning a
     * valid MaintenanceActivity to a valid Maintainer.
     */
    
    @Test
    public void testAssignActivity() throws Exception {
        System.out.println("assignActivity");
        String usernameMain = "mrossi";
        Integer activityId = 2;
        int NotExpectedResult = 0;
        List<Integer> listDay = new ArrayList<>();
        listDay.add(1);
        int result = as.assignActivity(usernameMain, activityId, listDay, 100.0);
         
        assertNotEquals(result,NotExpectedResult);
    }
    
    /*
    
     */
    /**
     * Test of assignActivity method, of class ActivityService, assigning an
     * invalid MaintenanceActivity to a valid Maintainer.
     */
    
    @Test(expected=SQLException.class)
    public void testAssignActivity1() throws Exception {
        System.out.println("assignActivity");
        String usernameMain = "mrossi";
        Integer activityId = 69;
        int ExpectedResult = 0;
        List<Integer> listDay = new ArrayList<>();
        listDay.add(1);
        int result = as.assignActivity(usernameMain, activityId, listDay, 180.0);
         
        assertEquals(result,ExpectedResult);
    }
    /*
    
     */
    /**
     * Test of assignActivity method, of class ActivityService, assigning a
     * MaintenanceActivity to an invalid User.
     */
      @Test(expected=UsernotFoundException.class) 
      public void testAssignActivity2() throws Exception {
        System.out.println("assignActivity"); 
        String usernameMain = "giulioc";
        Integer activityId = 2; 
        int ExpectedResult = 0;
        List<Integer> listDay = new ArrayList<>();
        listDay.add(1);
        int result = as.assignActivity(usernameMain, activityId, listDay, 100.0);
     
        assertEquals(result,ExpectedResult); 
      }
     
     /**
     * Test of assignActivity method, of class ActivityService, assigning a
     * MaintenanceActivity to a valid Maintainer who already has it.
     */
    @Test(expected=ActivityAlreadyAssignedException.class)
    public void testAssignActivity3() throws Exception {
        System.out.println("assignActivity3");
        String usernameMain = "mrossi";
        Integer activityId = 1;
        int ExpectedResult = 0;
        List<Integer> listDay = new ArrayList<>();
        listDay.add(2);
        int result = as.assignActivity(usernameMain, activityId, listDay, 120.0);
        
        assertEquals(result,ExpectedResult);
    }
    
    /**
     * Test of assignActivity method, of class ActivityService, assigning a
     * MaintenanceActivity to a valid Maintainer, but this activity is already 
     * assigned to another Maintainer.
     */
    @Test(expected=ActivityAlreadyAssignedException.class)
    public void testAssignActivity4() throws Exception {
        System.out.println("assignActivity4");
        String usernameMain = "fcerruti";
        Integer activityId = 1;
        int ExpectedResult = 0;
        List<Integer> listDay = new ArrayList<>();
        listDay.add(2);
        int result = as.assignActivity(usernameMain, activityId, listDay, 120.0);
        
        assertEquals(result,ExpectedResult);
    }
    
    /**
     * Test of assignActivity method, of class ActivityService, assigning a
     * MaintenanceActivity to a valid Maintainer, but the time of activity is
     * greater than his time availability.
     */
    @Test(expected=TimeExpiredException.class)
    public void testAssignActivity5() throws Exception {
        System.out.println("assignActivity5");
        String usernameMain = "mrossi";
        Integer activityId = 2;
        int ExpectedResult = 0;
        List<Integer> listDay = new ArrayList<>();
        listDay.add(1);
        // mrossi already used 120 minutes of time availability on monday
        int result = as.assignActivity(usernameMain, activityId, listDay, 400.0);
        
        assertEquals(result,ExpectedResult);
    }
    
    /**
     * Test of unassignActivity method, of class ActivityService, unassigning a
     * valid MaintenanceActivity.
     */
    @Test
    public void testUnassignActivity() throws Exception {
        System.out.println("unassignActivity");
        Integer activityId = 1;
        int NotExpectedResult = 0;
        int result = as.unassignActivity(activityId);
        assertNotEquals(NotExpectedResult, result);
    }

    /**
     * Test of unassignActivity method, of class ActivityService, unassigning an
     * invalid MaintenanceActivity.
     */
    @Test(expected = UnsuccessfulUpdateException.class)
    public void testUnassignActivity1() throws Exception {
        System.out.println("unassignActivity1");
        Integer activityId = 69;
        int ExpectedResult = 0;
        int result = as.unassignActivity(activityId);
        assertEquals(ExpectedResult, result);
    }

    /**
     * Test of unassignActivity method, of class ActivityService, unassigning a
     * valid MaintenanceActivity that is not assigned.
     */
    @Test(expected = UnsuccessfulUpdateException.class)
    public void testUnassignActivity2() throws Exception {
        System.out.println("unassignActivity1");
        Integer activityId = 2;
        int ExpectedResult = 0;
        int result = as.unassignActivity(activityId);
        assertEquals(ExpectedResult, result);
    }

}
