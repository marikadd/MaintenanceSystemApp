/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration.Database;

import configuration.Exceptions.DatabaseNotFoundException;

/**
 *
 * @author Group9
 */
public class DBFactoryContext extends DBAbstractFactory {

    @Override
    public DBProduct getInstance(DBInstanceType instanceType)  {
        
        switch(instanceType) {
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
