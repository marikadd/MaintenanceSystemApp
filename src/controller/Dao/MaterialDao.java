package controller.Dao;

import configuration.Database.ConnectionForTest;
import configuration.Database.DBAbstractFactory;
import configuration.Database.DBFactoryContext;
import configuration.Database.DBManager;
import configuration.Database.DBProduct;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Material.*;

/**
 *
 * @author Group9
 */
public class MaterialDao {

    private static MaterialDao matDao;
    private DBProduct dbProduct;
    private ConnectionForTest cft;

    /**
     * Pattern Singleton
     */
    private MaterialDao() {
    }

    /**
     * Creates a singleton for the current class. In order to avoid conflicts
     * between threads, the method uses the synchronized construct.
     *
     * @return an instance of the current class
     */
    public static MaterialDao init() {
        if (matDao == null) {
            synchronized (MaterialDao.class) {
                if (matDao == null) {
                    matDao = new MaterialDao();
                    matDao.cft = ConnectionForTest.init();

                    DBAbstractFactory dbFactory = new DBFactoryContext();
                    matDao.dbProduct = dbFactory.getInstance(DBManager.instanceType);
                }
            }
        }
        return matDao;
    }

    /**
     * Inserts a material whose type is passed as an input inside the table
     * Material
     *
     * @param type a string representing the type of a material
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     * @throws UnsuccessfulUpdateException if no row has been updated inside the
     * table Material
     * @throws InvalidParameterObjectException if the string representing the
     * material's type is not valid
     */
    public int insertMaterial(String type) throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {
        validateMaterial(type);
        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "INSERT INTO Material(Type_Material) VALUES(?)";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, type);

        int result = ps.executeUpdate();

        if (result == 0) {
            throw new UnsuccessfulUpdateException("Cannot insert this material");
        }
        con.close();
        return result;
    }

    /**
     * Creates a Material from the result of a query.
     *
     * @param rs is the ResultSet obtained from the execution of a query
     * @return an instance of the class Material
     * @throws SQLException if a database access error occurs
     */
    private Material getMaterial(ResultSet rs) throws SQLException {

        Material m = new Material(rs.getString("Type_Material"));
        return m;

    }

    /**
     * Creates a list of Material containing all of the materials inside the
     * database table Material.
     *
     * @return an ArrayList of Material
     * @throws SQLException if a database access error occurs
     */
    public List<Material> findAllMaterials() throws SQLException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "SELECT * FROM Material";

        PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        List<Material> matList = new ArrayList<>();

        while (rs.next()) {
            matList.add(getMaterial(rs));
        }
        con.close();
        return matList;
    }

    /**
     * Updates the material type inside the table Material
     *
     * @param oldMaterial represents the old material's type
     * @param newMaterial represents the new material's type
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     * @throws UnsuccessfulUpdateException if no row has been updated
     * @throws InvalidParameterObjectException if the string representing the
     * material's type is not valid
     */
    public int updateMaterial(String oldMaterial, String newMaterial)
            throws SQLException, UnsuccessfulUpdateException, InvalidParameterObjectException {

        validateMaterial(newMaterial);

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "UPDATE Material SET Type_Material = ? WHERE Type_Material = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, newMaterial);
        ps.setString(2, oldMaterial);

        int result = ps.executeUpdate();

        if (result == 0) {
            throw new UnsuccessfulUpdateException("Cannot update this material");
        }
        con.close();
        return result;
    }

    /**
     * Deletes from the table Material the material whose type has been
     * specified as an input
     *
     * @param type represents the type of the material
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     * @throws UnsuccessfulUpdateException if no row has been updated
     */
    public int deleteMaterial(String type) throws SQLException, UnsuccessfulUpdateException {

        Connection con = dbProduct.connectToDB();
        cft.setConn(con);

        String query = "DELETE FROM Material WHERE Type_Material = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, type);

        int result = ps.executeUpdate();

        if (result == 0) {
            throw new UnsuccessfulUpdateException("Cannot delete this material");
        }
        con.close();
        return result;
    }

    /**
     * Checks if the material's type taken as an input is not null, blank or has
     * a length superior than 20 characters
     *
     * @param type represents the material's type
     * @throws InvalidParameterObjectException if the material's type is null,
     * blank or has a length superior than 20 characters
     */
    private void validateMaterial(String type) throws InvalidParameterObjectException {

        if (type == null || type.isBlank()) {
            throw new InvalidParameterObjectException("Material type must be required");
        }

        if (type.length() > 20) {
            throw new InvalidParameterObjectException("Material type must be at most 30 characters");
        }
    }

}
