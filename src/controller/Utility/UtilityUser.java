/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Utility;

import model.Users.SystemAdmin;
import model.Users.UserModel;

/**
 *
 * @author Group9
 */
public class UtilityUser<T extends UserModel> {
    
    public void setUserModel(UserModel um, T user) {
        
        user.setEmail(um.getEmail());
        user.setName(um.getName());
        user.setPassword(um.getPassword());
        user.setPhone(um.getPhone());
        user.setSurname(um.getSurname());
        user.setUsername(um.getUsername());
    }
    
}