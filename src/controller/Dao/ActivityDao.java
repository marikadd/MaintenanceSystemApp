/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Dao;

import configuration.Database.ConnectionForTest;
import configuration.Database.DBAbstractFactory;
import configuration.Database.DBFactoryContext;
import configuration.Database.DBManager;
import configuration.Database.DBProduct;
import configuration.Exceptions.ActivityNotFoundException;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import model.Activity.MaintenanceActivity;
import model.Competences.Competence;
import model.Department.Department;
import model.Material.Material;
import model.Users.Maintainer;

/**
 *
 * @author Group9
 */
public class ActivityDao {

    private static ActivityDao activityDao;
    private CompetencesDao compDao;
    private DBProduct dbProduct;
    private ConnectionForTest cft;

    //Singleton
    private ActivityDao() {
    }

    public static ActivityDao init() {

        if (activityDao == null) {
            synchronized (ActivityDao.class) {
                if (activityDao == null) {
                    activityDao = new ActivityDao();
                    activityDao.compDao = CompetencesDao.init();
                    activityDao.cft = ConnectionForTest.init();
                    DBAbstractFactory dbFactory = new DBFactoryContext();
                    activityDao.dbProduct = dbFactory.getInstance(DBManager.instanceType);

                }
            }
        }
        return activityDao;
    }

    public int insertActivity(String type, String description, Integer time_activity, Integer week_num, Department dep)
            throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        validateActivity(type, description, time_activity);

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "INSERT INTO MaintenanceActivity(Type_Activity, Site, Description, Time_Activity, Week_Number, Assigned) "
                + "VALUES(?,?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, type);
        ps.setString(2, dep.getArea());
        ps.setString(3, description);
        ps.setInt(4, time_activity);
        ps.setInt(5, week_num);
        ps.setBoolean(6, false);

        int affectedRow = ps.executeUpdate();

