package controller.Services;

import java.sql.SQLException;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import configuration.Exceptions.UsernotFoundException;
import controller.Dao.*;
import java.util.LinkedList;
import model.Users.*;

public class UserManagementService {

    private static UserManagementService ums;

    // Singleton
    public static UserManagementService getUserManagementService() {
        if (ums == null) {
            ums = new UserManagementService();
        }
        return ums;
    }

    public static ProdManager findProdManagerByUsername(String username) throws SQLException, UsernotFoundException {

        ProdManager manager = ProdManagerDao.findProdManagerByUsername(username);

        return manager;
    }

    public void insertProdManager(String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        ProdManager prodManager = new ProdManager(username, password, name, surname, email, phone);
        ProdManagerDao.insertProdManager(prodManager);

    }

    public void deleteProdManager(String username) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {
        ProdManagerDao.deleteProdManager(username);
    }

    public void updateProdManager(String oldUsername, String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        ProdManager prodManager = new ProdManager(username, password, name, surname, email, phone);
        ProdManagerDao.updateProdManager(oldUsername, prodManager);

    }

    public static SystemAdmin findSystemAdminByUsername(String username) throws SQLException, UsernotFoundException {

        SystemAdmin sysAdmin = SystemAdminDao.findSystemAdminByUsername(username);

        return sysAdmin;
    }

    public void insertSystemAdmin(String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        SystemAdmin sysAdmin = new SystemAdmin(username, password, name, surname, email, phone);
        SystemAdminDao.insertSystemAdmin(sysAdmin);

    }

    public void deleteSystemAdmin(String username) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {
        SystemAdminDao.deleteSystemAdmin(username);
    }

    public void updateSystemAdmin(String oldUsername, String username, String password, String name, String surname, String email,
            String phone) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        SystemAdmin sysAdmin = new SystemAdmin(username, password, name, surname, email, phone);
        SystemAdminDao.updateSystemAdmin(oldUsername, sysAdmin);

    }
    
    public static LinkedList<Planner> findPlannerByUsername(String username) throws SQLException, UsernotFoundException {

        LinkedList<Planner> listPlanner = new LinkedList<>();
        
        listPlanner = PlannerDao.findPlannerByUsername(username);

        return listPlanner;
    }

    public void insertPlanner(String username, String password, String name, String surname, String email, String phone) 
                              throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        Planner planner = new Planner(username, password, name, surname, email, phone);
        PlannerDao.insertPlanner(planner);
    }

    public void deletePlanner(String username) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {
        PlannerDao.deletePlanner(username);
    }

    public void updatePlanner(String oldUsername, String username, String password, String name, String surname, String email, String phone) 
                              throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        Planner planner = new Planner(username, password, name, surname, email, phone);
        PlannerDao.updatePlanner(oldUsername, planner);

    }
    
    public static LinkedList<Maintainer> findMaintainerByUsername(String username) throws SQLException, UsernotFoundException {

        LinkedList<Maintainer> listMaintainer = new LinkedList<>();
        
        listMaintainer = MaintainerDao.findMaintainerByUsername(username);

        return listMaintainer;
    }

    public void insertMaintainer(String username, String password, String name, String surname, String email, String phone) 
                              throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        Maintainer maintainer = new Maintainer(username, password, name, surname, email, phone);
        MaintainerDao.insertMaintainer(maintainer);
    }

    public void deleteMaintainer(String username) throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {
        MaintainerDao.deleteMaintainer(username);
    }

    public void updateMaintainer(String oldUsername, String username, String password, String name, String surname, String email, String phone) 
                              throws InvalidParameterObjectException, SQLException, UnsuccessfulUpdateException {

        Maintainer maintainer = new Maintainer(username, password, name, surname, email, phone);
        MaintainerDao.updateMaintainer(oldUsername, maintainer);

    }
    
}
