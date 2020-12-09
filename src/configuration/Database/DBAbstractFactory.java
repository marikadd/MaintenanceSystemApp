/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration.Database;

import configuration.Exceptions.DatabaseNotFoundException;
import java.sql.Connection;

/**
 *
 * @author Group9
 */

public abstract class DBAbstractFactory {
    
    public abstract DBProduct getInstance(DBInstanceType instanceType);
    
}
