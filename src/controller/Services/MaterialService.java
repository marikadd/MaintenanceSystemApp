
package controller.Services;

import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.InvalidPermissionException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Dao.MaterialDao;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.Material.Material;

/**
 *
 * @author Group9
 * 
 * The public methods of this class encapsulate the methods of the DAO classes. 
 * For this reason the comments relating to the parameters and exceptions 
 * have not been written because they are already in the DAOs.
 * 
 */
public class MaterialService {

    private static MaterialService matService;
    private MaterialDao matDao;

    /**
     * Pattern Singleton.
     */
    private MaterialService() {
    }

    /**
     * Creates a singleton for the current class. In order to avoid conflicts
     * between threads, the method uses the synchronized construct.
     * @return an instance of the current class.
     */
    public static MaterialService getMaterialService() {
    
        if (matService == null) {
            synchronized (MaterialService.class) {
                if (matService == null) {
                    matService = new MaterialService();
                    matService.matDao = MaterialDao.init();
                }
            }
        }
        return matService;
    }

    /**
     * This method inserts a material.
     * @param type
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing.
     * @throws InvalidPermissionException
     * @throws SQLException
     * @throws UnsuccessfulUpdateException
     * @throws InvalidParameterObjectException
     */
    public int insertMaterial(String type)
            throws InvalidPermissionException, SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        return matDao.insertMaterial(type);
    }

    /**
     * This method updates a type of material.
     * @param oldType
     * @param newType
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing.
     * @throws SQLException
     * @throws UnsuccessfulUpdateException
     * @throws InvalidParameterObjectException
     */
    public int updateMaterial(String oldType, String newType) throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        return matDao.updateMaterial(oldType, newType);
    }

    /**
     * This method deletes a material.
     * @param type
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing.
     * @throws SQLException
     * @throws UnsuccessfulUpdateException
     */
    public int deleteMaterial(String type) throws SQLException, UnsuccessfulUpdateException {

        return matDao.deleteMaterial(type);
    }

    /**
     * This method creates a list of all materials.
     * @return a linkedlist of all materials.  
     * @throws SQLException
     */
    public List<Material> getAllMaterials() throws SQLException {

        List<Material> matList = new LinkedList<>();
        matList = matDao.findAllMaterials();

        return matList;
    }
    
}
