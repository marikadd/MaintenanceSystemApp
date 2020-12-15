package configuration.Exceptions;

/**
 *
 * @author Group9
 */
public class DatabaseNotFoundException extends RuntimeException {

    public DatabaseNotFoundException(String message) {
        super(message);
    }
    
}