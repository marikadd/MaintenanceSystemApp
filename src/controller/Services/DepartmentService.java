/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;

import configuration.Exceptions.DepartmentnotFoundException;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.InvalidPermissionException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Dao.DepartmentDao;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.Department.Department;

/**
 *
 * @author Group9
 */
public class DepartmentService {

    private static DepartmentService depService;

    private DepartmentDao depDao;

    public static DepartmentService getDepartmentService() {
        if (depService == null) {
            depService = new DepartmentService();
            depService.depDao = DepartmentDao.init();
        }
        return depService;
    }

    public int insertDepartment(String area)
            throws InvalidPermissionException, SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        return depDao.insertDepartment(area);
    }

    public int updateDepartment(String oldArea, String newArea) throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        return depDao.updateDepartment(oldArea, newArea);
    }

    public int deleteDepartment(String area) throws SQLException, UnsuccessfulUpdateException {

        return depDao.deleteDepartment(area);
    }

    public List<Department> getAllDepartments() throws SQLException {

        List<Department> depList = new LinkedList<>();
        depList = depDao.findAllDepartments();

        return depList;
    }

    public Department getDepartment(String area) throws SQLException, DepartmentnotFoundException {
        return depDao.findDepartment(area);
    }
}
