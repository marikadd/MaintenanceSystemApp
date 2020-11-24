/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Users;
import configuration.Exceptions.*;
import configuration.Exceptions.InsertException;

/**
 *
 * @author Group9
 */

public class SystemAdmin extends User {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Role role;

    public SystemAdmin(String username, String password, String name, String surname, String email, String phone) {
        super(username, password);
        this.name = name;
        this.surname = surname;
        this.role = Role.SYSTEM_ADMIN;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InsertException{
        if (name.isBlank()|| name.matches(".*\\d.*")){
            throw new InsertException("Value name is empty or contains numbers");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws InsertException{
        if (surname.isBlank() || surname.matches(".*\\d.*")){
            throw new InsertException("Value surname is empty or contains numbers");
        }
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "SystemAdmin{" + "name=" + name + ", surname=" + surname + ", email=" + email + ", phone=" + phone + ", role=" + role + '}';
    }
    
}
