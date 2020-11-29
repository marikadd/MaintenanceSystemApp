/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Activity;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import model.Competences.Competence;

/**
 *
 * @author Group9
 */

public class MaintenanceActivity {
     
    private Integer ID;
    private String type;
    private String description;
    private Integer time;
    private List<Competence> skill;
    private Boolean assigned;

    public MaintenanceActivity() {}
    
    public MaintenanceActivity(int ID, String type, String description, int time, Boolean assigned) {
        this.ID = ID;
        this.type = type;
        this.description = description;
        this.time = time;
        this.assigned = assigned;
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
    
}