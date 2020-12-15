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
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Services.ActivityService;
import java.security.InvalidParameterException;
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
    private DBProduct dbProduct;
    

    public ActivityServiceTestUpdate() {
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
     * Test of updateActivity method, of class ActivityService, updating a valid
     * MaintenanceActivity.
     */
    @Test
    public void testUpdateActivity() throws Exception {
        System.out.println("updateActivity");
        Integer id = 1;
        String type = "Mechanical";
        String description = "Change oil";
        Integer timeActivity = 22;
        Integer week_num= 33;
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
        Integer timeActivity = 22;
        Integer week_num= 22;
        Department department= new Department("Nusco - Carpentry");
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num, department);
        assertEquals(result, ExpectedResult);

    }

     /**
     * Test of updateActivity method, of class ActivityService, updating an
     * invalid MaintenanceActivity (ID empty).
     */ 
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateActivity2() throws Exception {
        System.out.println("updateActivity");
        Integer id = null;
        String type = "Mechanical";
        String description = "Change oil";
        Integer timeActivity = 22;
        Integer week_num= 22;
        Department department= new Department("Nusco - Carpentry");
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num, department);
        assertEquals(result, ExpectedResult);

    }
    
    /**
     * Test of updateActivity method, of class ActivityService, updating a
     * valid MaintenanceActivity with an invalid timeActivity (negative).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateActivity3() throws Exception {
        System.out.println("updateActivity");
        Integer id = 1;
        String type = "Mechanical";
        String description = "Change oil";
        Integer timeActivity = -3;
        Integer week_num= 11;
        Department department= new Department("Nusco - Carpentry");
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity,week_num, department);
        assertEquals(result, ExpectedResult);

    }
    
     /**
     * Test of updateActivity method, of class ActivityService, updating a
     * valid MaintenanceActivity with an invalid timeActivity (empty).
     */
    @Test
    public void testUpdateActivity4() throws Exception {
        System.out.println("updateActivity");
        Integer id = 1;
        String type = "Mechanical";
        String description = "Change oil";
        Integer timeActivity = null;
        Integer week_num= 11;
        Department department= new Department("Nusco - Carpentry");
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity,week_num, department);
        assertNotEquals(result, ExpectedResult);

    }

    /**
     * Test of updateActivity method, of class ActivityService, updating a
     * valid MaintenanceActivity with an invalid Type (empty).
     */
    @Test
    public void testUpdateActivity5() throws Exception {
        System.out.println("updateActivity");
        Integer id = 2;
        String type = null;
        String description = "Change oil";
        Integer timeActivity = 122;
        Integer week_num= 21;
        Department department= new Department("Nusco - Carpentry");
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num, department);
        assertNotEquals(result, ExpectedResult);

    }

     /**
     * Test of updateActivity method, of class ActivityService, updating a
     * valid MaintenanceActivity with an invalid Type (legth > 20).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateActivity6() throws Exception {
        System.out.println("updateActivity");
        Integer id = 2;
        String type = "Electrical - Mechanics - Idraulic";
        String description = "Change oil"; 
        Integer timeActivity = 122;
        Integer week_num= 21;
        Department department= new Department("Nusco - Carpentry");
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num, department);
        assertEquals(result, ExpectedResult);

    }
    /**
     * Test of updateActivity method, of class ActivityService, updating a
     * valid MaintenanceActivity with an invalid Description (empty).
     */
    @Test
    public void testUpdateActivity7() throws Exception {
        System.out.println("updateActivity");
        Integer id = 2;
        String type = "Mechanical";
        String description = null;
        Integer timeActivity = 122;
        Integer week_num=21;
        Department department= new Department("Nusco - Carpentry");
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num ,department);
        assertNotEquals(result, ExpectedResult);

    }
    
    /**
     * Test of updateActivity method, of class ActivityService, updating a
     * valid MaintenanceActivity with an invalid Description (length > 30).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateActivity8() throws Exception {
        System.out.println("updateActivity");
        Integer id = 2;
        String type = "Mechanical";
        String description = "Change sink, window, door, cable, tube, radiator";
        Integer timeActivity = 122;
        Integer week_num = 21;
        Department department= new Department("Nusco - Carpentry");
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num ,department);
        assertEquals(result, ExpectedResult);

    }
    /**
     * Test of updateActivity method, of class ActivityService, updating a
     * valid MaintenanceActivity with an invalid week number (it must be between 1 and 52).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateActivity9() throws Exception {
        System.out.println("updateActivity");
        Integer id = 2;
        String type = "Mechanical";
        String description = "Change engine";
        Integer timeActivity = 122;
        Integer week_num = 0;
        Department department= new Department("Nusco - Carpentry");
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num ,department);
        assertEquals(result, ExpectedResult);

    }
    
     /**
     * Test of updateActivity method, of class ActivityService, updating a
     * valid MaintenanceActivity with an invalid week number(it must be between 1 and 52).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateActivity10() throws Exception {
        System.out.println("updateActivity");
        Integer id = 1;
        String type = "Mechanical";
        String description = "Change engine";
        Integer timeActivity = 122;
        Integer week_num = 53;
        Department department = new Department("Nusco - Carpentry");
        int ExpectedResult = 99;
        int result = as.updateActivity(id, type, description, timeActivity, week_num ,department);
        assertEquals(result, ExpectedResult);

    }
     /**
     * Test of updateActivity method, of class ActivityService, updating a
     * valid MaintenanceActivity with an invalid Department (it doesn't exist).
     */
    @Test(expected = SQLException.class)
    public void testUpdateActivity11() throws Exception {
        System.out.println("updateActivity");
        Integer id = 2;
        String type = "Mechanical";
        String description = "Change engine";
        Integer timeActivity = 122;
        Integer week_num = 11;
        Department department= new Department("Morra - Painting");
        int ExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num ,department);
        assertEquals(result, ExpectedResult);

    }
    
    /**
     * Test of updateActivity method, of class ActivityService, updating a valid
     * MaintenanceActivity with an invalid week number (empty).
     */
    @Test
    public void testUpdateActivity12() throws Exception { 
        System.out.println("updateActivity");
        Integer id = 1;
        String type = "Mechanical";
        String description = "Change oil";
        Integer timeActivity = 22;
        Integer week_num = null;
        Department department= new Department("Nusco - Carpentry");
        int notExpectedResult = 0;
        int result = as.updateActivity(id, type, description, timeActivity, week_num, department);
        assertNotEquals(result, notExpectedResult);

    }
    
}
