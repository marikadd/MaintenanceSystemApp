/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;

import configuration.Exceptions.InvalidPermissionException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import configuration.Exceptions.UsernotFoundException;
import configuration.Session.Session;
import configuration.Session.SessionService;
import controller.Dao.CompetencesDao;
import controller.Dao.UsersDao;
import java.sql.SQLException;
import java.util.List;
import model.Users.Maintainer;
import model.Users.Role;

/**
 *
 * @author Group 9
 */

public class CompetenceService {
    
    private static CompetenceService compService;
    
    private SessionService sessionService;
    private UsersDao usersDao;
    private CompetencesDao compDao;
    
    public static CompetenceService getCompetenceService() {
        if(compService == null) {
            compService = new CompetenceService();
            compService.sessionService = SessionService.init();
            compService.usersDao = UsersDao.init();
            compService.compDao = CompetencesDao.init();
        }
        return compService;
    }
    
    public void assignCompetence(String usernameMain, List<Integer> listId) 
            throws InvalidPermissionException, SQLException, UsernotFoundException, UnsuccessfulUpdateException {
        
        validateSysAdmin();
        
        Maintainer maintainer = (Maintainer) usersDao.findUserByUsername(usernameMain, Role.MAINTAINER);
        
        compDao.assignCompetenceToUser(maintainer, listId);
    }
    
    public void insertCompetence(String description) 
            throws InvalidPermissionException, SQLException, UnsuccessfulUpdateException {
        
        validateSysAdmin();
        
        compDao.insertCompetence(description);
    }
    
    
    public void updateCompetence(Integer id, String description) 
            throws InvalidPermissionException, SQLException, UnsuccessfulUpdateException {
        
        validateSysAdmin();
        
        compDao.updateCompetence(id, description);
    }
    
        
    public void deleteCompetence(Integer id) throws InvalidPermissionException, SQLException, UnsuccessfulUpdateException {
        
        validateSysAdmin();
        
        compDao.deleteCompetence(id);
    }
    
    
    private void validateSysAdmin() throws InvalidPermissionException {
        Session session = sessionService.getSession();
        
        if(!Role.SYSTEM_ADMIN.equals(session.getRole())) {
            throw new InvalidPermissionException("Operation denied");
        }
    }
    
}