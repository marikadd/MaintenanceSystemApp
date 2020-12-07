/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Activity;

import java.util.*;
import model.Competences.Competence;
import model.Department.Department;

/**
 *
 * @author Group9
 */

//Adaptee
public class MaintenanceActivity {
     
    private Integer ID;
    private String type;
    private String description;
    private Integer time;
    private List<Competence> skill;
    private Integer week_num;
    private Department department;
    private Boolean assigned;

    public MaintenanceActivity() {}
    
    public MaintenanceActivity(int ID, String type, String description, int time, Boolean assigned, int week_num, Department dep) {
        this.ID = ID;
        this.type = type;
        this.description = description;
        this.time = time;
        this.assigned = assigned;
        this.week_num = week_num;
        this.department = dep;
    }

    public int getID() {
        return ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public List<Competence> getSkill() {
        return skill;
    }

    public void setSkill(List<Competence> skill) {
        this.skill = skill;
    }

    public Boolean getAssigned() {
        return assigned;
    }

    public void setAssigned(Boolean assigned) {
        this.assigned = assigned;
    }

    public Integer getWeekNum() {
        return week_num;
    }

    public void setWeekNum(Integer week_num) {
        this.week_num = week_num;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
    
    
}