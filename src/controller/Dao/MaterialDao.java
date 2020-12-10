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
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Department.Department;
import model.Material.*;

/**
 *
 * @author Group9
 */
public class MaterialDao {
   
    private static MaterialDao matDao;
    private DBProduct dbProduct;
    private ConnectionForTest cft;
    
    private MaterialDao(){}
    
    //Singleton
    public static MaterialDao init(){
        if(matDao==null) {
            synchronized(MaterialDao.class) {
                if(matDao == null) {
                    matDao=new MaterialDao();
                    matDao.cft = ConnectionForTest.init();
            
                    DBAbstractFactory dbFactory = new DBFactoryContext();
                    matDao.dbProduct = dbFactory.getInstance(DBManager.instanceType);
                }
            }
        }
        return matDao;
    }
    
    public int insertMaterial(String type) throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {
        validateMaterial(type);
        Connection con = dbProduct.connectToDB();
        cft.setConn(con);
        
        String query = "INSERT INTO Material(Type_Material) VALUES(?)";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, type);
         
        int result = ps.executeUpdate();
        
        if(result == 0) {
            throw new UnsuccessfulUpdateException("Cannot insert this material");
        }
        
        return result;
    }
    
    private Material getMaterial(ResultSet rs) throws SQLException {
        
        Material m = new Material(rs.getString("Type_Material"));
        return m;
        
    }
    
    public List<Material> findAllMaterials() throws SQLException {
        
        Connection con = dbProduct.connectToDB();
        cft.setConn(con);
        
        String query = "SELECT * FROM Material";
        
        PreparedStatement ps = con.prepareStatement(query);
        
        ResultSet rs = ps.executeQuery();
        List<Material> matList = new ArrayList<>();
        
        while(rs.next()) {
            matList.add(getMaterial(rs));
        }
        
        return matList;
    }
    
    public int updateMaterial(String oldMaterial, String newMaterial) throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {
        
        validateMaterial(newMaterial);
        
        Connection con = dbProduct.connectToDB();
        cft.setConn(con);
        
        String query = "UPDATE Material SET Type_Material = ? WHERE Type_Material = ?";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, newMaterial);
        ps.setString(2, oldMaterial);
        
        int result = ps.executeUpdate();
        
        if(result == 0) {
            throw new UnsuccessfulUpdateException("Cannot update this material");
        }
        
        return result;
    }
    
    public int deleteMaterial(String type) throws SQLException, UnsuccessfulUpdateException {
        
        Connection con = dbProduct.connectToDB();
        cft.setConn(con);
        
        String query = "DELETE FROM Material WHERE Type_Material = ?";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, type);
        
        int result = ps.executeUpdate();
        
        if(result == 0) {
            throw new UnsuccessfulUpdateException("Cannot delete this material");
        }
        
        return result;
    }
    
    private void validateMaterial(String type) throws InvalidParameterObjectException {
        
        if(type == null || type.isBlank()) {
            throw new InvalidParameterObjectException("The material type must be not null");
        }
        
        if(type.length() > 20) {
            throw new InvalidParameterObjectException("The material type must be at most 30 characters");
        }
    }
    
}
