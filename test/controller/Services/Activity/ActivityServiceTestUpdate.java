/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Activity;

import configuration.Database.ConnectionForTest;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Services.ActivityService;
import java.sql.SQLException;
import model.Department.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */

public class ActivityServiceTestUpdate {

    private ActivityService as;
    private ConnectionForTest cft;
    

    public ActivityServiceTestUpdate() {
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
     * Test of updateActivity method, of class ActivityService, updating a valid
     * MaintenanceActivity.
     */
    @Test
    public void testUpdateActivity() throws Exception {
        System.out.println("updateActivity");
        Integer id = 1;
        String type = "Mechanical";
        String description = "Change oil";
        int timeActivity = 22;
        int week_num= 33;
        Department department= new Department("Nusco - Carpentry");
        int notExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num, department);
        assertNotEquals(result, notExpectedResult);

    }

    /**
     * Test of updateActivity method, of class ActivityService, updating an
     * invalid MaintenanceActivity (ID doesn't exist).
     */
    @Test(expected = UnsuccessfulUpdateException.class)
    public void testUpdateActivity1() throws Exception {
        System.out.println("updateActivity");
        Integer id = 55;
        String type = "Mechanical";
        String description = "Change oil";
        int timeActivity = 22;
        int week_num= 22;
        Department department= new Department("Nusco - Carpentry");
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
        Integer id = 1;
        String type = "Mechanical";
        String description = "Change oil";
        int timeActivity = -3;
        int week_num= 11;
        Department department= new Department("Nusco - Carpentry");
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity,week_num, department);
        assertEquals(result, ExpectedResult);

    }

    /**
     * Test of updateActivity method, of class ActivityService, updating an
     * valid MaintenanceActivity with an invalid Type (empty).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateActivity3() throws Exception {
        System.out.println("updateActivity");
        Integer id = 2;
        String type = null;
        String description = "Change oil";
        int timeActivity = 122;
        int week_num= 21;
        Department department= new Department("Nusco - Carpentry");
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num, department);
        assertEquals(result, ExpectedResult);

    }

    /**
     * Test of updateActivity method, of class ActivityService, updating an
     * valid MaintenanceActivity with an invalid Description (empty).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateActivity4() throws Exception {
        System.out.println("updateActivity");
        Integer id = 2;
        String type = "Mechanical";
        String description = null;
        int timeActivity = 122;
        int week_num=21;
        Department department= new Department("Nusco - Carpentry");
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num ,department);
        assertEquals(result, ExpectedResult);

    }
    
    /**
     * Test of updateActivity method, of class ActivityService, updating an
     * valid MaintenanceActivity with an invalid week number.
     */
    @Test(expected = SQLException.class)
    public void testUpdateActivity5() throws Exception {
        System.out.println("updateActivity");
        Integer id = 2;
        String type = "Mechanical";
        String description = "Change engine";
        int timeActivity = 122;
        int week_num=0;
        Department department= new Department("Nusco - Carpentry");
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num ,department);
        assertEquals(result, ExpectedResult);

    }
    
     /**
     * Test of updateActivity method, of class ActivityService, updating an
     * valid MaintenanceActivity with an invalid week number.
     */
    @Test(expected = SQLException.class)
    public void testUpdateActivity7() throws Exception {
        System.out.println("updateActivity");
        Integer id = 1;
        String type = "Mechanical";
        String description = "Change engine";
        int timeActivity = 122;
        int week_num=53;
        Department department= new Department("Nusco - Carpentry");
        int ExpectedResult = 99;
        int result = as.updateActivity(id, type, description, timeActivity, week_num ,department);
        assertEquals(result, ExpectedResult);

    }
     /**
     * Test of updateActivity method, of class ActivityService, updating an
     * valid MaintenanceActivity with an invalid Department (it doesn't exist).
     */
    @Test(expected = SQLException.class)
    public void testUpdateActivity8() throws Exception {
        System.out.println("updateActivity");
        Integer id = 2;
        String type = "Mechanical";
        String description = "Change engine";
        int timeActivity = 122;
        int week_num=11;
        Department department= new Department("Morra - Painting");
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num ,department);
        assertEquals(result, ExpectedResult);

    }
    
    /**
     * Test of updateActivity method, of class ActivityService, updating a valid
     * MaintenanceActivity, but with the week num set to null.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateActivity9() throws Exception {
        System.out.println("updateActivity");
        Integer id = 1;
        String type = "Mechanical";
        String description = "Change oil";
        int timeActivity = 22;
        Integer week_num= null;
        Department department= new Department("Nusco - Carpentry");
        int notExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num, department);
        assertNotEquals(result, notExpectedResult);

    }
    
}
