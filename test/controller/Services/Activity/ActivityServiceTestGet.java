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
import configuration.Exceptions.ActivityNotFoundException;
import configuration.Exceptions.DayNotValidException;
import configuration.Exceptions.InvalidParameterObjectException;
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
import java.sql.Connection;

/**
 *
 * @author Group9
 */
public class ActivityServiceTestGet {

    private ActivityService as;
    private ConnectionForTest cft;
    private DBProduct dbProduct;

    public ActivityServiceTestGet() {
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
     * Test of getAllActivities method, of class ActivityService, getting all
     * activities.
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

    /**
     * Test of getGetActivity method, of class ActivityService, getting a valid
     * MaintenanceActivity.
     */
    @Test
    public void testGetActivity() throws Exception {
        System.out.println("getActivity");
        Integer ID = 1;
        List<MaintenanceActivity> list = new ArrayList<>();
        int expResult = 1;
        int result = as.getActivity(ID).getID();
        assertEquals(expResult, result);

    }

    /**
     * Test of getGetActivity method, of class ActivityService, getting an
     * invalid MaintenanceActivity.
     */
    @Test(expected = ActivityNotFoundException.class)
    public void testGetActivity1() throws Exception {
        System.out.println("getActivity");
        Integer ID = 51;
        List<MaintenanceActivity> list = new ArrayList<>();
        int expResult = 0;
        int result = as.getActivity(ID).getID();
        assertEquals(expResult, result);

    }

    /**
     * Test of getGetActivity method, of class ActivityService, getting an
     * invalid MaintenanceActivity (empty).
     */
    @Test(expected = ActivityNotFoundException.class)
    public void testGetActivity2() throws Exception {
        System.out.println("getActivity");
        Integer ID = null;
        List<MaintenanceActivity> list = new ArrayList<>();
        int expResult = 0;
        int result = as.getActivity(ID).getID();
        assertEquals(expResult, result);

    }
    /**
     * Test of getAllActivitiesInWeek method, of class ActivityService, getting
     * all MaintenanceActivity in a valid week ([1,52]).
     */
    @Test
    public void testGetAllActivitiesInWeek() throws Exception {
        System.out.println("getAllActivitiesInWeek");
        Integer week_num = 9;
        int expResult = 1;
        int result = as.getAllActivitiesInWeek(week_num).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllActivitiesInWeek method, of class ActivityService, getting
     * all MaintenanceActivity in a valid week ([1,52]) without any Activity.
     */
    @Test
    public void testGetAllActivitiesInWeek1() throws Exception {
        System.out.println("getAllActivitiesInWeek1");
        Integer week_num = 1;
        int expResult = 0;
        int result = as.getAllActivitiesInWeek(week_num).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllActivitiesInWeek method, of class ActivityService, getting
     * all MaintenanceActivity in an invalid week (>52).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testGetAllActivitiesInWeek2() throws Exception {
        System.out.println("getAllActivitiesInWeek");
        Integer week_num = 53;
        int expResult = 0;
        int result = as.getAllActivitiesInWeek(week_num).size();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAllActivitiesInWeek method, of class ActivityService, getting
     * all MaintenanceActivity in an invalid week (<1).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testGetAllActivitiesInWeek3() throws Exception {
        System.out.println("getAllActivitiesInWeek");
        Integer week_num = 0;
        int expResult = 0;
        int result = as.getAllActivitiesInWeek(week_num).size();
        assertEquals(expResult, result);
    }
   
    /**
     * Test of getAllActivitiesInWeek method, of class ActivityService, getting
     * all MaintenanceActivity in an invalid week (empty).
     */
    @Test(expected = ActivityNotFoundException.class)
    public void testGetAllActivitiesInWeek4() throws Exception {
        System.out.println("getAllActivitiesInWeek");
        Integer week_num = null;
        int expResult = 0;
        int result = as.getAllActivitiesInWeek(week_num).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDailyAvailability method, of class ActivityService, getting
     * avaiability from a valid Maintainer in a valid day.
     */
    @Test
    public void testGetDailyAvailability() throws Exception {
        System.out.println("getDailyAvailability");
        String username = "mrossi";
        Integer day = 1;
        int expResult = 71; //An activity of 120 min is assigned to mrossi so --> ((420-120)/420)*100) = 71%
        int result = as.getDailyAvailability(username, day);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDailyAvailability method, of class ActivityService, getting
     * avaiability from a valid Maintainer in a unvalid day.
     */
    @Test(expected = DayNotValidException.class)
    public void testGetDailyAvailability1() throws Exception {
        System.out.println("getDailyAvailability");
        String username = "mrossi";
        Integer day = 0;
        int expResult = 71; //An activity of 120 min is assigned to mrossi so --> ((420-120)/420)*100) = 71%
        int result = as.getDailyAvailability(username, day);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDailyAvailability method, of class ActivityService, getting
     * avaiability from a valid Maintainer in an unvalid day.
     */
    @Test(expected = DayNotValidException.class)
    public void testGetDailyAvailability2() throws Exception {
        System.out.println("getDailyAvailability");
        String username = "mrossi";
        Integer day = 8;
        int expResult = 71; //An activity of 120 min is assigned to mrossi so --> ((420-120)/420)*100) = 71%
        int result = as.getDailyAvailability(username, day);
        assertEquals(expResult, result);
    }
    

 
    /**
     * Test of getDailyAvailability method, of class ActivityService, getting
     * avaiability from a invalid Maintainer in a valid day. 
     */
    @Test(expected= UsernotFoundException.class)
    public void testGetDailyAvailability3() throws Exception {
        System.out.println("getDailyAvailability");
        String username = "giulioc";
        Integer day = 1;
        int expResult = 0;
        int result = as.getDailyAvailability(username, day);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAssignedActivities method, of class ActivityService, getting
     * all activities assigned.
     */
    @Test
    public void testGetAssignedActivities() throws Exception {
        System.out.println("getAssignedActivities");
        int expResult = 1;
        int result = as.getAssignedActivities().size();
        assertEquals(expResult, result);
    }

}
