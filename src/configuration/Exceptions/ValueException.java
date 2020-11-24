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

public class ValueException extends Exception {

    /**
     * Creates a new instance of <code>ValueException</code> without detail
     * message.
     */
    public ValueException() {
    }

    /**
     * Constructs an instance of <code>ValueException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ValueException(String msg) {
        super(msg);
    }

}