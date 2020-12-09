/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    private DepartmentDao() {}
    
    //Singleton
    public static DepartmentDao init() {
        if(depDao == null) {
            synchronized(DepartmentDao.class) {
                if(depDao == null) {
                    depDao = new DepartmentDao();
                    depDao.cft = ConnectionForTest.init();
            
                    DBAbstractFactory dbFactory = new DBFactoryContext();
                    depDao.dbProduct = dbFactory.getInstance(DBManager.instanceType);
                }
            }
        }
        return depDao;
    }
    
    public int insertDepartment(String area) throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {
        
        validateDepartment(area);
        
        Connection con = dbProduct.connectToDB();
        cft.setConn(con);
        
        String query = "INSERT INTO Department(Area) VALUES(?)";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, area);
        
        int result = ps.executeUpdate();
        
        if(result == 0) {
            throw new UnsuccessfulUpdateException("Cannot insert this department");
        }
        
        return result;
    }
    
    public List<Department> findAllDepartments() throws SQLException {
        
        Connection con = dbProduct.connectToDB();
        cft.setConn(con);
        
        String query = "SELECT * FROM Department";
        
        PreparedStatement ps = con.prepareStatement(query);
        
        ResultSet rs = ps.executeQuery();
        List<Department> depList = new ArrayList<>();
        
        while(rs.next()) {
            depList.add(getDepartment(rs));
        }
        
        return depList;
    }
    
    public Department findDepartmentByArea(String area) throws SQLException, DepartmentnotFoundException {
        
        Connection con = dbProduct.connectToDB();
        cft.setConn(con);
        
        String query = "SELECT Area FROM Department WHERE Area = ?";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, area);
        
        ResultSet rs = ps.executeQuery();
        
        Department dep = null;
        
        if(rs.next()) {
            dep = getDepartment(rs);
        }else {
            throw new DepartmentnotFoundException("Department " + area + " not found");
        }
        
        return dep;
    }
    
    public int updateDepartment(String oldArea, String newArea) throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {
        
        validateDepartment(newArea);
        
        Connection con = dbProduct.connectToDB();
        cft.setConn(con);
        
        String query = "UPDATE Department SET Area = ? WHERE Area = ?";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, newArea);
        ps.setString(2, oldArea);
        
        int result = ps.executeUpdate();
        
        if(result == 0) {
            throw new UnsuccessfulUpdateException("Cannot update this department");
        }
        
        return result;
    }
    
    public int deleteDepartment(String area) throws SQLException, UnsuccessfulUpdateException {
        
        Connection con = dbProduct.connectToDB();
        cft.setConn(con);
        
        String query = "DELETE FROM Department WHERE Area = ?";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, area);
        
        int result = ps.executeUpdate();
        
        if(result == 0) {
            throw new UnsuccessfulUpdateException("Cannot delete this department");
        }
        
        return result;
    }
    
    private Department getDepartment(ResultSet rs) throws SQLException {
        Department d = new Department(rs.getString("Area"));
        return d;
    }
    
    private void validateDepartment(String area) throws InvalidParameterObjectException {
        
        if(area == null || area.isBlank()) {
            throw new InvalidParameterObjectException("Area must be not null");
        }
        
        if(area.length() > 30) {
            throw new InvalidParameterObjectException("Area must be at most 30 characters");
        }
    }
}
