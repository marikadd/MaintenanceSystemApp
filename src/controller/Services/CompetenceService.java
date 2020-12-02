/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;

import configuration.Exceptions.InvalidParameterObjectException;
import controller.Utility.UtilityUser;
import configuration.Exceptions.InvalidPermissionException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import configuration.Exceptions.UsernotFoundException;
import controller.Dao.CompetencesDao;
import controller.Dao.UsersDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.Competences.Competence;
import model.Competences.CompetenceAdapter;
import model.Competences.CompetenceTarget;
import model.Users.Maintainer;
import model.Users.Role;
import model.Users.UserModel;

/**
 *
 * @author Group9
 */

public class CompetenceService {
    
    private static CompetenceService compService;
    private UsersDao usersDao;
    private CompetencesDao compDao;
    
    //Singleton
    private CompetenceService() {
    }
    
    public static CompetenceService getCompetenceService() {
        if(compService == null) {
            compService = new CompetenceService();
            compService.usersDao = UsersDao.init();
            compService.compDao = CompetencesDao.init();
        }
        return compService;
    }
    
    public int assignCompetence(String usernameMain, List<Integer> listId) 
            throws InvalidPermissionException, SQLException, UsernotFoundException, UnsuccessfulUpdateException {
        
        UtilityUser<Maintainer> utilityUser = new UtilityUser<Maintainer>();
        Maintainer maintainer = new Maintainer(); 
        UserModel um = usersDao.findUserByUsername(usernameMain, Role.MAINTAINER);
        utilityUser.setUserModel(um, maintainer);
        
        return compDao.assignCompetenceToUser(maintainer, listId);
    }
    
    public int insertCompetence(String description) 
            throws InvalidPermissionException, SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {
        
        return compDao.insertCompetence(description);
    }
   
    public int updateCompetence(Integer id, String description) 
            throws InvalidPermissionException, SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {
        
        return compDao.updateCompetence(id, description);
    }
    
        
    public int deleteCompetence(Integer id) throws InvalidPermissionException, SQLException, UnsuccessfulUpdateException {
        
        return compDao.deleteCompetence(id);
    }
    
    public List<Competence> getAllCompetences() throws SQLException{
        
        List<Competence> compList = new LinkedList<>();
        compList = compDao.findAllCompetences();
        
        return compList;
    }
    
    public List<CompetenceTarget> getAllCompetenceTarget(String username) throws SQLException, UsernotFoundException {
        
        validateMaintainer(username);
        List<Competence> competencesInMaintener = compDao.findCompetencesInMaintener(username);
        List<Competence> competencesNotInMaintener = compDao.findCompetencesNotInMaintener(username);
        
        List<CompetenceTarget> targets = new ArrayList<CompetenceTarget>();
        targets.addAll(getListTargetMaintainer(competencesInMaintener, true));
        targets.addAll(getListTargetMaintainer(competencesNotInMaintener, false));
        
        return targets;
    }
     
    private List<CompetenceTarget> getListTargetMaintainer(List<Competence> competences, boolean linked) {
        
        List<CompetenceTarget> targets = new ArrayList<CompetenceTarget>();
        
        for(Competence c: competences) {
            CompetenceTarget ct = new CompetenceAdapter(linked, c.getId(), c.getDescription());
            targets.add(ct);
        }
        
        return targets;
    }
    
    private void validateMaintainer(String username) throws SQLException, UsernotFoundException {
        
        UserModel um = usersDao.findUserByUsername(username, Role.MAINTAINER);
    }
    
}