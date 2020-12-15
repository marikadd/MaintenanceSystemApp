
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
import model.Competences.CompetenceLinked;
import model.Users.Maintainer;
import model.Users.Role;
import model.Users.UserModel;
import model.Competences.CompetenceInterface;

/**
 *
 * @author Group9
 * 
 * The public methods of this class encapsulate the methods of the DAO classes. 
 * For this reason the comments relating to the parameters and exceptions 
 * have not been written because they are already in the DAOs.
 * 
 */
public class CompetenceService {

    private static CompetenceService compService;
    private UsersDao usersDao;
    private CompetencesDao compDao;

    /**
     * Pattern Singleton.
     */
    private CompetenceService() {
    }

    /**
     * Creates a singleton for the current class. In order to avoid conflicts
     * between threads, the method uses the synchronized construct.
     * @return an instance of the current class.
     */
    public static CompetenceService getCompetenceService() {
        
        if (compService == null) {
            synchronized (CompetenceService.class) {
                if (compService == null) {
                    compService = new CompetenceService();
                    compService.usersDao = UsersDao.init();
                    compService.compDao = CompetencesDao.init();
                }
            }
        }
        return compService;
    }

    /**
     * This method assigns a competence to a maintainer.
     * @param usernameMain
     * @param listId
     * @return either the row count for SQL Data Manipulation Language (DML) statements or
     * 0 for SQL statements that return nothing.
     * @throws InvalidPermissionException
     * @throws SQLException
     * @throws UsernotFoundException
     * @throws UnsuccessfulUpdateException
     */
    public int assignCompetence(String usernameMain, List<Integer> listId)
            throws InvalidPermissionException, SQLException, UsernotFoundException, UnsuccessfulUpdateException {

        UtilityUser<Maintainer> utilityUser = new UtilityUser<>();
        Maintainer maintainer = new Maintainer();
        UserModel um = usersDao.findUserByUsername(usernameMain, Role.MAINTAINER);
        utilityUser.setUserModel(um, maintainer);

        return compDao.assignCompetenceToUser(maintainer, listId);
    }
    
    /**
     * This method deassings a competence to a maintainer.
     * @param usernameMain
     * @param listId
     * @return either the row count for SQL Data Manipulation Language (DML) statements or
     * 0 for SQL statements that return nothing.
     * @throws InvalidPermissionException
     * @throws SQLException
     * @throws UsernotFoundException
     * @throws UnsuccessfulUpdateException 
     */
    public int deassignCompetence(String usernameMain, List<Integer> listId)
            throws InvalidPermissionException, SQLException, UsernotFoundException, UnsuccessfulUpdateException {

        UtilityUser<Maintainer> utilityUser = new UtilityUser<>();
        Maintainer maintainer = new Maintainer();
        UserModel um = usersDao.findUserByUsername(usernameMain, Role.MAINTAINER);
        utilityUser.setUserModel(um, maintainer);

        return compDao.deassignCompetenceToUser(maintainer, listId);
    }
    
