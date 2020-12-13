/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import model.Activity.MaintenanceActivity;
import model.Competences.Competence;
import model.Department.Department;
import model.Material.Material;
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
public class ActivityServiceTest {
    
    public ActivityServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getActivityService method, of class ActivityService.
     */
    @Test
    public void testGetActivityService() {
        System.out.println("getActivityService");
        ActivityService expResult = null;
        ActivityService result = ActivityService.getActivityService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertActivity method, of class ActivityService.
     */
    @Test
    public void testInsertActivity() throws Exception {
        System.out.println("insertActivity");
        String type = "";
        String description = "";
        Integer time = null;
        ArrayList<Competence> skill = null;
        ArrayList<Material> materials = null;
        Integer week_num = null;
        Department dep = null;
        ActivityService instance = null;
        int expResult = 0;
        int result = instance.insertActivity(type, description, time, skill, materials, week_num, dep);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of assignActivity method, of class ActivityService.
     */
    @Test
    public void testAssignActivity() throws Exception {
        System.out.println("assignActivity");
        String usernameMain = "";
        Integer activityId = null;
        List<Integer> listIdDay = null;
        double time = 0.0;
        ActivityService instance = null;
        int expResult = 0;
        int result = instance.assignActivity(usernameMain, activityId, listIdDay, time);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unassignActivity method, of class ActivityService.
     */
    @Test
    public void testUnassignActivity() throws Exception {
        System.out.println("unassignActivity");
        Integer activityId = null;
        ActivityService instance = null;
        int expResult = 0;
        int result = instance.unassignActivity(activityId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateActivity method, of class ActivityService.
     */
    @Test
    public void testUpdateActivity() throws Exception {
        System.out.println("updateActivity");
        Integer id = null;
        String type = "";
        String description = "";
        int timeActivity = 0;
        Integer week_num = null;
        Department dep = null;
        ActivityService instance = null;
        int expResult = 0;
        int result = instance.updateActivity(id, type, description, timeActivity, week_num, dep);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteActivity method, of class ActivityService.
     */
    @Test
    public void testDeleteActivity() throws Exception {
        System.out.println("deleteActivity");
        Integer activityId = null;
        ActivityService instance = null;
        int expResult = 0;
        int result = instance.deleteActivity(activityId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActivity method, of class ActivityService.
     */
    @Test
    public void testGetActivity() throws Exception {
        System.out.println("getActivity");
        Integer ID = null;
        ActivityService instance = null;
        MaintenanceActivity expResult = null;
        MaintenanceActivity result = instance.getActivity(ID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllActivities method, of class ActivityService.
     */
    @Test
    public void testGetAllActivities() throws Exception {
        System.out.println("getAllActivities");
        ActivityService instance = null;
        List<MaintenanceActivity> expResult = null;
        List<MaintenanceActivity> result = instance.getAllActivities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllActivitiesInWeek method, of class ActivityService.
     */
    @Test
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
    @Test
    public void testGetAssignedActivities() throws Exception {
        System.out.println("getAssignedActivities");
        ActivityService instance = null;
        TreeMap<Integer, String> expResult = null;
        TreeMap<Integer, String> result = instance.getAssignedActivities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
