/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Users;

/**
 *
 * @author Group9
 */
public interface UserModel {
    
    public String getUsername();

    public void setUsername(String username);

    public String getPassword();
    
    public void setPassword(String password);
    
    public String getEmail();

    public void setEmail(String email);

    public String getPhone();

    public void setPhone(String phone);
    
    public String getName();

    public void setName(String name);

    public String getSurname();

    public void setSurname(String surname);
    
    public String getRole();
    
}