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
public class ActivityNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>ActivityNotFoundException</code> without
     * detail message.
     */
    public ActivityNotFoundException() {
    }

    /**
     * Constructs an instance of <code>ActivityNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ActivityNotFoundException(String msg) {
        super(msg);
    }
}
