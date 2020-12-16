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
import java.util.List;
import java.util.TreeMap;
import model.Activity.ActivityNumRecord;
import model.Activity.MaintenanceActivity;
import model.Activity.UsernameResultActivity;
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

    /**
     * Pattern Singleton
     */
    private ActivityDao() {
    }

    /**
     * Creates a singleton for the current class. In order to avoid conflicts
     * between threads, the method uses the synchronized construct.
     *
     * @return an instance of the current class
     */
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

    /**
     * Inserts inside the table MaintenanceActivity an activity whose
     * type,description,time activity week number, department and the number of
     * the activity is passed as an input
     *
     * @param type a string representing the type of the activity
     * @param description a string describing the activity
     * @param time_activity an integer representing the duration of the activity
     * @param week_num an integer representing the number of the current week
     * @param dep the department associated with the activity
     * @param actNumRecord is an object that represents the number di righe
     * inserite all'interno della tabella
     * @return the ID of the activity that has been inserted
     * @throws SQLException if the new row has not been inserted
     * @throws InvalidParameterObjectException if the activity attributes are
     * null or don't respect the constraints defined inside the database
     */
    public int insertActivity(String type, String description, Integer time_activity, Integer week_num, Department dep, ActivityNumRecord actNumRecord)
            throws SQLException, InvalidParameterObjectException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        validateActivity(type, description, time_activity, dep, week_num);

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

        actNumRecord.setNumRecord(affectedRow);

        if (affectedRow > 0) {
            String sqlquery = "SELECT id From MaintenanceActivity "
                              + "where id = (SELECT max(id) FROM MaintenanceActivity)";

            PreparedStatement psSql = con.prepareStatement(sqlquery);

            ResultSet rsSql = psSql.executeQuery();

            if (rsSql.next()) {
                con.close();
                return rsSql.getInt("id");
            } else {
                con.close();
                throw new SQLException("Row not insert");
            }
        } 
        else {
            con.close();
            throw new SQLException("Row not insert");
        }

    }

    /**
     * Associates a competence to an activity by executing an insert inside the
     * table Activity_Competences
     *
     * @param activityId an int representing the activity id
     * @param competences a list of competences to associate to an activity
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     */
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
        con.close();
        return result;
    }

    /**
     * Associates a material to an activity by executing an insert inside the
     * table Activity_Materials
     *
     * @param activityId an int representing the activity id
     * @param materials a list of materials to associate to an activity
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     */
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
        con.close();
        return result;
    }

    /**
     * Updates the activity type, department,description, time and week number
     * inside the table MaintenanceActivity. It is not necessary that every
     * field has to be modified.
     *
     * @param id an integer representing the activity id
     * @param type a string representing the type of the activity
     * @param description a string representing a description of the activity
     * @param time_activity an integer representing the duration of the activity
     * @param week_num an integer representing the number of the current week
     * @param dep the department associated with the activity
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     * @throws UnsuccessfulUpdateException if no row has been updated inside the
     * table
     * @throws InvalidParameterObjectException if the activity attributes are
     * null or don't respect the constraints defined inside the database
     */
    public int updateActivity(Integer id, String type, String description, Integer time_activity, Integer week_num, Department dep)
            throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        validateUpdate(id, type, description, time_activity, week_num, dep);

        String query = "UPDATE MaintenanceActivity SET Type_Activity = coalesce(?,Type_Activity), Site = coalesce(?, Site),"
                + " Description = coalesce(?,Description), Time_Activity = coalesce(?,Time_Activity), Week_Number = coalesce(?,Week_number)"
                + " WHERE ID = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, type);
        ps.setString(2, dep.getArea());
        ps.setString(3, description);
        ps.setObject(4, time_activity);
        ps.setObject(5, week_num);
        ps.setObject(6, id);

        int result = ps.executeUpdate();

        if (result == 0) {
            throw new UnsuccessfulUpdateException("No rows update");
        }
        con.close();
        return result;
    }

    /**
     * Changes the state of an activity, setting it as assigned.
     *
     * @param id an integer representing the activity id
     * @throws SQLException if a database access error occurs
     */
    public void assignmentActivity(Integer id)
            throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "UPDATE MaintenanceActivity SET Assigned = true"
                + " WHERE ID = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);

        boolean result = ps.execute();
        con.close();
    }

    /**
     * Changes the state of an activity, setting it as unassigned.
     *
     * @param id an integer representing the activity id
     * @throws SQLException if a database access error occurs
     */
    public void unassignmentActivity(Integer id)
            throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "UPDATE MaintenanceActivity SET Assigned = false"
                + " WHERE ID = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);

        boolean result = ps.execute();
        con.close();
    }

    /**
     * Checks if an activity has been assigned
     *
     * @param activityId an int representing the activity id
     * @return the username of the maintainer to which the activity is
     * associated
     * @throws SQLException if a database access error occurs
     */
    public UsernameResultActivity checkActivityAssigned(int activityId) throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "SELECT am.Username_Maintainer as username "
                        + "FROM MaintenanceActivity ma JOIN Activity_Maintainers am "
                        + "ON (ma.ID = am.Activity_Maintainer_ID) "
                        + "WHERE ID = ? AND "
                        + "Assigned = true";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, activityId);

        ResultSet rs = ps.executeQuery();

        UsernameResultActivity ura = new UsernameResultActivity();
        ura.setResult(rs.next());

        if (ura.isResult()) {
            ura.setUsername(rs.getString("username"));
        }
        con.close();
        return ura;

    }

    /**
     * Deletes an activity from the table MaintenanceActivity
     *
     * @param id an integer representing the activity id
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     */
    public int deleteActivity(Integer id) throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "DELETE FROM MaintenanceActivity WHERE ID = ?";

        if (id == null) {
            id = -1;
        }

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);

        int result = ps.executeUpdate();
        con.close();
        return result;

    }

    /**
     * Executes a query searching for an activity starting from its id.
     *
     * @param ID an integer representing
     * @return a MaintenanceActivity
     * @throws SQLException if a database access error occurs
     * @throws ActivityNotFoundException if the activity with the id passed as
     * an input doesn't exist
     * @throws InvalidParameterObjectException if the activity attributes are
     * null or don't respect the constraints defined inside the database
     */
    public MaintenanceActivity findActivityByID(Integer ID) throws SQLException, ActivityNotFoundException, InvalidParameterObjectException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "select * from MaintenanceActivity " + "where ID = ?";

        if (ID == null) {
            ID = -1;
        }

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, ID);

        ResultSet rs = ps.executeQuery();

        MaintenanceActivity activity = null;

        if (rs.next()) {
            activity = getMaintenanceActivity(rs);
        } else {
            throw new ActivityNotFoundException("Activity " + ID + " not found");
        }
        con.close();
        return activity;
    }

    /**
     * Executes a query searching for the activities of a specific week number,
     * that have not been assigned yet
     *
     * @param week_num an integer representing the number of the current week
     * @return an ArrayList of MaintenanceActivity representing the activities
     * of the specified week.
     * @throws SQLException if a database access error occurs
     * @throws ActivityNotFoundException if the week number passed as an input
     * is null.
     * @throws InvalidParameterObjectException if the week number passed as an
     * input has a value that is not in the range [1,52].
     */
    public List<MaintenanceActivity> findActivitiesByWeekNum(Integer week_num)
            throws SQLException, ActivityNotFoundException, InvalidParameterObjectException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        if (week_num == null) {
            throw new ActivityNotFoundException("Week Num must be not null");
        }

        if (week_num < 1 || week_num > 52) {
            throw new InvalidParameterObjectException("Week num must be in range [1,52]");
        }

        String query = "select * from MaintenanceActivity " 
                       + "where Week_Number = ? AND Assigned=false";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, week_num);

        ResultSet rs = ps.executeQuery();

        List<MaintenanceActivity> activities = new ArrayList<>();

        while (rs.next()) {
            activities.add(getMaintenanceActivity(rs));
        }
        con.close();
        return activities;
    }

    /**
     * Gets all the activities inside the table MaintenanceActivity.
     *
     * @return an ArrayList of MaintenanceActivity
     * @throws SQLException if a database access error occurs
     * @throws InvalidParameterObjectException if the competence associated to
     * the activity is not valid
     */
    public List<MaintenanceActivity> findAllActivities() throws SQLException, InvalidParameterObjectException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);
        String query = "select * from MaintenanceActivity";

        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<MaintenanceActivity> activities = new ArrayList<>();

        while (rs.next()) {
            activities.add(getMaintenanceActivity(rs));
        }
        con.close();
        return activities;
    }

    /**
     * Associates an activity to a maintainer, executing an insert inside the
     * table Activity_Maintainers
     *
     * @param maintainer represents the maintainer to whom associate the
     * activity
     * @param listId a list of the maintainers id
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     * @throws UnsuccessfulUpdateException if the insert can't be performed due
     * to database errors
     */
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
        con.close();
        return result;
    }

    /**
     * Deassociates the activity from a maintainer by performing a delete on the
     * table Activity_Maintainers.
     *
     * @param id an integer representing the id of the activity associated to a
     * maintainer
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     * @throws UnsuccessfulUpdateException if the delete can't be performed due
     * to database errors
     */
    public int deassignActivity(Integer id)
            throws SQLException, UnsuccessfulUpdateException {

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
        
        con.close();
        
        return result;
    }

    /**
     * Deassociates an activity of a specific day from a maintainer by
     * performing a delete on the table MaintainerActivityDay.
     *
     * @param id representing the id of the activity associated to a maintainer
     * in a specific day
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     * @throws UnsuccessfulUpdateException if the delete can't be performed due
     * to database errors
     */
    public int deassignDayActivity(Integer id)
            throws SQLException, UnsuccessfulUpdateException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "DELETE FROM MaintainerActivityDay WHERE ma_id = ?";

        PreparedStatement ps = con.prepareStatement(query);

        int result = 0;

        ps.setInt(1, id);
        result = ps.executeUpdate();

        if (result == 0) {
            throw new UnsuccessfulUpdateException("Cannot deaassign activity #" + id);
        }
        con.close();
        
        return result;
    }

    /**
     * Executes a query searching for the maintainer associated to a specific
     * activity.
     *
     * @param activityId an integer representing the activity ID.
     * @return a string representing the maintainer's username.
     * @throws SQLException if a database access error occurs
     */
    public String findMaintainerByActivityId(Integer activityId) throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "SELECT Username_Maintainer as username FROM Activity_Maintainers "
                        + "WHERE Activity_Maintainer_ID = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, activityId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            
            con.close();
            
            return rs.getString("username");
            
        } else {
            
            con.close();
            
            return null;
        }

    }

    /**
     * Executes a query searching an activity associated to a maintainer, based
     * on the username
     *
     * @param username a string representing the username of the maintainer
     * @return an ArrayList of MaintenanceActivity
     * @throws SQLException if a database access error occurs
     * @throws InvalidParameterObjectException if the competence associated to
     * the activity is not valid
     */
    public List<MaintenanceActivity> findActivityByMaintainer(String username) throws SQLException, InvalidParameterObjectException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "SELECT * FROM MaintenanceActivity WHERE ID IN ("
                       + "SELECT Activity_Maintainer_ID FROM Activity_Maintainers WHERE "
                       + "username_maintainer = ?)";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        List<MaintenanceActivity> activities = new ArrayList<>();
        while (rs.next()) {
            activities.add(getMaintenanceActivity(rs));
        }
        con.close();
        return activities;

    }

    /**
     * Creates a TreeMap whose keys are the ID of the table Activity_Maintainers
     * and its values are the maintainers' username
     *
     * @return a TreeMap whose keys are integer representing the ID and values
     * are string reppresenting the maintainers username
     * @throws SQLException if a database access error occurs
     */
    public TreeMap<Integer, String> findAssignedActivities() throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "select * from Activity_Maintainers";

        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        TreeMap<Integer, String> result = new TreeMap<>();

        while (rs.next()) {
            result.put(rs.getInt(2), rs.getString(1));
        }
        con.close();
        
        return result;
    }

    /**
     * Creates a MaintenanceActivity starting from the result of a query.
     *
     * @param rs is the ResultSet obtained from the execution of a query
     * @return an instance of the class MaintenanceActivity
     * @throws SQLException if a database access error occurs
     * @throws InvalidParameterObjectException if the competence associated to
     * the activity is not valid
     */
    private MaintenanceActivity getMaintenanceActivity(ResultSet rs) throws SQLException, InvalidParameterObjectException {

        Integer activityId = rs.getInt("ID");
        Department department = new Department(rs.getString("Site"));

        MaintenanceActivity activity = null;
        List<Competence> skills = new ArrayList<Competence>();

        skills = compDao.getCompetencesByActivityId(activityId);
        activity = new MaintenanceActivity(activityId, rs.getString("Type_Activity"), rs.getString("Description"), rs.getInt("Time_Activity"), rs.getBoolean("Assigned"), rs.getInt("Week_Number"), department);
        activity.setSkill(skills);

        return activity;
    }

    /**
     * Sets the day on which a maintainer must do an activity by executing an
     * insert inside the table MaintainerActivityDay
     *
     * @param username a string representing the maintainer's username
     * @param maId an integer representing the maintainer's id
     * @param days a list of integer representing the days of the week
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     */
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
        con.close();
        return result;

    }

    /**
     * Sums the time required to perform a certain activity (by the maintainer)
     * on a specific day of the week.
     *
     * @param username a string representing the maintainer's username
     * @param day an integer representing a day of the week (e.g. 1 for Monday,
     * 2 for Tuesday ecc).
     * @return a double representing the sum of the minutes required for a
     * specific activity
     * @throws SQLException if a database access error occurs
     */
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
            con.close();
            return rs.getDouble("result");
        } else {
            con.close();
            return 0;
        }

    }

    /**
     * Checks if the activity attributes taken in input are valid and respect
     * the constraints defined in the database tables.
     *
     * @param type a string representing the type of the activity
     * @param description a string representing the description of the activity
     * @param time an integer representing the time requested for the activity
     * @param dep the department associated to an activity
     * @param weekNum an integer representing the number of the week
     * @throws InvalidParameterObjectException if any of the attributes don't
     * respect the constraints
     */
    private void validateActivity(String type, String description, Integer time, Department dep, Integer weekNum)
            throws InvalidParameterObjectException {

        if (dep == null) {
            throw new InvalidParameterObjectException("Department must be inserted");
        }

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
        if (time > 420) {
            throw new InvalidParameterObjectException("Activity time must be a number less than or equal to 420");
        }

        if (!time.toString().matches("[0-9]+")) {
            throw new InvalidParameterObjectException("Field Time must be numeric");

        }

        if (weekNum == null) {
            throw new InvalidParameterObjectException("Field Week Num must be not null");

        }

        if (weekNum < 1 || weekNum > 52) {
            throw new InvalidParameterObjectException("Week Num must be between 1 and 52");
        }

    }

    /**
     * Checks if the activity attributes taken in input are valid and respect
     * the constraints defined in the database tables.
     *
     * @param id an integer representing the activity id
     * @param type a string representing the type of the activity
     * @param description a string representing the description of the activity
     * @param time an integer representing the time requested for the activity
     * @param week_num an integer representing the number of the week
     * @param dep the department associated to an activity
     * @throws InvalidParameterObjectException if any of the attributes don't
     * respect the constraints
     */
    private void validateUpdate(Integer id, String type, String description, Integer time, Integer week_num, Department dep) throws InvalidParameterObjectException {

        if (id == null) {
            throw new InvalidParameterObjectException("ID number must be not null");
        }

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

            if (time > 420) {
                throw new InvalidParameterObjectException("Activity time must be a number less than or equal to 420");
            }

            if (!time.toString().matches("[0-9]+")) {
                throw new InvalidParameterObjectException("Field Time must be numeric");
            }
        }

        if (week_num != null) {
            if (week_num < 1 || week_num > 52) {
                throw new InvalidParameterObjectException("Week Num must be between 1 and 52");
            }
        }

    }
}
