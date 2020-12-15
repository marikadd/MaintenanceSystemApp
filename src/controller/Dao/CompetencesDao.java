package controller.Dao;

import configuration.Database.ConnectionForTest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import configuration.Database.DBAbstractFactory;
import configuration.Database.DBFactoryContext;
import configuration.Database.DBManager;
import configuration.Database.DBProduct;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Competences.Competence;
import model.Users.Maintainer;

/**
 *
 * @author Group9
 */
public class CompetencesDao {

    private static CompetencesDao compDao;
    private DBProduct dbProduct;
    private ConnectionForTest cft;

    /**
     * Pattern Singleton
     */
    private CompetencesDao() {
    }

    /**
     * Creates a singleton for the current class. In order to avoid conflicts
     * between threads, the method uses the synchronized construct.
     *
     * @return an instance of the current class
     */
    public static CompetencesDao init() {
        if (compDao == null) {
            synchronized (CompetencesDao.class) {
                if (compDao == null) {
                    compDao = new CompetencesDao();
                    DBAbstractFactory dbFactory = new DBFactoryContext();
                    compDao.cft = ConnectionForTest.init();
                    compDao.dbProduct = dbFactory.getInstance(DBManager.instanceType);
                }
            }
        }
        return compDao;
    }

    /**
     * Assigns competences to the maintainer passed as an input by executing an
     * insert on the table Users_Competences
     *
     * @param maintainer represents the maintainer to wich assign the activity
     * @param listId represents a list of activities to assign to a maintainer
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     */
    public int assignCompetenceToUser(Maintainer maintainer, List<Integer> listId)
            throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "INSERT INTO Users_Competences VALUES(?,?)";

        PreparedStatement ps = con.prepareStatement(query);
        int result = 0;
        for (Integer id : listId) {

            ps.setString(1, maintainer.getUsername());
            ps.setInt(2, id);
            result += ps.executeUpdate();

        }

