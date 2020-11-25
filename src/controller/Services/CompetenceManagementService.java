/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;
import java.sql.SQLException;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import configuration.Exceptions.UsernotFoundException;
import controller.Dao.*;
import java.util.LinkedList;
import model.Competences.Competence;

/**
 *
 * @author raiza
 */
public class CompetenceManagementService {
    
    private static CompetenceManagementService cms;
    
    // Singleton
    public static CompetenceManagementService getCompetenceManagementService() {
        if (cms == null) {
            cms = new CompetenceManagementService();
        }
        return cms;
    }
    
    public void insertCompetence(String description) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        Competence competence = new Competence(description);
        CompetenceDao.insertCompetence(competence);

    }
}
