/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Activity;

import controller.Services.ActivityService;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marika
 */
public class ActivityServiceTestAssignActivity {
    private ActivityService as;
    
    public ActivityServiceTestAssignActivity() {
    }
    
    @Before
    public void setUp() {
        as = ActivityService.getActivityService();
    }

    /**
     * Test of assignActivity method, of class ActivityService.
     */
    @Test
    public void testAssignActivity() throws Exception {
        System.out.println("assignActivity");
        String usernameMain = "";
        List<Integer> listId = null;
        ActivityService instance = null;
        instance.assignActivity(usernameMain, listId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
