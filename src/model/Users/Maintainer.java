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

public class Maintainer extends User {

    private String name;
    private String surname;
    private String email;
    private String phone;
    private Role role;
    private ArrayList<Competence> competencies;

    public Maintainer(String username, String password, String name, String surname, String email, String phone) {
        super(username, password);
        this.name = name;
        this.surname = surname;
        this.role = Role.MAINTAINER;
        this.email = email;
        this.phone = phone;
    }

    public String getInsertQuery() {
        StringBuilder temp = new StringBuilder();
        temp.append("insert into users (nome,surname,username,pw,email,phonenumber,ruolo)");
        temp.append("values(");
        temp.append("'").append(name).append("',");
        temp.append("'").append(surname).append("',");
        temp.append("'").append(this.getUsername()).append("',");
        temp.append("'").append(password).append("',");
        temp.append("'").append(email).append("',");
        temp.append("'").append(phone).append("',");
        temp.append("'").append(role).append("',");

        return temp.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InsertException {
        if (name.isBlank() || name.matches(".*\\d.*")) {
            throw new InsertException("Value name is empty or contains numbers");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws InsertException {
        if (surname.isBlank() || surname.matches(".*\\d.*")) {
            throw new InsertException("Value surname is empty or contains numbers");
        }
        this.surname = surname;
    }

    public void addCompetence(Competence c) throws InsertException {
        if (!competencies.contains(c)) {
            competencies.add(c);
        } else {
            throw new InsertException("Value already present");
        }
    }

    public void removeCompetence(Competence c) throws RemoveException {
        if (competencies.contains(c)) {
            competencies.remove(c);
        } else {
            throw new RemoveException("Value not present");
        }
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

    public ArrayList<Competence> getCompetencies() {
        return competencies;
    }

    @Override
    public String toString() {
        return "Maintainer{" + "name=" + name + ", surname=" + surname + ", email=" + email + ", phone=" + phone + ", role=" + role + '}';
    }

}
