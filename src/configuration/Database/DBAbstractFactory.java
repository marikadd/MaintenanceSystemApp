package configuration.Database;

import configuration.Exceptions.DatabaseNotFoundException;
import java.sql.Connection;

/**
 *
 * @author Group9 
 * The purpose of this abstract class is to represent the abstract creator of 
 * the commonly known pattern factory method.
 */
public abstract class DBAbstractFactory {

    /**
     * Creates an instance of the product class, which is DBProduct
     *
     * @param instanceType is an enum whose value is the name of the currently
     * using database
     * @return an instance of a specific product, that in this case is a
     * connection to the database specified by the input parameter
     */
    public abstract DBProduct getInstance(DBInstanceType instanceType);

}
