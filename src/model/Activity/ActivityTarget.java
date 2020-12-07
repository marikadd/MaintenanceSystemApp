/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Activity;

import java.util.List;
import model.Competences.Competence;
import model.Department.Department;

/**
 *
 * @author Group9
 */

public interface ActivityTarget {
    
    public int getID();

    public String getType();
    
    public void setType(String type);

    public String getDescription();

    public void setDescription(String description);

    public Integer getTime();

    public void setTime(Integer time);
    
    public Integer getWeekNum();

    public void setWeekNum(Integer week_num);
    
    public Department getDepartment();

    public void setDepartment(Department dep);

    public List<Competence> getSkill();

    public void setSkill(List<Competence> skill);

    public Boolean getAssigned();

    public void setAssigned(Boolean assigned);
    
    public boolean isActivityLinked();
    
    public void setActivityLinked(boolean activityLinked);
    
    public String toString();

}
