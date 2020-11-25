/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.Competences;

/**
 *
 * @author Group9
 */

public class Competence {
    private String description;

    public Competence(String description) {
     
        this.description = description;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
