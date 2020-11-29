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

public class InsertException extends Exception {

    /**
     * Creates a new instance of <code>InsertException</code> without detail
     * message.
     */
    public InsertException() {
    }
    
    /**
     * Constructs an instance of <code>InsertException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public InsertException(String msg) {
        super(msg);
    }

}