        if (affectedRow > 0) {
            String sqlquery = "SELECT id From MaintenanceActivity "
                    + "where id = (SELECT max(id) FROM MaintenanceActivity)";

            PreparedStatement psSql = con.prepareStatement(sqlquery);

            ResultSet rsSql = psSql.executeQuery();

            if (rsSql.next()) {
                return rsSql.getInt("id");
            } else {
                throw new SQLException("Row not insert");
            }

        } else {
            throw new SQLException("Row not insert");
        }

    }

    public int insertCompentecesInActivity(int activityId, List<Competence> competences) throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "INSERT INTO Activity_Competences "
                + "VALUES(?,?)";

        int result = 0;
        for (Competence c : competences) {

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, c.getId());
            ps.setInt(2, activityId);

            result = ps.executeUpdate();
        }

        return result;
    }

    public int insertMaterialsInActivity(int activityId, List<Material> materials) throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "INSERT INTO Activity_Materials "
                + "VALUES(?,?)";

        int result = 0;
        for (Material m : materials) {

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, activityId);
            ps.setString(2, m.getType());

            result = ps.executeUpdate();
        }

        return result;
    }

    public int updateActivity(Integer id, String type, String description, int time_activity, Integer week_num, Department dep)
            throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        validateUpdate(type, description, time_activity, week_num);

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "UPDATE MaintenanceActivity SET Type_Activity = coalesce(?,Type_Activity), Site = coalesce(?, Site),"
                + " Description = coalesce(?,Description), Time_Activity = coalesce(?,Time_Activity), Week_Number = coalesce(?,Week_number)"
                + " WHERE ID = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, type);
        ps.setString(2, dep.getArea());
        ps.setString(3, description);
        ps.setInt(4, time_activity);
        ps.setInt(5, week_num);
        ps.setInt(6, id);

        int result = ps.executeUpdate();

        if (result == 0) {
            throw new UnsuccessfulUpdateException("No rows update");
        }

        return result;
    }

    public void assignmentActivity(Integer id)
            throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "UPDATE MaintenanceActivity SET Assigned = true"
                + " WHERE ID = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);

        boolean result = ps.execute();
    }
    
    public void unassignmentActivity(Integer id)
            throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "UPDATE MaintenanceActivity SET Assigned = false"
                + " WHERE ID = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);

        boolean result = ps.execute();
    }

    public boolean checkActivityAssigned(int activityId) throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "SELECT * FROM MaintenanceActivity WHERE ID = ? AND "
                + "Assigned = true";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, activityId);

        ResultSet rs = ps.executeQuery();

        return rs.next();

    }

    public int deleteActivity(Integer id) throws SQLException, UnsuccessfulUpdateException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "DELETE FROM MaintenanceActivity WHERE ID = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);

        int result = ps.executeUpdate();

        return result;

    }

    public MaintenanceActivity findActivityByID(int ID) throws SQLException, ActivityNotFoundException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "select * from MaintenanceActivity " + "where ID = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, ID);

        ResultSet rs = ps.executeQuery();

        MaintenanceActivity activity = null;

        if (rs.next()) {
            activity = getMaintenanceActivity(rs);
        } else {
            throw new ActivityNotFoundException("Activity " + ID + " not found");
        }

        return activity;
    }

    public List<MaintenanceActivity> findActivitiesByWeekNum(int week_num) throws SQLException, ActivityNotFoundException {

        Connection con = dbProduct.connectToDB();

        String query = "select * from MaintenanceActivity " + "where Week_Number = ? AND Assigned=false";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, week_num);

        ResultSet rs = ps.executeQuery();

        List<MaintenanceActivity> activities = new ArrayList<>();

        while (rs.next()) {
            activities.add(getMaintenanceActivity(rs));
        }

        return activities;
    }

    public List<MaintenanceActivity> findAllActivities() throws SQLException, ActivityNotFoundException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);
        String query = "select * from MaintenanceActivity";

        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<MaintenanceActivity> activities = new ArrayList<>();

        while (rs.next()) {
            activities.add(getMaintenanceActivity(rs));
        }

        return activities;
    }

    // non dovrebbe esserci una lista di attività dato che ne assrgno sempre
    // una alla volta
    public int assignActivityToMaintainer(Maintainer maintainer, List<Integer> listId)
            throws SQLException, UnsuccessfulUpdateException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "INSERT INTO Activity_Maintainers VALUES(?,?)";

        PreparedStatement ps = con.prepareStatement(query);

        int result = 0;

        for (Integer id : listId) {

            ps.setString(1, maintainer.getUsername());
            ps.setInt(2, id);
            result = ps.executeUpdate();

            if (result == 0) {
                throw new UnsuccessfulUpdateException("Cannot assign activity #" + id + " to user " + maintainer.getUsername());
            }
        }

        return result;
    }
    
    public int deassignActivity(Integer id)
            throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "DELETE FROM Activity_Maintainers WHERE Activity_Maintainer_ID = ?";

        PreparedStatement ps = con.prepareStatement(query);

        int result = 0;
            
        ps.setInt(1, id);
        result = ps.executeUpdate();

        if (result == 0) {
            throw new UnsuccessfulUpdateException("Cannot deaassign activity #" + id);
        }
        
        unassignmentActivity(id);

        return result;
    }

    /*
    public int deassignActivityMaintainer(String username) throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "UPDATE MaintenanceActivity SET Assigned = false WHERE ID IN ("
                + "SELECT Activity_Maintainer_ID FROM Activity_Maintainers WHERE "
                + "username_maintainer = ?)";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);

        return ps.executeUpdate();

    }
    */
    
    public List<MaintenanceActivity> findActivityByMaintainer(String username) throws SQLException {
        
        Connection con = dbProduct.connectToDB();
        cft.setConn(con);
        
        String query = "SELECT * FROM MaintenanceActivity WHERE ID IN ("
                + "SELECT Activity_Maintainer_ID FROM Activity_Maintainers WHERE "
                + "username_maintainer = ?)";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);
        
        ResultSet rs = ps.executeQuery();
        
        List<MaintenanceActivity> activities = new ArrayList<>();
        while(rs.next()) {
            activities.add(getMaintenanceActivity(rs));
        }
        
        return activities;
        
    }
    
    public TreeMap<Integer, String> findAssignedActivities() throws SQLException {

        Connection con = dbProduct.connectToDB();

        String query = "select * from Activity_Maintainers";
        
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        TreeMap<Integer, String> result = new TreeMap<>();

        while (rs.next()) {
            result.put(rs.getInt(2), rs.getString(1));
        }

        return result;
    }

    /*
    public List<MaintenanceActivity> findActivitiesInMaintainer(String username) throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

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

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

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
     */
    private MaintenanceActivity getMaintenanceActivity(ResultSet rs) throws SQLException {

        Integer activityId = rs.getInt("ID");
        Department department = new Department(rs.getString("Site"));

        MaintenanceActivity activity = null;
        List<Competence> skills = new ArrayList<Competence>();

        skills = compDao.getCompetencesByActivityId(activityId);
        activity = new MaintenanceActivity(activityId, rs.getString("Type_Activity"), rs.getString("Description"), rs.getInt("Time_Activity"), rs.getBoolean("Assigned"), rs.getInt("Week_Number"), department);
        activity.setSkill(skills);

        return activity;
    }

    public int setMaintainerActivityDays(String username, Integer maId, List<Integer> days) throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "Insert into MaintainerActivityDay(username, ma_id, week_day) VALUES(?, ?, ?)";

        int result = 0;
        for (Integer day : days) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setInt(2, maId);
            ps.setInt(3, day);

            result += ps.executeUpdate();

        }

        return result;

    }

    public double getSumActivityDay(String username, int day) throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "SELECT sum(Time_Activity) as result FROM MaintenanceActivity WHERE "
                + "id in (SELECT ma_id FROM MaintainerActivityDay WHERE username = ? "
                + " AND week_day = ?)";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);
        ps.setInt(2, day);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getDouble("result");
        } else {
            return 0;
        }

    }

    private void validateActivity(String type, String description, Integer time) throws InvalidParameterObjectException {

        if (type == null || type.isBlank()) {
            throw new InvalidParameterObjectException("Activity type must be not null");
        }

        if (type.length() > 20) {
            throw new InvalidParameterObjectException("Activity type must be length at most 20 characters");
        }

        if (description == null || description.isBlank()) {
            throw new InvalidParameterObjectException("Activity description must be not null");
        }

        if (description.length() > 30) {
            throw new InvalidParameterObjectException("Activity description must be length at most 30 characters");
        }

        if (time == null || time.toString().isBlank()) {
            throw new InvalidParameterObjectException("Activity time must be not null");
        }

        if (time <= 0) {
            throw new InvalidParameterObjectException("Activity time must be a positive integer");
        }

        if (!time.toString().matches("[0-9]+")) {
            throw new InvalidParameterObjectException("Field Time must be numeric");

        }
    }

    private void validateUpdate(String type, String description, Integer time, Integer week_num) throws InvalidParameterObjectException {

        if (type != null) {
            if (type.length() > 20) {
                throw new InvalidParameterObjectException("Activity type must be length at most 20 characters");
            }
        }

        if (description != null) {
            if (description.length() > 30) {
                throw new InvalidParameterObjectException("Activity description must be length at most 30 characters");
            }
        }

        if (time != null) {
            if (time <= 0) {
                throw new InvalidParameterObjectException("Activity time must be a positive integer");
            }

            if (!time.toString().matches("[0-9]+")) {
                throw new InvalidParameterObjectException("Field Time must be numeric");
            }
        }

    }
}