        return result;
    }

    /**
     * Deassigns a competence from a user by executing a delete on the table
     * Users_Competences
     *
     * @param maintainer represents the maintainer to whom to deassign the
     * activity
     * @param listId represents a list of the competence to deassign
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     */
    public int deassignCompetenceToUser(Maintainer maintainer, List<Integer> listId)
            throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "DELETE FROM Users_Competences WHERE Username = ? AND Id_Competences = ?";

        PreparedStatement ps = con.prepareStatement(query);
        int result = 0;
        for (Integer id : listId) {

            ps.setString(1, maintainer.getUsername());
            ps.setInt(2, id);
            result += ps.executeUpdate();

        }

        return result;
    }

    /**
     * Constructs a list containing all the competences inside the table
     * Competence
     *
     * @return an ArrayList of Competence
     * @throws SQLException if a database access error occurs
     */
    public List<Competence> findAllCompetences() throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "select * from Competence";

        PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        List<Competence> competences = new ArrayList<>();

        while (rs.next()) {
            competences.add(getCompetence(rs));
        }

        return competences;
    }

    /**
     * Finds the competences that aren't associated to a maintainer
     *
     * @param username a string representing the username of the maintainer
     * @return an ArrayList of Competence containing all the competence that a
     * maintainer doesn't have
     * @throws SQLException if a database access error occurs
     */
    public List<Competence> findCompetencesNotInMaintener(String username) throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "select c.* from Competence c "
                + "where c.ID NOT IN "
                + "(select uc.ID_Competences "
                + "from Users_Competences uc where uc.Username = ?) "
                + "group by c.ID, c.Description";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();
        List<Competence> competences = new ArrayList<Competence>();

        while (rs.next()) {
            competences.add(getCompetence(rs));
        }

        return competences;
    }

    /**
     * Finds the competences that are associated to a maintainer
     *
     * @param username a string representing the username of the maintainer
     * @return an ArrayList of Competence containing all the competence that a
     * maintainer has got.
     * @throws SQLException if a database access error occurs
     */
    public List<Competence> findCompetencesInMaintener(String username) throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "select c.* from Competence c "
                + "where c.ID IN "
                + "(select uc.ID_Competences "
                + "from Users_Competences uc where uc.Username = ?) "
                + "group by c.ID, c.Description";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();
        List<Competence> competences = new ArrayList<Competence>();

        while (rs.next()) {
            competences.add(getCompetence(rs));
        }

        return competences;
    }

    /**
     * Inserts a Competence with the description passed as an input inside the
     * table Competence
     *
     * @param description a string representing the description of the
     * competence
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     * @throws InvalidParameterObjectException if the description passed as an
     * input in null or doesn't respect the constraint defined inside the
     * database
     */
    public int insertCompetence(String description)
            throws SQLException, InvalidParameterObjectException {

        validateCompetence(description);

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "INSERT INTO Competence(Description) VALUES(?)";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, description);

        int result = ps.executeUpdate();

        return result;
    }

    /**
     * Updates a maintainer's competence description inside the table Competence
     *
     * @param id an integer representing the competence id
     * @param description a string representing the competence description
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     * @throws UnsuccessfulUpdateException if no row has been updated
     * @throws InvalidParameterObjectException if the description passed as an
     * input in null or doesn't respect the constraint defined inside the
     * database
     */
    public int updateCompetence(Integer id, String description)
            throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        validateUpdateCompetence(description, id);

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "UPDATE Competence SET Description = ? WHERE ID = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, description);

        ps.setInt(2, id);

        int result = ps.executeUpdate();

        if (result == 0) {
            throw new UnsuccessfulUpdateException("No rows update");
        }

        return result;
    }

    /**
     * Deletes from the table Competence the competence whose id is passed as an
     * input.
     *
     * @param id string representing the competence id
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     */
    public int deleteCompetence(Integer id) throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "DELETE FROM Competence WHERE ID = ?";

        PreparedStatement ps = con.prepareStatement(query);

        // Nel caso in cui l'id è null, per prevenire l'errore di cast in intValue
        // settiamo l'id a -1. Questo valore non esiste nel database perché la sequenza
        // degli id prevede interi positivi
        if (id == null) {

            id = -1;
        }

        ps.setInt(1, id);

        int result = ps.executeUpdate();

        return result;
    }

    /**
     * Finds the competences associated to an activity id.
     *
     * @param activityId an integer representing the activity id
     * @return an ArrayList of competences
     * @throws SQLException if a database access error occurs
     * @throws InvalidParameterObjectException if the description passed as an
     * input in null or doesn't respect the constraint defined inside the
     * database
     */
    public List<Competence> getCompetencesByActivityId(Integer activityId)
            throws SQLException, InvalidParameterObjectException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        validateGetCompetence(activityId);

        String query = "select c.* from Competence c join Activity_Competences ac "
                + "ON (c.id = ac.Competence_ID) "
                + "where ac.Activity_ID = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, activityId);

        ResultSet rs = ps.executeQuery();

        List<Competence> competences = new ArrayList<>();

        while (rs.next()) {

            competences.add(getCompetence(rs));

        }

        return competences;

    }

    /**
     * Creates a Competence from the result of a query
     *
     * @param rs
     * @return is the ResultSet obtained from the execution of a query
     * @throws SQLException if a database access error occurs
     */
    private Competence getCompetence(ResultSet rs) throws SQLException {
        Competence c = new Competence(rs.getInt("ID"), rs.getString("Description"));
        return c;
    }

    /**
     * Checks if the description of the competence taken as an input is
     * null,blank or has a lenght higher than 50 character
     *
     * @param description a string representing the competence description
     * @throws InvalidParameterObjectException if the description is null, blank
     * or has a lenght superior than 50 characters
     */
    private void validateCompetence(String description) throws InvalidParameterObjectException {

        if (description == null || description.isBlank()) {
            throw new InvalidParameterObjectException("Description must be required");
        }

        if (description.length() > 50) {
            throw new InvalidParameterObjectException("Description must be at most 50 characters");
        }

    }

    /**
     * Checks if the description of the competence taken as an input is
     * null,blank or has a lenght higher than 50 character. Moreover, it checks
     * also if the id is null.
     *
     * @param description a string representing the competence description
     * @param id an integer representing the competence id
     * @throws InvalidParameterObjectException if the description is null, blank
     * or has a lenght superior than 50 characters, or if the id is null.
     */
    private void validateUpdateCompetence(String description, Integer id) throws InvalidParameterObjectException {

        if (description == null || description.isBlank()) {
            throw new InvalidParameterObjectException("Description must be required");
        }

        if (description.length() > 50) {
            throw new InvalidParameterObjectException("Description must be at most 50 characters");
        }

        if (id == null) {
            throw new InvalidParameterObjectException("ID must be not null");
        }
    }

    /**
     * Checks if the competence's id is null.
     *
     * @param id an integer representing the competence id
     * @throws InvalidParameterObjectException if the id is null
     */
    private void validateGetCompetence(Integer id) throws InvalidParameterObjectException {

        if (id == null) {

            throw new InvalidParameterObjectException("ID must be not null");
        }
    }

}
