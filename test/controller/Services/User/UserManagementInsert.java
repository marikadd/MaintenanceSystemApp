/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services.User;

import controller.Services.UserManagementService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Group9
 */
public class UserManagementInsert {
    
    private UserManagementService ums;
    
    
    public UserManagementInsert() {
   
    }
    
    
    @Before
    public void setUp() {
        
        ums = UserManagementService.getUserManagementService();
        
    }
    
  
     /**
     * Test of insertProdManager method, of class UserManagementService.
     * Insert of a ProdManager with correct parameters
     */
    @Test
    public void testInsertProdManager() throws Exception {
        
        System.out.println("insertProdManager");
        String username = "tcaio";
        String password = "Tiziotto@9";
        String name = "Tizio";
        String surname = "Caio";
        String email = "tcaio@gmail.com";
        String phone = "1234567890";
        int result = ums.insertProdManager(username, password, name, surname, email, phone);
        
        int notExpectedResult = 0;
        assertNotEquals(result, notExpectedResult);
        
    }
 
     /**
     * Test of insertSystemAdmin method, of class UserManagementService.
     * Insert of a SystemAdmin with correct parameters
     */
    @Test
    public void testInsertSystemAdmin() throws Exception {
        System.out.println("insertSystemAdmin");
        String username = "gdipaolo";
        String password = "Giuseppina98@";
        String name = "Giuseppina";
        String surname = "Di Paolo";
        String email = "gdipaolo@gmail.com";
        String phone = "3334567224";
        int result = ums.insertSystemAdmin(username, password, name, surname, email, phone);
        
        int notExpectedResult = 0;
        assertNotEquals(result, notExpectedResult);
   
    }
    
     /**
     * Test of insertPlanner method, of class UserManagementService.
     * Insert of a Planner with correct parameters
     */
    @Test
    public void testInsertPlanner() throws Exception {
        System.out.println("insertPlanner");
        String username = "icantalupo";
        String password = "Irmacanta98@";
        String name = "Irma";
        String surname = "Cantalupo";
        String email = "irma98@gmail.com";
        String phone = "3334565336";
        int result=ums.insertPlanner(username, password, name, surname, email, phone);
        
        int notExpectedResult = 0;
        assertNotEquals(result, notExpectedResult);
    }
 
     /**
     * Test of insertMaintainer method, of class UserManagementService.
     * Insert of a Maitainer with correct parameters
     */
    @Test
    public void testInsertMaintainer() throws Exception {
        System.out.println("insertMaintainer");
        String username = "mdelledonne";
        String password = "Marika98@";
        String name = "Marika";
        String surname = "Delle Donne";
        String email = "mdelledonne@gmail.com";;
        String phone = "3345645778";
        int result=ums.insertMaintainer(username, password, name, surname, email, phone);
       
        int notExpectedResult = 0;
        assertNotEquals(result, notExpectedResult);
    }
    
}
