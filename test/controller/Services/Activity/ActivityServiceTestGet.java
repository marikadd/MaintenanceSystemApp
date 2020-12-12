/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Activity;

import configuration.Database.ConnectionForTest;
import configuration.Exceptions.UsernotFoundException;
import controller.Services.ActivityService;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import model.Activity.MaintenanceActivity;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import model.Activity.ActivityInterface;
import org.junit.After;

/**
 *
 * @author Group9
 */

public class ActivityServiceTestGet {

    private ActivityService as;
    private ConnectionForTest cft;
    

    public ActivityServiceTestGet() {
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
     * Test of getAllActivities method, of class ActivityService, getting
     * all activities.
     */
    @Test
    public void testGetAllActivities() throws Exception {
        System.out.println("getAllActivities");
        List<MaintenanceActivity> list = new ArrayList<>();
        list = as.getAllActivities();
        int result = list.size();
        int ExpectedResult = 2; 
        assertEquals(result, ExpectedResult);

    }
    
    @Test
    public void testGetActivity() throws Exception {
        System.out.println("getActivity");
        Integer ID = 1;
        List<MaintenanceActivity> list = new ArrayList<>();
        ActivityService instance = null;
        MaintenanceActivity expResult = null;
        result = as.getActivity(ID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
  
    /**
     * Test of getAllActivitiesInWeek method, of class ActivityService.
     */
    /*@Test
    public void testGetAllActivitiesInWeek() throws Exception {
        System.out.println("getAllActivitiesInWeek");
        int week_num = 0;
        ActivityService instance = null;
        List<MaintenanceActivity> expResult = null;
        List<MaintenanceActivity> result = instance.getAllActivitiesInWeek(week_num);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDailyAvailability method, of class ActivityService.
     */
    @Test
    public void testGetDailyAvailability() throws Exception {
        System.out.println("getDailyAvailability");
        String username = "";
        int day = 0;
        double time = 0.0;
        ActivityService instance = null;
        int expResult = 0;
        int result = instance.getDailyAvailability(username, day, time);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
     /**
     * Test of getAssignedActivities method, of class ActivityService.
     */
    /*
    @Test
    public void testGetAssignedActivities() throws Exception {
        System.out.println("getAssignedActivities");
        ActivityService instance = null;
        TreeMap<String, Integer> expResult = null;
        TreeMap<String, Integer> result = instance.getAssignedActivities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of getAllActivityTarget method, of class ActivityService, getting
     *all MaintenanceActivity from a valid Maintainer.
   
    @Test
    public void testGetAllActivityTarget() throws Exception {
        System.out.println("getAllActivityTarget");
        String username = "mrossi";
        List<ActivityInterface> list = new ArrayList<>();
        list = as.getAllActivityTarget(username);
        int result = list.size(); //restituisce il numero di attività ???
        int expectedResult = 1;

        assertEquals(result, expectedResult);

    }

    /**
     * Test of getAllActivityTarget method, of class ActivityService, getting
     * all MaintenanceActivity from an invalid Maintainer.
     */ 
    /*
    @Test(expected = UsernotFoundException.class)
    public void testGetAllActivityTarget1() throws Exception {
        System.out.println("getAllActivityTarget");
        String username = "lbianchi";
        List<ActivityInterface> list = new ArrayList<>();
        list = as.getAllActivityTarget(username);
        int result = list.size();
        int expectedResult = 0;

        assertEquals(result, expectedResult);

    }

    /**
     * Test of getAllActivityTarget method, of class ActivityService, getting
     * all MaintenanceActivity from an invalid User.
     */
    /*
    @Test(expected = UsernotFoundException.class)
    public void testGetAllActivityTarget2() throws Exception {
        System.out.println("getAllActivityTarget");
        String username = "icantalupo";
        List<ActivityInterface> list = new ArrayList<>();
        list = as.getAllActivityTarget(username);
        int result = list.size();
        int expectedResult = 0;

        assertEquals(result, expectedResult);

    }*/

}
