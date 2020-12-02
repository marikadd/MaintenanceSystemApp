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
 * @author Group9
 */

public class CompetencesDao {
    
    private static CompetencesDao compDao;
    
    //Singleton
    private CompetencesDao() {
    }
   
    public static CompetencesDao init() {
        if(compDao == null)
            compDao = new CompetencesDao();
        return compDao;
    }
    
    public int assignCompetenceToUser(Maintainer maintainer, List<Integer> listId) 
            throws SQLException, UnsuccessfulUpdateException {
        
        Connection con = DBFactory.connectToDB();
        
        String query = "INSERT INTO Users_Competences VALUES(?,?)";
        
        PreparedStatement ps = con.prepareStatement(query);
        int result = 0;
        for(Integer id: listId) {
            
            ps.setString(1, maintainer.getUsername());
            ps.setInt(2, id);
            result += ps.executeUpdate();
 
        }
        return result;
    }
    
    public List<Competence> findAllCompetences() throws SQLException {
        
        Connection con = DBFactory.connectToDB();
        
        String query = "select * from Competence";
        
        PreparedStatement ps = con.prepareStatement(query);
        
        ResultSet rs = ps.executeQuery();
        List<Competence> competences = new ArrayList<>();
        
        while(rs.next()) {
            competences.add(getCompetence(rs));
        }
        
        return competences;
    }
    
    public List<Competence> findCompetencesNotInMaintener(String username) throws SQLException {
        
        Connection con = DBFactory.connectToDB();
        
        String query = "select c.* from Competence c "
                       + "where c.ID NOT IN "
                       + "(select uc.ID_Competences "
                       + "from Users_Competences uc where uc.Username = ?) "
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
        
        String query = "select c.* from Competence c "
                       + "where c.ID IN "
                       + "(select uc.ID_Competences "
                       + "from Users_Competences uc where uc.Username = ?) "
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
    
    public int insertCompetence(String description) throws  SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException  {

        validateCompetence(description);
        
        Connection con = DBFactory.connectToDB();
        
        String query = "INSERT INTO Competence(Description) VALUES(?)";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, description);
        
        int result = ps.executeUpdate();
        
        return result;
    }
    
    public int updateCompetence(Integer id, String description) throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {
        
        validateCompetence(description);
        
        Connection con = DBFactory.connectToDB();
        
        String query = "UPDATE Competence SET Description = ? WHERE ID = ?";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, description);
        ps.setInt(2, id);
        
        int result = ps.executeUpdate();
        
        if(result == 0) {
            throw new UnsuccessfulUpdateException("No rows update");
        }
        
        return result;
    }
    
    public int deleteCompetence(Integer id) throws SQLException, UnsuccessfulUpdateException {
        
        Connection con = DBFactory.connectToDB();
        
        String query = "DELETE FROM Competence WHERE ID = ?";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        
        int result = ps.executeUpdate();
        
        return result;
    }
    
    
    
    public List<Competence> getCompetencesByActivityId(Integer activityId) throws SQLException {
        
         Connection con = DBFactory.connectToDB();
        
        String query = "select c.* from Competence c join Activity_Competences ac "
                + "ON (c.id = ac.Competence_ID) "
                + "where ac.Activity_ID = ?";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, activityId);
        
        ResultSet rs = ps.executeQuery();
        
        List<Competence> competences = new ArrayList<>();
        
        while(rs.next()) {
            
            competences.add(getCompetence(rs));
            
        }
        
        return competences;
        
    }
    
    private Competence getCompetence(ResultSet rs) throws SQLException {
        Competence c = new Competence(rs.getInt("ID"), rs.getString("Description"));
        return c;
    }
    
    
    private void validateCompetence(String description) throws InvalidParameterObjectException {
        
        if(description == null) {
            throw new InvalidParameterObjectException("Description must be required");
        }
        
        if("".equals(description)) {
            throw new InvalidParameterObjectException("Description must be required");
        }
        
        if(description.length() > 50) {
            throw new InvalidParameterObjectException("Description must be at most 50 characters");
        }
    }
    
}