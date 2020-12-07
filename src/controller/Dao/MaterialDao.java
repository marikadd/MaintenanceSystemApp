/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Dao;

import configuration.Database.DBFactory;
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
    
    private MaterialDao(){}
    
    //Singleton
    public static MaterialDao init(){
        if(matDao==null) 
            matDao=new MaterialDao();
        return matDao;
    }
    
    public int insertMaterial(String type) throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {
        validateMaterial(type);
        Connection con = DBFactory.connectToDB();
        
        String query = "INSERT INTO Material(Type) VALUES(?)";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, type);
        
        int result = ps.executeUpdate();
        
        if(result == 0) {
            throw new UnsuccessfulUpdateException("Cannot insert this material");
        }
        
        return result;
    }
    
    private Material getMaterial(ResultSet rs) throws SQLException {
        Material m = new Material(rs.getString("Type"));
        return m;
    }
    public List<Material> findAllMaterials() throws SQLException {
        
        Connection con = DBFactory.connectToDB();
        
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
        
        Connection con = DBFactory.connectToDB();
        
        String query = "UPDATE Material SET Type = ? WHERE Type = ?";
        
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
        
        Connection con = DBFactory.connectToDB();
        
        String query = "DELETE FROM Material WHERE Type = ?";
        
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
