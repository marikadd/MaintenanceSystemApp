
package controller.Services;

import configuration.Exceptions.DepartmentnotFoundException;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.InvalidPermissionException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Dao.DepartmentDao;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.Department.Department;

/**
 *
 * @author Group 9 
 * 
 * The public methods of this class encapsulate the methods of the DAO classes. 
 * For this reason the comments relating to the parameters and exceptions 
 * have not been written because they are already in the DAOs.
 * 
 */
public class DepartmentService {

    private static DepartmentService depService;
    private DepartmentDao depDao;
 
    /**
     * Pattern Singleton.
     */
    private DepartmentService() {
    }

    /**
     * Creates a singleton for the current class. In order to avoid conflicts
     * between threads, the method uses the synchronized construct.
     * @return an instance of the current class.
     */
    public static DepartmentService getDepartmentService() {
        
        if (depService == null) {
            synchronized (DepartmentService.class) {
                if (depService == null) {
                    depService = new DepartmentService();
                    depService.depDao = DepartmentDao.init();
                }
            }
        }
        return depService;
    }
    
    /**
     * This method inserts a department.
     * @param area
     * @return either the row count for SQL Data Manipulation Language (DML) statements or
     * 0 for SQL statements that return nothing.
     * @throws InvalidPermissionException
     * @throws SQLException
     * @throws UnsuccessfulUpdateException
     * @throws InvalidParameterObjectException 
     */
    public int insertDepartment(String area)
            throws InvalidPermissionException, SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        return depDao.insertDepartment(area);
    }
    
    /**
     * This method updates a department's area.
     * @param oldArea
     * @param newArea
     * @return either the row count for SQL Data Manipulation Language (DML) statements or
     * 0 for SQL statements that return nothing.
     * @throws SQLException
     * @throws UnsuccessfulUpdateException
     * @throws InvalidParameterObjectException 
     */
    public int updateDepartment(String oldArea, String newArea) throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        return depDao.updateDepartment(oldArea, newArea);
    }
    
    /**
     * This method deletes a department.
     * @param area
     * @return either the row count for SQL Data Manipulation Language (DML) statements or
     * 0 for SQL statements that return nothing.
     * @throws SQLException
     * @throws UnsuccessfulUpdateException 
     */
    public int deleteDepartment(String area) throws SQLException, UnsuccessfulUpdateException {

        return depDao.deleteDepartment(area);
    }
    
    /**
     * This method creates a list of all departments.
     * @return a linkedlist of all departments.
     * @throws SQLException 
     */
    public List<Department> getAllDepartments() throws SQLException {

        List<Department> depList = new LinkedList<>();
        depList = depDao.findAllDepartments();

        return depList;
    }
   
    /**
     * This method gets the department that corresponds to the area you are looking for.
     * @param area
     * @return the department you are looking for.
     * @throws SQLException
     * @throws DepartmentnotFoundException 
     */
    public Department getDepartment(String area) throws SQLException, DepartmentnotFoundException {
         
        return depDao.findDepartmentByArea(area);
    }
}
