package configuration.Database;

import configuration.Exceptions.DatabaseNotFoundException;

/**
 *
 * @author Group9 
 * This class extends the abstract class DBAbstractFactory. It
 * represents the cocrete creator of the factory method pattern. The concrete
 * creator creates an instance of a concrete product, which could be a
 * connection to specific database.
 */
public class DBFactoryContext extends DBAbstractFactory {

    /**
     * Creates an instance of the product class, which is DBProduct
     *
     * @param instanceType is an enum whose value is the name of the currently
     * using database
     * @return an instance of a specific product, that in this case is a
     * connection to the database specified by the input parameter
     */
    @Override
    public DBProduct getInstance(DBInstanceType instanceType) {

        switch (instanceType) {
            case POSTGRESS: {
                return new PostgressProduct();
            }
            default: {
                throw new DatabaseNotFoundException("The selected implementation of "
                        + "database's instance is not found!");
            }
        }

    }

}