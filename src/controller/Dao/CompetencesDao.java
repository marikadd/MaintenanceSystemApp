/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import configuration.Database.DBFactory;
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
 * @author Group 9
 */

public class CompetencesDao {
    
    private static CompetencesDao compDao;
    
    //Singleton
    public static CompetencesDao init() {
        if(compDao == null) compDao = new CompetencesDao();
        return compDao;
    }
    
    public void assignCompetenceToUser(Maintainer maintainer, List<Integer> listId) 
            throws SQLException, UnsuccessfulUpdateException {
        
        Connection con = DBFactory.connectToDB();
        
        String query = "INSERT INTO Users_Compentences VALUES(?,?)";
        
        PreparedStatement ps = con.prepareStatement(query);
        
        for(Integer id: listId) {
            
            ps.setString(1, maintainer.getUsername());
            ps.setInt(2, id);
            boolean result = ps.execute();
            
            if(!result) {
                throw new UnsuccessfulUpdateException("Cannot assign competence #" + id + " to user " + maintainer.getUsername());
            }
        }
    }
    
    public List<Competence> findAllCompetences() throws SQLException {
        
        Connection con = DBFactory.connectToDB();
        
        String query = "select * from Competence";
        
        PreparedStatement ps = con.prepareStatement(query);
        
        ResultSet rs = ps.executeQuery();
        List<Competence> competences = new ArrayList<Competence>();
        
        while(rs.next()) {
            competences.add(getCompetence(rs));
        }
        
        return competences;
    }
    
    public List<Competence> findCompetencesNotInMaintener(String username) throws SQLException {
        
        Connection con = DBFactory.connectToDB();
        
        String query = "select c.* from Competence c join Users_Compentences uc "
                       + "ON (c.ID = uc.ID_Competences) "
                       + "where uc.Username <> ? "
                       + "group by c.ID, c.Description";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);
        
        ResultSet rs = ps.executeQuery();
        List<Competence> competences = new ArrayList<Competence>();
        
        while(rs.next()) {
            competences.add(getCompetence(rs));
        }
        
        return competences;
    }
    
    public List<Competence> findCompetencesInMaintener(String username) throws SQLException {
        
        Connection con = DBFactory.connectToDB();
        
        String query = "select c.* from Competences c join Users_Compentences uc "
                       + "ON (c.ID = uc.ID_Competences) "
                       + "where uc.Username = ? "
                       + "group by c.ID, c.Description";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);
        
        ResultSet rs = ps.executeQuery();
        List<Competence> competences = new ArrayList<Competence>();
        
        while(rs.next()) {
            competences.add(getCompetence(rs));
        }
        
        return competences;
        
    }
    
    
    public void insertCompetence(String description) throws SQLException, UnsuccessfulUpdateException {
        
        Connection con = DBFactory.connectToDB();
        
        String query = "INSERT INTO Competence(Description) VALUES(?)";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, description);
        
        boolean result = ps.execute();
        
        if(!result) {
            throw new UnsuccessfulUpdateException("Cannot insert this competence");
        }
    }
    
    public void updateCompetence(Integer id, String description) throws SQLException, UnsuccessfulUpdateException {
        
        Connection con = DBFactory.connectToDB();
        
        String query = "UPDATE Competence SET Description = ? WHERE ID = ?";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, description);
        ps.setInt(2, id);
        
        boolean result = ps.execute();
        
        if(!result) {
            throw new UnsuccessfulUpdateException("Cannot update this competence");
        }
    }
    
    public void deleteCompetence(Integer id) throws SQLException, UnsuccessfulUpdateException {
        
        Connection con = DBFactory.connectToDB();
        
        String query = "DELETE FROM Competence WHERE ID = ?";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        
        boolean result = ps.execute();
        
        if(!result) {
            throw new UnsuccessfulUpdateException("Cannot delete this competence");
        }
    }
    
    private Competence getCompetence(ResultSet rs) throws SQLException {
        Competence c = new Competence(rs.getInt("ID"), rs.getString("Description"));
        return c;
    }
    
    
    private void validateCompetence(String description) throws InvalidParameterObjectException {
        
        if(description == null) {
            throw new InvalidParameterObjectException("Description must be required");
        }
        
        if(description.length() > 50) {
            throw new InvalidParameterObjectException("Description must be at most 50 characters");
        }
    }
    
}
