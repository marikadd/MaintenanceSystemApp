/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import configuration.Database.DBFactory;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import configuration.Exceptions.CompetencenotFoundException;
import java.util.LinkedList;
import model.Competences.Competence;

/**
 *
 * @author raiza
 */
public class CompetenceDao {

    public static void insertCompetence(Competence competence)
            throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        validateCompetence(competence);

        Connection con = DBFactory.connectToDB();

        String query = "INSERT INTO Competence VALUES(?)";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, competence.getDescription());

        boolean result = ps.execute();

        if (!result) {
            throw new UnsuccessfulUpdateException("Cannot create this competence");
        }
    }

    private static void validateCompetence(Competence competence) throws InvalidParameterObjectException {

        if (competence.getDescription().length() > 50) {
            throw new InvalidParameterObjectException("Competence description must not contain more than 50 characters");
        }
    }

}
