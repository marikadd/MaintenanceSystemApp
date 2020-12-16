package controller.Services.Competence;

import configuration.Database.ConnectionForTest;
import configuration.Database.DBAbstractFactory;
import configuration.Database.DBFactoryContext;
import configuration.Database.DBManager;
import configuration.Database.DBProduct;
import configuration.Exceptions.InvalidParameterObjectException;
import controller.Services.CompetenceService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.After;

/**
 *
 * @author Group9
 */
public class CompetenceServiceTestInsert {

    private CompetenceService cps;
    private ConnectionForTest cft;
    private DBProduct dbProduct;

    public CompetenceServiceTestInsert() {
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
     * Test of insertCompetence method, inserting a new Competence with its
     * description.
     */
    @Test
    public void testInsertCompetence() throws Exception {
        System.out.println("insertCompetence");
        String description = "Repair Tubes";
        int result = cps.insertCompetence(description);
        int ExpectedResult = 1;
        assertEquals(result, ExpectedResult);
    }

    /**
     * Test of insertCompetence method, inserting a Competence with a
     * description that already exists.
     */
    @Test(expected = SQLException.class)
    public void testInsertCompetence1() throws Exception {
        System.out.println("insertCompetence");
        String description = "PAV Certification";
        int result = cps.insertCompetence(description);
        int expectedResult = 0;
        assertEquals(result, expectedResult);
    }

    /**
     * Test of insertCompetence method, inserting a Competence with an invalid
     * description (empty).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertCompetence2() throws Exception {
        System.out.println("insertCompetence");
        String description = null;
        int result = cps.insertCompetence(description);
        int expectedResult = 0;
        assertEquals(result, expectedResult);
    }

    /**
     * Test of insertCompetence method, inserting a Competence with an invalid
     * description (lenght > 50).
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testInsertCompetence3() throws Exception {
        System.out.println("insertCompetence");
        String description = "Samalamadumaloomayoureassumingimahumanwhatigottadotogetitthroughtoyouimasuperhuman";
        int result = cps.insertCompetence(description);
        int expectedResult = 0;
        assertEquals(result, expectedResult);
    }

}
