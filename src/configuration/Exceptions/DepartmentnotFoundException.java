package configuration.Exceptions;

/**
 *
 * @author Group9
 */
public class DepartmentnotFoundException extends Exception {

    /**
     * Creates a new instance of <code>DepartmentnotFoundException</code>
     * without detail message.
     */
    public DepartmentnotFoundException() {
    }

    /**
     * Constructs an instance of <code>DepartmentnotFoundException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public DepartmentnotFoundException(String msg) {
        super(msg);
    }

}