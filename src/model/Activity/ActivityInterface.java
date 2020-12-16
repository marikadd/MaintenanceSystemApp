package model.Activity;

import java.util.List;
import model.Competences.Competence;
import model.Department.Department;

/**
 * This interface has the purpose of representing the common methods that each
 * activity must implement. Each activity has a description that has to be
 * initialized, the week number ecc.
 *
 * @author Group9
 */
public interface ActivityInterface {

    /**
     *
     * @return an integer representing the activity id
     */
    public int getID();

    /**
     *
     * @return a String representing the activity type
     */
    public String getType();

    /**
     * sets the activity type
     *
     * @param type a string representing the type of the activity
     */
    public void setType(String type);

    /**
     *
     * @return a string representing the activity description
     */
    public String getDescription();

    /**
     * sets the activity description
     *
     * @param description a string representing the activity description
     */
    public void setDescription(String description);

    /**
     *
     * @return an integer representing the time required to execute the activity
     */
    public Integer getTime();

    /**
     * sets the time required to execute the activity
     *
     * @param time an integer representing the time required to execute the
     * activity
     */
    public void setTime(Integer time);

    /**
     *
     * @return an integer representing the number of the week associated to an
     * activity
     */
    public Integer getWeekNum();

    /**
     * sets the week number associated to an activity
     *
     * @param week_num an integer representing the number of the week associated
     * to an activity
     */
    public void setWeekNum(Integer week_num);

    /**
     *
     * @return the department associated to the activity
     */
    public Department getDepartment();

    /**
     * sets the department associated to an activity
     *
     * @param dep a department associated to an activity
     */
    public void setDepartment(Department dep);

    /**
     *
     * @return a list of competence associated to an activity
     */
    public List<Competence> getSkill();

    /**
     * associates a list of competences to an activity
     *
     * @param skill a list of Competence
     */
    public void setSkill(List<Competence> skill);

    /**
     *
     * @return a boolean that is true if the activity has been assigned,
     * otherwise false
     */
    public Boolean getAssigned();

    /**
     * assigns an activity
     *
     * @param assigned a boolean which is true if the activity has to be
     * assigned
     */
    public void setAssigned(Boolean assigned);

    /**
     * states if the activity has been associated to a specific maintainer
     *
     * @return a boolean that is true if the activity is linked to a maintainer,
     * otherwise false
     */
    public boolean isActivityLinked();

    /**
     * links an activity to a maintainer
     *
     * @param activityLinked a boolean that is true if the activity has to be
     * linked to a maintainer, otherwise false
     */
    public void setActivityLinked(boolean activityLinked);

    public String toString();

}
