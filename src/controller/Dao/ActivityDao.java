/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Dao;

import configuration.Database.DBFactory;
import configuration.Exceptions.ActivityNotFoundException;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import configuration.Exceptions.UsernotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.Activity.MaintenanceActivity;
import model.Competences.Competence;
import model.Users.Maintainer;

/**
 *
 * @author Group9
 */
public class ActivityDao {

    private static ActivityDao activityDao;
    private CompetencesDao compDao;
    
    public static ActivityDao init() {
        if (activityDao == null) {
            activityDao = new ActivityDao();
            activityDao.compDao = CompetencesDao.init();
        }
        return activityDao;
    }

    public int insertActivity(String type, String description, Integer time_activity) 
            throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        validateActivity(type, description, time_activity);

        Connection con = DBFactory.connectToDB();

        String query = "INSERT INTO MaintenanceActivity(Type_Activity, Description, Time_Activity, Assigned) "
                       + "VALUES(?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, type);
        ps.setString(2, description);
        ps.setInt(3,time_activity);
        ps.setBoolean(4, false);
        
        int affectedRow = ps.executeUpdate();
        
        if(affectedRow > 0) {
            String sqlquery = "SELECT id From MaintenanceActivity "
                    + "where id = (SELECT max(id) FROM MaintenanceActivity)";
        
            PreparedStatement psSql = con.prepareStatement(sqlquery);
        
            ResultSet rsSql = psSql.executeQuery();
            
            if(rsSql.next()) {
                return rsSql.getInt("id");
            } else {
                throw new SQLException("Row not insert");
            }
            
        } else {
                throw new SQLException("Row not insert");
            }
        
    }
    
    public void insertCompentecesInActivity(int activityId, List<Competence> competences) throws SQLException {
        
        Connection con = DBFactory.connectToDB();

        String query = "INSERT INTO Activity_Competences "
                       + "VALUES(?,?)";
        
       for(Competence c: competences) {
            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, c.getId());
            ps.setInt(2, activityId);
            
            ps.execute();
        }
    }

    public void updateActivity(Integer id, String type, String description, int time_activity) 
            throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        validateActivity(type, description, time_activity);
        
        Connection con = DBFactory.connectToDB();

        String query = "UPDATE MaintenanceActivity SET Type_Activity = ?, Description = ?, Time_Activity = ?"
                       + " WHERE ID = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, type);
        ps.setString(2, description);
        ps.setInt(3, time_activity);
        ps.setInt(4, id);

        int result = ps.executeUpdate();
        
        if(result == 0) {
            throw new UnsuccessfulUpdateException("No rows update");
        }
        
    }
    
    public void assignmentActivity(Integer id) 
            throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        Connection con = DBFactory.connectToDB();

        String query = "UPDATE MaintenanceActivity SET Assigned = true"
                       + " WHERE ID = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);

        boolean result = ps.execute();
    }

    public void deleteActivity(Integer id) throws SQLException, UnsuccessfulUpdateException {

        Connection con = DBFactory.connectToDB();

        String query = "DELETE FROM MaintenanceActivity WHERE ID = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);

        boolean result = ps.execute();
    }

    public MaintenanceActivity findActivityByID(int ID) throws SQLException, ActivityNotFoundException {

        Connection con = DBFactory.connectToDB();

        String query = "select * from MaintenanceActivity " + "where ID = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, ID);

        ResultSet rs = ps.executeQuery();

        MaintenanceActivity activity = null;

        if (!rs.wasNull()) {
            activity = getMaintenanceActivity(rs);
        } else {
            throw new ActivityNotFoundException("Activity " + ID + " not found");
        }

        return activity;
    }

    public List<MaintenanceActivity> findAllActivities() throws SQLException, ActivityNotFoundException {

        Connection con = DBFactory.connectToDB();
        String query = "select * from MaintenanceActivity";

        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<MaintenanceActivity> activities = new LinkedList<>();

        while (rs.next()) {
            activities.add(getMaintenanceActivity(rs));
        }

        return activities;
    }

    public void assignActivityToMaintainer(Maintainer maintainer, List<Integer> listId)
            throws SQLException, UnsuccessfulUpdateException {

        Connection con = DBFactory.connectToDB();

        String query = "INSERT INTO Activity_Maintainers VALUES(?,?)";

        PreparedStatement ps = con.prepareStatement(query);

        for (Integer id : listId) {

            ps.setString(1, maintainer.getUsername());
            ps.setInt(2, id);
            int result = ps.executeUpdate();

            if (result == 0) {
                throw new UnsuccessfulUpdateException("Cannot assign activity #" + id + " to user " + maintainer.getUsername());
            }
        }
    }

    public List<MaintenanceActivity> findActivitiesInMaintainer(String username) throws SQLException {

        Connection con = DBFactory.connectToDB();

        String query = "select ma.* from MaintenanceActivity ma "
                        + "where ma.ID IN "
                        + "(select am.Activity_Maintainer_ID "
                        + "from Activity_Maintainers am where am.Username_Maintainer = ?) "
                        + "group by ma.ID";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();
        List<MaintenanceActivity> activities = new ArrayList<>();

        while (rs.next()) {
            activities.add(getMaintenanceActivity(rs));
        }

        return activities;
    }

    public List<MaintenanceActivity> findActivitiesNotInMaintainer(String username) throws SQLException {

        Connection con = DBFactory.connectToDB();

        String query = "select ma.* from MaintenanceActivity ma "
                        + "where ma.ID NOT IN "
                        + "(select am.Activity_Maintainer_ID "
                        + "from Activity_Maintainers am where am.Username_Maintainer = ?) "
                        + "group by ma.ID";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();
        List<MaintenanceActivity> activities = new ArrayList<>();

        while (rs.next()) {
            activities.add(getMaintenanceActivity(rs));
        }

        return activities;
    }

    private MaintenanceActivity getMaintenanceActivity(ResultSet rs) throws SQLException {

        Integer activityId = rs.getInt("ID");
        
        MaintenanceActivity activity = null;
        List<Competence> skills = new ArrayList<Competence>();
        skills = compDao.getCompetencesByActivityId(activityId);
        
        activity = new MaintenanceActivity(activityId,rs.getString("Type_Activity"), rs.getString("Description"), rs.getInt("Time_Activity"), rs.getBoolean("Assigned"));
        activity.setSkill(skills);
        
        return activity;
    }
    
    private void validateActivity(String type, String description, Integer time) throws InvalidParameterObjectException {
        
        if (type == null) {
            throw new InvalidParameterObjectException("Activity type must be not null");
        }

        if(type.length() > 20) {
            throw new InvalidParameterObjectException("Activity type must be length at most 20 characters");
        }
        
        if (description == null) {
            throw new InvalidParameterObjectException("Activity description must be not null");
        }

        if(description.length() > 30) {
            throw new InvalidParameterObjectException("Activity description must be length at most 30 characters");
        }
        
        if(time == null) {
            throw new InvalidParameterObjectException("Activity time must be not null");
        }
        
        if (time <= 0) {
            throw new InvalidParameterObjectException("Activity time must be a positive integer");
        }
        
    }
    
}