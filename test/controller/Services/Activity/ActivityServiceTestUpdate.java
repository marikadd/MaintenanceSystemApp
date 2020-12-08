/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Activity;

import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Services.ActivityService;
import model.Department.Department;
import org.junit.Before;
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
     * Test of updateActivity method, of class ActivityService, updating a valid
     * MaintenanceActivity.
     */
    @Test
    public void testUpdateActivity() throws Exception {
        System.out.println("updateActivity");
        Integer id = 7;
        String type = "Mechanical";
        String description = "Change Oil";
        int timeActivity = 22;
        int week_num= 33;
        String department= "Fisciano - Molding";
        int notExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num, department);

        assertNotEquals(result, notExpectedResult);

    }

    /**
     * Test of updateActivity method, of class ActivityService, updating an
     * invalid MaintenanceActivity.
     */
    @Test(expected = UnsuccessfulUpdateException.class)
    public void testUpdateActivity1() throws Exception {
        System.out.println("updateActivity");
        Integer id = 55;
        String type = "Mechanical";
        String description = "Change Oil";
        int timeActivity = 22;
        int week_num= 22;
        String department= "Fisciano - Molding";
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num, department);

        assertEquals(result, ExpectedResult);

    }

    /**
     * Test of updateActivity method, of class ActivityService, updating an
     * valid MaintenanceActivity with an invalid timeActivity (negative).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateActivity2() throws Exception {
        System.out.println("updateActivity");
        Integer id = 8;
        String type = "Mechanical";
        String description = "Change Oil";
        int timeActivity = -3;
        int week_num = 11;
        String department = "Fisciano - Molding";
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity,week_num, department);

        assertEquals(result, ExpectedResult);

    }

    /**
     * Test of updateActivity method, of class ActivityService, updating an
     * valid MaintenanceActivity with an invalid Type.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateActivity3() throws Exception {
        System.out.println("updateActivity");
        Integer id = 8;
        String type = "";
        String description = "Change Oil";
        int timeActivity = 122;
        int week_num= 21;
        String department= "Fisciano - Molding";
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num, department);

        assertEquals(result, ExpectedResult);

    }

    /**
     * Test of updateActivity method, of class ActivityService, updating an
     * valid MaintenanceActivity with an invalid Description.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateActivity4() throws Exception {
        System.out.println("updateActivity");
        Integer id = 8;
        String type = "Mechanical";
        String description = "";
        int timeActivity = 122;
        int week_num = 21;
        String department = "Fisciano - Molding";
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num ,department);

        assertEquals(result, ExpectedResult);

    }
    
    /**
     * Test of updateActivity method, of class ActivityService, updating an
     * valid MaintenanceActivity with an invalid week number.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateActivity5() throws Exception {
        System.out.println("updateActivity");
        Integer id = 8;
        String type = "Mechanical";
        String description = "";
        int timeActivity = 122;
        int week_num=0;
        String department = "Fisciano - Molding";
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num ,department);

        assertEquals(result, ExpectedResult);

    }
    
     /**
     * Test of updateActivity method, of class ActivityService, updating an
     * valid MaintenanceActivity with an invalid week number.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateActivity7() throws Exception {
        System.out.println("updateActivity");
        Integer id = 8;
        String type = "Mechanical";
        String description = "";
        int timeActivity = 122;
        int week_num = 0;
        String department = "Fisciano - Molding";
        int ExpectedResult = 99;
        int result = as.updateActivity(id, type, description, timeActivity, week_num ,department);

        assertEquals(result, ExpectedResult);

    }
     /**
     * Test of updateActivity method, of class ActivityService, updating an
     * valid MaintenanceActivity with an invalid Department.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateActivity8() throws Exception {
        System.out.println("updateActivity");
        Integer id = 8;
        String type = "Mechanical";
        String description = "";
        int timeActivity = 122;
        int week_num = -1;
        String department = "Milano - Central";
        int ExpectedResult = 99;
        int result = as.updateActivity(id, type, description, timeActivity, week_num ,department);

        assertEquals(result, ExpectedResult);

    }
}
