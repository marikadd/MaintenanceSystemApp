/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration.Exceptions;

/**
 *
 * @author Group9
 */

public class RemoveException extends Exception {

    private final String msg = "Value is not present";
    
    public RemoveException() {
    }

    /**
     * Constructs an instance of <code>RemoveException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public RemoveException(String msg) {
        super(msg);
    }

}