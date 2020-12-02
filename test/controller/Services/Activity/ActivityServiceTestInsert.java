/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Activity;

import configuration.Exceptions.InvalidParameterObjectException;
import controller.Services.ActivityService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Activity.ActivityTarget;
import model.Activity.MaintenanceActivity;
import model.Competences.Competence;
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

public class ActivityServiceTestInsert {
    
    private ActivityService as;
    
    public ActivityServiceTestInsert() {
    }
    
    @Before
    public void setUp() {
        as = ActivityService.getActivityService();
    }
   
    /**
     * Test of insertActivity method, of class ActivityService inserting a new 
     * Activity with its type, description, time and skills.
     */
    @Test
    public void testInsertActivity() throws Exception {
        System.out.println("insertActivity");
        String type = "Electrical";
        String description = "Change cable";
        Integer time = 120;
        ArrayList<Competence> skill = new ArrayList(Arrays.asList("PAV Certification", "Excel Certification")); 
        int result = as.insertActivity(type, description, time, skill);
        int ExpectedResult = 1;
        assertEquals(result, ExpectedResult);
    }
    
    /**
     * Test of insertActivity method, of class ActivityService inserting a new 
     * Activity without its type, description, time and skills.
     */
    @Test(expected=InvalidParameterObjectException.class)
    public void testInsertActivity1() throws Exception {
        System.out.println("insertActivity");
        String type = "";
        String description = "";
        Integer time = 120; //?
        ArrayList<Competence> skill = new ArrayList(Arrays.asList("", ""));
        int result = as.insertActivity(type, description, time, skill);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }
    
    
    /**
     * Test of insertActivity method, of class ActivityService inserting a new 
     * Activity with uncorrect type (length > 20).
     */
    @Test(expected=InvalidParameterObjectException.class)
    public void testInsertActivity2() throws Exception {
        System.out.println("insertActivity");
        String type = "Electrical - Mechanics - Idraulic";
        String description = "Change cable";
        Integer time = 70;
        ArrayList<Competence> skill = new ArrayList(Arrays.asList("Word Certification")); 
        int result = as.insertActivity(type, description, time, skill);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }
    
    /**
     * Test of insertActivity method, of class ActivityService inserting a new 
     * Activity with uncorrect description (length > 30).
     */
    @Test(expected=InvalidParameterObjectException.class)
    public void testInsertActivity3() throws Exception {
        System.out.println("insertActivity");
        String type = "Mechanics";
        String description = "Change cable with a new cable for a new railway";
        Integer time = 60;
        ArrayList<Competence> skill = new ArrayList(Arrays.asList("Compressor Knowledge")); 
        int result = as.insertActivity(type, description, time, skill);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }
    
    /**
     * Test of insertActivity method, of class ActivityService inserting a new 
     * Activity with uncorrect time (time must be an Integer positive).
     */
    @Test(expected=InvalidParameterObjectException.class)
    public void testInsertActivity4() throws Exception {
        System.out.println("insertActivity");
        String type = "Mechanics";
        String description = "Change tube";
        Integer time = -1; //????????
        ArrayList<Competence> skill = new ArrayList(Arrays.asList("Compressor Maintenance")); 
        int result = as.insertActivity(type, description, time, skill);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }
    
}