/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Activity;

import model.Department.Department;

/**
 *
 * @author Group9
 */
public class ActivityLinked extends MaintenanceActivity implements ActivityInterface {

    private boolean activityLinked;

    public ActivityLinked(boolean activityLinked, int ID, String type, String description, int time, Boolean assigned, int week_num, Department dep) {
        super(ID, type, description, time, assigned, week_num, dep);
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
