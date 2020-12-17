package controller.Dao;

import configuration.Database.ConnectionForTest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import configuration.Database.DBAbstractFactory;
import configuration.Database.DBFactoryContext;
import configuration.Database.DBManager;
import configuration.Database.DBProduct;
import configuration.Exceptions.DepartmentnotFoundException;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Department.Department;

/**
 *
 * @author Group9
 */
public class DepartmentDao {

    private static DepartmentDao depDao;
    private DBProduct dbProduct;
    private ConnectionForTest cft;

    /**
     * Pattern Singleton
     */
    private DepartmentDao() {
    }

    /**
     * Creates a singleton for the current class. In order to avoid conflicts
     * between threads, the method uses the synchronized construct.
     *
     * @return an instance of the current class
     */
    public static DepartmentDao init() {
        if (depDao == null) {
            synchronized (DepartmentDao.class) {
                if (depDao == null) {
                    depDao = new DepartmentDao();
                    depDao.cft = ConnectionForTest.init();

                    DBAbstractFactory dbFactory = new DBFactoryContext();
                    depDao.dbProduct = dbFactory.getInstance(DBManager.instanceType);
                }
            }
        }
        return depDao;
    }

    /**
     * Inserts inside the table Department a department whose area is passed as
     * an input
     *
     * @param area a string representing the department's area
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     * @throws UnsuccessfulUpdateException if no row has been updated inside the
     * table Department
     * @throws InvalidParameterObjectException if the string representing the
     * department's area is not valid
     */
    public int insertDepartment(String area) throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        validateDepartment(area);

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "INSERT INTO Department(Area) VALUES(?)";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, area);

        int result = ps.executeUpdate();

        if (result == 0) {
            throw new UnsuccessfulUpdateException("Cannot insert this department");
        }
        //con.close();
        return result;
    }

    /**
     * Creates a list of Department containing all of the departments inside the
     * database table Department.
     *
     * @return an ArrayList of Department
     * @throws SQLException if a database access error occurs
     */
    public List<Department> findAllDepartments() throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "SELECT * FROM Department";

        PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        List<Department> depList = new ArrayList<>();

        while (rs.next()) {
            depList.add(getDepartment(rs));
        }
       // con.close();
        return depList;
    }

    /**
     * Executes a query on the database table Department that, starting from the
     * department's area, and creates an object Department
     *
     * @param area represents the department's area
     * @return a Department
     * @throws SQLException if a database access error occurs
     * @throws DepartmentnotFoundException if the department with the specified
     * area doesn't exist inside the table Department
     */
    public Department findDepartmentByArea(String area) throws SQLException, DepartmentnotFoundException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "SELECT Area FROM Department WHERE Area = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, area);

        ResultSet rs = ps.executeQuery();

        Department dep = null;

        if (rs.next()) {
            dep = getDepartment(rs);
        } else {
            throw new DepartmentnotFoundException("Department " + area + " not found");
        }
        //con.close();
        return dep;
    }

    /**
     * Updates the department's area inside the table Department
     *
     * @param oldArea represents the old department's area
     * @param newArea represents the new department's area
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     * @throws UnsuccessfulUpdateException if no row has been updated
     * @throws InvalidParameterObjectException if the string representing the
     * department's area is not valid
     */
    public int updateDepartment(String oldArea, String newArea)
            throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        validateDepartment(newArea);

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "UPDATE Department SET Area = ? WHERE Area = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, newArea);
        ps.setString(2, oldArea);

        int result = ps.executeUpdate();

        if (result == 0) {
            throw new UnsuccessfulUpdateException("Cannot update this department");
        }
        //con.close();
        return result;
    }

    /**
     * Deletes from the table Department the department whose area has been
     * specified as an input
     *
     * @param area represents the department's area
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     * @throws UnsuccessfulUpdateException if no row has been updated
     */
    public int deleteDepartment(String area) throws SQLException, UnsuccessfulUpdateException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "DELETE FROM Department WHERE Area = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, area);

        int result = ps.executeUpdate();

        if (result == 0) {
            throw new UnsuccessfulUpdateException("Cannot delete this department");
        }
        //con.close();
        return result;
    }

    /**
     * Creates a Department from the result of a query.
     *
     * @param rs is the ResultSet obtained from the execution of a query
     * @return an instance of the class Department
     * @throws SQLException if a database access error occurs
     */
    private Department getDepartment(ResultSet rs) throws SQLException {
        Department d = new Department(rs.getString("Area"));
        return d;
    }

    /**
     * Checks if the department's area taken as an input is not null, blank or
     * has a length higher than 30 characters
     *
     * @param area represents the department's area
     * @throws InvalidParameterObjectException if the area is null, blank or has
     * a length higher than 30 characters
     */
    private void validateDepartment(String area) throws InvalidParameterObjectException {

        if (area == null || area.isBlank()) {
            throw new InvalidParameterObjectException("Area must be required");
        }

        if (area.length() > 30) {
            throw new InvalidParameterObjectException("Area must be at most 30 characters");
        }
    }
}
