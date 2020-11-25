/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration.Session;

import model.Users.Role;

/**
 *
 * @author Group 9
 */

public class SessionService {
    
    private Session session;
    private static SessionService sessionService;
    
 
    public static SessionService init(String username, Role role) {
        if(sessionService == null) {
            sessionService = new SessionService();
            sessionService.createSession(username, role);
        }
        
        return sessionService;
    }
    
    public static SessionService init() {
        if(sessionService == null) {
            sessionService = new SessionService();
        }
        
        return sessionService;
    }
    
    private void createSession(String username, Role role) {
        session = new Session(username, role);
    }
    
    public Session getSession() {
        return session;
    }
    
}