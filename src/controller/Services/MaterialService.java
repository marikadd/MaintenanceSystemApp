/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;

import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.InvalidPermissionException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Dao.MaterialDao;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.Department.Department;
import model.Material.Material;

/**
 *
 * @author Group9
 */
public class MaterialService {
        private static MaterialService matService;
        private MaterialDao matDao;
        
        //Singleton
         public static MaterialService getMaterialService() {
        if (matService == null) {
            matService = new MaterialService();
            matService.matDao = MaterialDao.init();
        }
        return matService;
    }
         
        public int insertMaterial(String type)
            throws InvalidPermissionException, SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

            return matDao.insertMaterial(type);
        }
        
        public int updateMaterial(String oldType, String newType) throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

            return matDao.updateMaterial(oldType, newType);
        }

        public int deleteMaterial(String type) throws SQLException, UnsuccessfulUpdateException {

            return matDao.deleteMaterial(type);
        }
        
        public List<Material> getAllMaterials() throws SQLException {

            List<Material> matList = new LinkedList<>();
            matList = matDao.findAllMaterials();

            return matList;
        }       
}
