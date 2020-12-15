package configuration.Database;

import java.sql.Connection;

/**
 *
 * @author Group9 
 * The purpose of this interface is to represent the "product" of
 * the commonly known pattern factory method. In this case, the product is
 * represented by a connection to a database.
 */
public interface DBProduct {

    /**
     * Its purpose is to create a connection to a specific database.
     *
     * @return Connection object
     */
    public Connection connectToDB();

}
