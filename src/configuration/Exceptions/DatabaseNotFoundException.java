/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration.Exceptions;

/**
 *
 * @author giusi
 */
public class DatabaseNotFoundException extends RuntimeException {

    public DatabaseNotFoundException(String message) {
        super(message);
    }
    
}
