/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Activity;

import configuration.Database.ConnectionForTest;
import controller.Services.ActivityService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */
public class ActivityServiceTestDelete {
    
    private ActivityService as;
    private ConnectionForTest cft;
    
    
    public ActivityServiceTestDelete() {
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
     * Test of deleteActivity method, of class ActivityService deleting an
     * Activity by its ID.
     */
    @Test
    public void testDeleteActivity() throws Exception {
        System.out.println("deleteActivity");
        Integer activityId = 2;
        int expResult = 1;
        int result = as.deleteActivity(activityId);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of deleteActivity method, of class ActivityService deleting an
     * Activity by an inexistent ID.
     */
    @Test
    public void testDeleteActivity1() throws Exception {
        System.out.println("deleteActivity");
        Integer activityId = 10; 
        int expResult = 0;
        int result = as.deleteActivity(activityId);
        assertEquals(result, expResult);
    }
    
    /**
     * Test of deleteActivity method, of class ActivityService deleting an
     * Activity by an uncorrect ID (it must be positive).
     */
    @Test
    public void testDeleteActivity2() throws Exception {
        System.out.println("deleteActivity");
        Integer activityId = -1 ;
        int expResult = 0;
        int result = as.deleteActivity(activityId);
        assertEquals(result, expResult);
    }
    
    /**
     * Test of deleteActivity method, of class ActivityService deleting an
     * Activity by an uncorrect ID (it must be not null).
     */
    @Test
    public void testDeleteActivity3() throws Exception {
        System.out.println("deleteActivity");
        Integer activityId = null ;
        int expResult = 0;
        int result = as.deleteActivity(activityId);
        assertEquals(result, expResult);
    }
    
    //caso delete con null
}
