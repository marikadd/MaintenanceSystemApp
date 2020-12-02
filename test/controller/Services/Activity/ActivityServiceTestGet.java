/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Activity;

import controller.Services.ActivityService;
import java.util.List;
import model.Activity.ActivityTarget;
import model.Activity.MaintenanceActivity;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */
public class ActivityServiceTestGet {
    private ActivityService as;
    
    public ActivityServiceTestGet() {
    }
    
    @Before
    public void setUp() {
        as = ActivityService.getActivityService();
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
     * Test of getAllActivityTarget method, of class ActivityService.
     */
    @Test
    public void testGetAllActivityTarget() throws Exception {
        System.out.println("getAllActivityTarget");
        String username = "";
        ActivityService instance = null;
        List<ActivityTarget> expResult = null;
        List<ActivityTarget> result = instance.getAllActivityTarget(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
