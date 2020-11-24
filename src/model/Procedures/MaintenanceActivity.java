/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.Procedures;
import java.io.*;
import java.util.*;
import model.Competences.Competence;

/**
 *
 * @author Group9
 */

public class MaintenanceActivity {
    private final int ID;
    private Category category;
    private Department department;
    private ActivityType type;
    private String description;
    private int time;
    private Boolean interruptible;
    private int weekNumber;
    private File SMP;
    private ArrayList<Competence> skill;
    private Boolean assigned;

    public MaintenanceActivity(int ID, Category category, Department department, ActivityType type, String description, int time, Boolean interruptible, int weekNumber) {
        this.ID = ID;
        this.category = category;
        this.department = department;
        this.type = type;
        this.description = description;
        this.time = time;
        this.interruptible = interruptible;
        this.weekNumber = weekNumber;
        this.SMP = null;
        this.assigned = false;
    }

    public MaintenanceActivity(int ID, Category category, Department department, ActivityType type, String description, int time, Boolean interruptible, int weekNumber, File SMP) {
        this.ID = ID;
        this.category = category;
        this.department = department;
        this.type = type;
        this.description = description;
        this.time = time;
        this.interruptible = interruptible;
        this.weekNumber = weekNumber;
        this.SMP = SMP;
    }

    public int getID() {
        return ID;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Boolean getInterruptible() {
        return interruptible;
    }

    public void setInterruptible(Boolean interruptible) {
        this.interruptible = interruptible;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public File getSMP() {
        return SMP;
    }

    public void setSMP(File SMP) {
        this.SMP = SMP;
    }

    public ArrayList<Competence> getSkill() {
        return skill;
    }

    public void setSkill(ArrayList<Competence> skill) {
        this.skill = skill;
    }

    public Boolean getAssigned() {
        return assigned;
    }

    public void setAssigned(Boolean assigned) {
        this.assigned = assigned;
    }
    
    public Boolean isInterruptible(){
        return this.interruptible;
    }
    
}
