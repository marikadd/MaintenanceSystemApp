package controller.Services.Competence;

import configuration.Database.ConnectionForTest;
import configuration.Database.DBAbstractFactory;
import configuration.Database.DBFactoryContext;
import configuration.Database.DBManager;
import configuration.Database.DBProduct;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Services.CompetenceService;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */
public class CompetenceServiceTestDelete {

    private CompetenceService cps;
    private ConnectionForTest cft;
    private DBProduct dbProduct;

    public CompetenceServiceTestDelete() {
    }

    @Before
    public void setUp() {
        cps = CompetenceService.getCompetenceService();
        DBAbstractFactory dbFactory = new DBFactoryContext();
        cft = ConnectionForTest.init();
        dbProduct = dbFactory.getInstance(DBManager.instanceType);
        cft.setConn(dbProduct.connectToDB());
        setAfter();
        cft.rollbackConnection();
    }

    @After
    public void setAfter() {
        cft.rollbackConnection();
    }

    /**
     * Test of deleteCompetence method of class CompetenceService, deleting a
     * Competence passing its ID.
     */
    @Test
    public void testDeleteCompetence() throws Exception {
        System.out.println("deleteCompetence");
        Integer id = 2;
        int ExpResult = 1;
        int result = cps.deleteCompetence(id);
        assertEquals(result, ExpResult);
    }

    /**
     * Test of deleteCompetence method of class CompetenceService, deleting
     * Competence with an unexisting ID.
     */
    @Test
    public void testDeleteCompetence1() throws Exception {
        System.out.println("deleteCompetence");
        Integer id = 150;
        Integer expResult = 0;
        Integer result = cps.deleteCompetence(id);
        assertEquals(result, expResult);
    }

    /**
     * Test of deleteCompetence method of class CompetenceService, deleting
     * Competence with an unexisting ID (empty).
     */
    @Test
    public void testDeleteCompetence2() throws Exception {
        System.out.println("deleteCompetence");
        Integer id = null;
        Integer expResult = 0;
        Integer result = cps.deleteCompetence(id);
        assertEquals(result, expResult);
    }

}