    /**
     * This method inserts a competence.
     * @param description
     * @return either the row count for SQL Data Manipulation Language (DML) statements or
     * 0 for SQL statements that return nothing.
     * @throws InvalidPermissionException
     * @throws SQLException
     * @throws UnsuccessfulUpdateException
     * @throws InvalidParameterObjectException 
     */
    public int insertCompetence(String description)
            throws InvalidPermissionException, SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        return compDao.insertCompetence(description);
    }
   
    /**
     * This method updates a description of a competence.
     * @param id
     * @param description
     * @return either the row count for SQL Data Manipulation Language (DML) statements or
     * 0 for SQL statements that return nothing.
     * @throws InvalidPermissionException
     * @throws SQLException
     * @throws UnsuccessfulUpdateException
     * @throws InvalidParameterObjectException 
     */
    public int updateCompetence(Integer id, String description)
            throws InvalidPermissionException, SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        return compDao.updateCompetence(id, description);
    }
    
    /**
     * This method deletes a competence.
     * @param id
     * @return either the row count for SQL Data Manipulation Language (DML) statements or
     * 0 for SQL statements that return nothing.
     * @throws InvalidPermissionException
     * @throws SQLException
     * @throws UnsuccessfulUpdateException 
     */
    public int deleteCompetence(Integer id) throws InvalidPermissionException, SQLException, UnsuccessfulUpdateException {

        return compDao.deleteCompetence(id);
    }
    
    /**
     * This method creates a list of all the skills associated with a specific activity.
     * @param activityID
     * @return a linkedlist of skills related to a specific activity.
     * @throws SQLException
     * @throws InvalidParameterObjectException 
     */
    public List<Competence> getAllSkills(Integer activityID) throws SQLException, InvalidParameterObjectException {

        List<Competence> compList = new LinkedList<>();
        compList = compDao.getCompetencesByActivityId(activityID);

        return compList;
    }
   
    /**
     * This method verifies how many Maintainer's skills are included in Activity skills. 
     * @param activityComp
     * @param username
     * @return a string which expresses the number of Maintainer's competences (included in Activity competences)  
     * compared to the total number of competences that the activity needs.
     * @throws SQLException
     * @throws UsernotFoundException 
     */
    public String getCommonSkills(List<Competence> activityComp, String username) throws SQLException, UsernotFoundException {

        int totalSkills = activityComp.size();
        int commonSkills = 0;
       
        usersDao.checkuserMaintainer(username);
        List<Competence> maintainerComp = compDao.findCompetencesInMaintener(username);

        for (Competence c : activityComp) {
            if (maintainerComp.contains(c)) {
                commonSkills++;
            }
        }

        String result = String.valueOf(commonSkills) + "/" + String.valueOf(totalSkills);

        return result;
    }
   
    /**
     * This method creates a list of all competences.
     * @return a linkedlist of competences.
     * @throws SQLException 
     */
    public List<Competence> getAllCompetences() throws SQLException {

        List<Competence> compList = new LinkedList<>();
        compList = compDao.findAllCompetences();

        return compList;
    }
    
    /**
     * This method creates a list of all competences.
     * @param username
     * @return an arraylist that contains both competences that have been assigned and those not assigned.
     * @throws SQLException
     * @throws UsernotFoundException 
     */
    public List<CompetenceInterface> getAllCompetenceTarget(String username) throws SQLException, UsernotFoundException {

        validateMaintainer(username);
        List<Competence> competencesInMaintener = compDao.findCompetencesInMaintener(username);
        List<Competence> competencesNotInMaintener = compDao.findCompetencesNotInMaintener(username);

        List<CompetenceInterface> targets = new ArrayList<>();
        targets.addAll(getListTargetMaintainer(competencesInMaintener, true));
        targets.addAll(getListTargetMaintainer(competencesNotInMaintener, false));

        return targets;
    }
    
    /**
     * This method creates a list of CompetenceInterface in which every element  
     * states whether a particular activity has been associated with a maintainer or not.
     * @param competences is a list of competences.
     * @param linked is a boolean which is used to indicate whether an activity has been assigned or not.
     * @return an arraylist of CompetenceInterface.
     */
    private List<CompetenceInterface> getListTargetMaintainer(List<Competence> competences, boolean linked) {

        List<CompetenceInterface> targets = new ArrayList<>();

        for (Competence c : competences) {
            CompetenceInterface ct = new CompetenceLinked(linked, c.getId(), c.getDescription());
            targets.add(ct);
        }

        return targets;
    }

    /**
     * This method makes sure that the user you are looking for is actually a maintainer.
     * @param username represents the username of a maintainer.
     * @throws SQLException if the columnLabel is not valid, or if a database access error occurs,
     * or if this method is called on a closed result set.
     * @throws UsernotFoundException if the user with the specified role doesn't exist.
     */
    private void validateMaintainer(String username) throws SQLException, UsernotFoundException {

        UserModel um = usersDao.findUserByUsername(username, Role.MAINTAINER);
    }

}
 