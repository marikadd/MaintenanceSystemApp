/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.Competence;

import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Services.CompetenceService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */

public class CompetenceServiceTestUpdate {

    private CompetenceService cps;

    @Before
    public void setUp() {
        cps = CompetenceService.getCompetenceService();
    }

    /**
     * Test of updateCompetence method, of class CompetenceService, updating an
     * existing Competence with a valid description.
     */
    @Test
    public void testUpdateCompetence() throws Exception {
        System.out.println("updateCompetence");
        Integer id = 5;
        String newDescription = "Repair Broken Tubes";

        int notExpResult = 0;
        int result = cps.updateCompetence(id, newDescription);
        assertNotEquals(result, notExpResult);
    }

    /**
     * Test of updateCompetence method, of class CompetenceService, updating an
     * invalid Competence (id doesn't exist) with a valid description.
     */
    @Test(expected = UnsuccessfulUpdateException.class)
    public void testUpdateCompetence1() throws Exception {
        System.out.println("updateCompetence");
        Integer id = 150;
        String newDescription = "Repair Computers";

        int expResult = 0;
        int result = cps.updateCompetence(id, newDescription);
        assertEquals(result, expResult);
    }

    /**
     * Test of updateCompetence method, of class CompetenceService, updating a
     * valid Competence with an invalid description.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateCompetence2() throws Exception {
        System.out.println("updateCompetence");
        Integer id = 2;
        String newDescription = "Samaloomadumaloomayoureassumingimahumanwhatigottadotogetitthroughtoyouimasuperhuman";

        int expResult = 0;
        int result = cps.updateCompetence(id, newDescription);
        assertEquals(result, expResult);
    }

    /**
     * Test of updateCompetence method, of class CompetenceService, updating a
     * valid Competence with an invalid description.
     */
    @Test(expected = InvalidParameterObjectException.class)
    public void testUpdateCompetence4() throws Exception {
        System.out.println("updateCompetence");
        Integer id = 2;
        String newDescription = "";

        int expResult = 0;
        int result = cps.updateCompetence(id, newDescription);
        assertEquals(result, expResult);
    }

}
