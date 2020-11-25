/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Users;

import java.util.*;
import configuration.Exceptions.*;
import configuration.Exceptions.InsertException;
import configuration.Exceptions.RemoveException;
import model.Competences.Competence;

/**
 *
 * @author Group9
 */

public class Maintainer extends User implements UserModel {

    private String name;
    private String surname;
    private String email;
    private String phone;
    private Role role;
    private ArrayList<Competence> competences;

    public Maintainer(String username, String password, String name, String surname, String email, String phone) {
        super(username, password);
        this.name = name;
        this.surname = surname;
        this.role = Role.MAINTAINER;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        
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

    public ArrayList<Competence> getCompetences() {
        return competences;
    }

    @Override
    public String toString() {
        return "Maintainer{" + "name=" + name + ", surname=" + surname + ", email=" + email + ", phone=" + phone + ", role=" + role + '}';
    }

}
