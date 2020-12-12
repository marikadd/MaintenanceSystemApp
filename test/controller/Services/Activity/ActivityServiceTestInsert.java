/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Activity;

import com.sun.jdi.connect.spi.Connection;
import configuration.Database.ConnectionForTest;
import configuration.Database.DBAbstractFactory;
import configuration.Database.DBFactoryContext;
import configuration.Database.DBManager;
import configuration.Database.DBProduct;
import configuration.Exceptions.InvalidParameterObjectException;
import controller.Services.ActivityService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import model.Competences.Competence;
import model.Department.Department;
import model.Material.Material;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */
public class ActivityServiceTestInsert {
    
    private ConnectionForTest cft;
    private ActivityService as;
    

    public ActivityServiceTestInsert() {
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
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity with its type, description, time, department, week number, materials and skills.
     */
    @Test
    public void testInsertActivity() throws Exception {
        System.out.println("insertActivity");
        String type = "Electrical";
        String description = "Change cable";
        Integer time = 120;
        Integer week_num=12;
        Department department= new Department("Fisciano - Molding");
        Competence c= new Competence(1, "PAV - Certification");
        ArrayList<Competence> skill = new ArrayList<>();
        skill.add(c);
        Material material= new Material("Marble");
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(material);
        int result = as.insertActivity(type, description, time, skill, materials, week_num, department);
        int ExpectedResult = 1;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity without its type.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertActivity1() throws Exception {
        System.out.println("insertActivity");
        String type = null;
        String description = "change sink";
        Integer time = 100;
        Integer week_num= 12;
        Department department= new Department("Fiscinano - Molding");
        Competence c= new Competence(1, "PAV - Certification");
        ArrayList<Competence> skill = new ArrayList<>();
        skill.add(c);
        Material material= new Material("Marble");
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(material);
        int result = as.insertActivity(type, description, time, skill, materials, week_num, department);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }
    
    /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity without its description.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertActivity2() throws Exception {
        System.out.println("insertActivity");
        String type = "Electrical";
        String description = null;
        Integer time = 100;
        Integer week_num= 12;
        Department department= new Department("Fiscinano - Molding");
        Competence c= new Competence(1, "PAV - Certification");
        ArrayList<Competence> skill = new ArrayList<>();
        skill.add(c);
        Material material= new Material("Iron");
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(material);
        int result = as.insertActivity(type, description, time, skill, materials, week_num, department);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }
    
     /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity without its time.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertActivity3() throws Exception {
        System.out.println("insertActivity");
        String type = "Electrical";
        String description = "Change engine";
        Integer time = null;
        Integer week_num= 12;
        Department department= new Department("Fiscinano - Molding");
        Competence c= new Competence(1, "PAV - Certification");
        ArrayList<Competence> skill = new ArrayList<>();
        skill.add(c);
        Material material= new Material("Iron");
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(material);
        int result = as.insertActivity(type, description, time, skill, materials, week_num, department);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }
    
     /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity without its week number.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertActivity4() throws Exception {
        System.out.println("insertActivity");
        String type = "Electrical";
        String description = "Change engine";
        Integer time = 100;
        Integer week_num= null;
        Department department= new Department("Fiscinano - Molding");
        Competence c= new Competence(1, "PAV - Certification");
        ArrayList<Competence> skill = new ArrayList<>();
        skill.add(c);
        Material material= new Material("Iron");
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(material);
        int result = as.insertActivity(type, description, time, skill, materials, week_num, department);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }
    
     /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity without its department.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertActivity5() throws Exception {
        System.out.println("insertActivity");
        String type = "Electrical";
        String description = "Change engine";
        Integer time = 100;
        Integer week_num= null;
        Department department= new Department("");
        Competence c= new Competence(1, "PAV - Certification");
        ArrayList<Competence> skill = new ArrayList<>();
        skill.add(c);
        Material material= new Material("Iron");
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(material);
        int result = as.insertActivity(type, description, time, skill, materials, week_num, department);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }
    
     /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity without competences. //la deve creare lo stesso
     */
    @Test
    public void testInsertActivity6() throws Exception {
        System.out.println("insertActivity");
        String type = "Electrical";
        String description = "Change engine";
        Integer time = 100;
        Integer week_num= 12;
        Department department= new Department("Fisciano - Molding");
        ArrayList<Competence> skill = new ArrayList<>();
        Material material= new Material("Iron");
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(material);
        int result = as.insertActivity(type, description, time, skill, materials, week_num, department);
        int ExpectedResult = 1;
        assertEquals(result, ExpectedResult);
    }
    
    
    /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity with uncorrect type (length > 20).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertActivity7() throws Exception {
        System.out.println("insertActivity");
        String type = "Electrical - Mechanics - Idraulic";
        String description = "Change cable";
        Integer time = 70;
        Integer week_num= 12;
        Department department= new Department("Fisciano - Molding ");
        Competence c= new Competence(1, "PAV - Certification");
        ArrayList<Competence> skill = new ArrayList<>();
        skill.add(c);
        Material material= new Material("Marble");
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(material);
        int result = as.insertActivity(type, description, time, skill, materials, week_num,department);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity with uncorrect description (length > 30).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertActivity8() throws Exception {
        System.out.println("insertActivity");
        String type = "Mechanic";
        String description = "Change cable with a new cable for a new railway";
        Integer time = 60;
        Integer week_num= 21;
        Department department= new Department("Fisciano - Molding");
        Competence c= new Competence(1, "PAV - Certification");
        ArrayList<Competence> skill = new ArrayList<>();
        skill.add(c);
        Material material= new Material("Iron");
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(material);
        int result = as.insertActivity(type, description, time, skill, materials, week_num, department);
        int ExpectedResult = 0; 
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity with invalid time (time must be an Integer positive).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertActivity9() throws Exception {
        System.out.println("insertActivity");
        String type = "Mechanics";
        String description = "Change tube";
        Integer time = -1;
        Integer week_num= 2;
        Department department= new Department("Fisciano - Molding");
        Competence c= new Competence(1, "PAV - Certification");
        ArrayList<Competence> skill = new ArrayList<>();
        skill.add(c);
        Material material= new Material("Marble");
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(material);
        int result = as.insertActivity(type, description, time, skill, materials, week_num, department);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }
    
     /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity with invalid week number (it must be between 1 and 52 ).
     */
    @Test(expected = SQLException.class)
    public void testInsertActivity10() throws Exception {
        System.out.println("insertActivity");
        String type = "Mechanics";
        String description = "Change tube";
        Integer time = 90;
        Integer week_num= 0;
        Department department= new Department("Fisciano - Molding");
        Competence c= new Competence(1, "PAV - Certification");
        ArrayList<Competence> skill = new ArrayList<>();
        skill.add(c);
        Material material= new Material("Marble");
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(material);
        int result = as.insertActivity(type, description, time, skill, materials, week_num, department);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }

     /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity with invalid week number (it must be between 1 and 52 ).
     */
    @Test(expected = SQLException.class)
    public void testInsertActivity11() throws Exception {
        System.out.println("insertActivity");
        String type = "Mechanics";
        String description = "Change tube";
        Integer time = 90;
        Integer week_num= 53;
        Department department= new Department("Fisciano - Molding");
        Competence c= new Competence(1, "PAV - Certification");
        ArrayList<Competence> skill = new ArrayList<>();
        skill.add(c);
        Material material= new Material("Marble");
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(material);
        int result = as.insertActivity(type, description, time, skill, materials, week_num, department);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }
    
    /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity with valid week number (borderline case: week_num = 1).
     */
    @Test
    public void testInsertActivity12() throws Exception {
        System.out.println("insertActivity");
        String type = "Mechanics";
        String description = "Change tube";
        Integer time = 90;
        Integer week_num= 1;
        Department department= new Department("Fisciano - Molding");
        Competence c= new Competence(1,"PAV - Certification");
        ArrayList<Competence> skill = new ArrayList<>();
        skill.add(c);
        Material material= new Material("Marble");
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(material);
        int result = as.insertActivity(type, description, time, skill, materials, week_num, department);
        int ExpectedResult = 1;
        assertEquals(result, ExpectedResult);
    }
    
    /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity with valid week number (borderline case: week_num = 52 ).
     */
    @Test
    public void testInsertActivity13() throws Exception {
        System.out.println("insertActivity");
        String type = "Mechanics";
        String description = "Change tube";
        Integer time = 90;
        Integer week_num= 52;
        Department department= new Department("Fisciano - Molding");
        Competence c= new Competence(1, "PAV - Certification");
        ArrayList<Competence> skill = new ArrayList<>();
        skill.add(c);
        Material material= new Material("Marble");
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(material);
        int result = as.insertActivity(type, description, time, skill, materials, week_num, department);
        int ExpectedResult = 1;
        assertEquals(result, ExpectedResult);
    }
    
    /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity with a Department which doesn't exist.
     */
    @Test(expected = SQLException.class)
    public void testInsertActivity14() throws Exception {
        System.out.println("insertActivity");
        String type = "Mechanics";
        String description = "Change tube";
        Integer time = 90;
        Integer week_num= 22;
        Department department= new Department("Arzano - Molding");
        Competence c= new Competence(1, "PAV - Certification");
        ArrayList<Competence> skill = new ArrayList<>();
        skill.add(c);
        Material material= new Material("Marble");
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(material);
        int result = as.insertActivity(type, description, time, skill, materials, week_num, department);
        int ExpectedResult = 0;
        assertEquals(result, ExpectedResult);
    }
    
        
    /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity with an invalid material(empty).
     */
    @Test(expected = SQLException.class)
    public void testInsertActivity15() throws Exception {
        System.out.println("insertActivity");
        String type = "Mechanics";
        String description = "Change tube";
        Integer time = 90;
        Integer week_num= 12;
        Department department= new Department("Arzano - Molding");
         Competence c= new Competence(1, "PAV - Certification");
        ArrayList<Competence> skill = new ArrayList<>();
        skill.add(c);
        ArrayList<Material> materials = new ArrayList<>();
        int result = as.insertActivity(type, description, time, skill, materials, week_num, department);
        int ExpectedResult = 1;
        assertEquals(result, ExpectedResult);
    }
    
     
    /**
     * Test of insertActivity method, of class ActivityService inserting a new
     * Activity with an invalid material (it is not included among the materials bought by the company).//la deve creare lo stesso
     */
    @Test(expected = SQLException.class)
    public void testInsertActivity16() throws Exception {
        System.out.println("insertActivity");
        String type = "Mechanics";
        String description = "Change tube";
        Integer time = 90;
        Integer week_num= 22;
        Department department= new Department("Arzano - Molding");
         Competence c= new Competence(1, "PAV - Certification");
        ArrayList<Competence> skill = new ArrayList<>();
        skill.add(c);
        Material material= new Material("Plastic");
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(material);
        int result = as.insertActivity(type, description, time, skill, materials, week_num, department);
        int ExpectedResult = 1;
        assertEquals(result, ExpectedResult);
    }
}
