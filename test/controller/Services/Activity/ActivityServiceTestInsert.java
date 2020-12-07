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
import model.Competences.Competence;
import model.Department.Department;
import org.junit.Before;
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
     * Activity with its type, description, time, deparment, week number and skills.
     */
    @Test
    public void testInsertActivity() throws Exception {
        System.out.println("insertActivity");
        String type = "Electrical";
        String description = "Change cable";
        Integer time = 120;
        ArrayList<Competence> skill = new ArrayList<>();
        Integer week_num=12;
        Department department= new Department("Electrical area");
        Competence c = new Competence(17, "PAV Certification");
        skill.add(c);
        int result = as.insertActivity(type,description, time, skill, week_num, department);
        int ExpectedResult = 1;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity without its type, description, time, department, week number and skills.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertActivity1() throws Exception {
        System.out.println("insertActivity");
        String type = "";
        String description = "";
        Integer time = 120; //?
        Integer week_num=12;//?
        Department department= new Department("");
        ArrayList<Competence> skill = new ArrayList(Arrays.asList("", ""));
        int result = as.insertActivity(type, description, time, skill, week_num, department);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity with uncorrect type (length > 20).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertActivity2() throws Exception {
        System.out.println("insertActivity");
        String type = "Electrical - Mechanics - Idraulic";
        String description = "Change cable";
        Integer time = 70;
        Integer week_num=12;
        Department department= new Department("Electrical area");
        ArrayList<Competence> skill = new ArrayList(Arrays.asList("Word Certification"));
        int result = as.insertActivity(type, description, time, skill,week_num,department);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity with uncorrect description (length > 30).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertActivity3() throws Exception {
        System.out.println("insertActivity");
        String type = "Mechanics";
        String description = "Change cable with a new cable for a new railway";
        Integer time = 60;
        Integer week_num= 21;
        ArrayList<Competence> skill = new ArrayList(Arrays.asList("Compressor Knowledge"));
        Department department= new Department("Electrical area");
        int result = as.insertActivity(type, description, time, skill, week_num, department);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity with invalid time (time must be an Integer positive).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertActivity4() throws Exception {
        System.out.println("insertActivity");
        String type = "Mechanics";
        String description = "Change tube";
        Integer time = -1;
        Integer week_num= 2;
        ArrayList<Competence> skill = new ArrayList(Arrays.asList("Compressor Maintenance"));
        Department department= new Department("Electrical area");
        int result = as.insertActivity(type, description, time, skill, week_num, department);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }
    
      /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity with invalid week number (time must be between 1 and 52 ).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertActivity5() throws Exception {
        System.out.println("insertActivity");
        String type = "Mechanics";
        String description = "Change tube";
        Integer time = 90;
        Integer week_num= 0;
        ArrayList<Competence> skill = new ArrayList(Arrays.asList("Compressor Maintenance"));
        Department department= new Department("Electrical area");
        int result = as.insertActivity(type, description, time, skill, week_num, department);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

       /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity with invalid week number (it must be between 1 and 52 ).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertActivity6() throws Exception {
        System.out.println("insertActivity");
        String type = "Mechanics";
        String description = "Change tube";
        Integer time = 90;
        Integer week_num= 52;
        ArrayList<Competence> skill = new ArrayList(Arrays.asList("Compressor Maintenance"));
        Department department= new Department("Electrical area");
        int result = as.insertActivity(type, description, time, skill, week_num, department);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }
    
       /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity with invalid week number (it must be between 1 and 52 ).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertActivity7() throws Exception {
        System.out.println("insertActivity");
        String type = "Mechanics";
        String description = "Change tube";
        Integer time = 90;
        Integer week_num= 52;
        ArrayList<Competence> skill = new ArrayList(Arrays.asList("Compressor Maintenance"));
        Department department= new Department("Electrical area");
        int result = as.insertActivity(type, description, time, skill, week_num, department);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }
    
  
       /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity with invalid Department (lenght > 30 ).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertActivity8() throws Exception {
        System.out.println("insertActivity");
        String type = "Mechanics";
        String description = "Change tube";
        Integer time = 90;
        Integer week_num= 52;
        ArrayList<Competence> skill = new ArrayList(Arrays.asList("Compressor Maintenance"));
        Department department= new Department("Engineering and metal processing and production area");
        int result = as.insertActivity(type, description, time, skill, week_num, department);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }
}
