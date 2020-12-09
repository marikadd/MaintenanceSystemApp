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
     * Test of getAllActivities method, of class ActivityService.
     */
    @Test
    public void testGetAllActivities() throws Exception {
        System.out.println("getAllActivities");
        List<MaintenanceActivity> list = new ArrayList<>();
        list = as.getAllActivities();
        int result = list.size();
        int ExpectedResult = 2; //righe nel database
        assertEquals(result, ExpectedResult);

    }

    /**
     * Test of getAllActivityTarget method, of class ActivityService, getting
     * all MaintenanceActivity from a valid Maintainer.
     */
   
    /*
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
