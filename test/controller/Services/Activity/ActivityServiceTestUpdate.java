/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Activity;

import controller.Services.ActivityService;
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
public class ActivityServiceTestUpdate {
    
    private ActivityService as;
       
    public ActivityServiceTestUpdate() {
    }
    
    @Before
    public void setUp() {
        as = ActivityService.getActivityService();
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
        ActivityService instance = null;
        instance.updateActivity(id, type, description, timeActivity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
