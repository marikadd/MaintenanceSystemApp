/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Activity;

/**
 *
 * @author Group9
 */

public class ActivityAdapter extends MaintenanceActivity implements ActivityTarget {
    
    private boolean activityLinked;

    public ActivityAdapter(boolean activityLinked, int ID, String type, String description, int time, Boolean assigned) {
        super(ID, type, description, time, assigned);
        this.activityLinked = activityLinked;
    }
    
    public boolean isActivityLinked() {
        return activityLinked;
    }

    public void setActivityLinked(boolean activityLinked) {
        this.activityLinked = activityLinked;
    }

    @Override
    public String toString() {
        return "ActivityAdapter{" + "activityLinked=" + activityLinked + '}';
    }   
    
}
