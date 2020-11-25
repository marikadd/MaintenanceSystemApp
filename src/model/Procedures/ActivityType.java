/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.Procedures;

/**
 *
 * @author Group9
 */

public class ActivityType {
    private final int id;
    private String typology;

    public ActivityType(int id, String typology) {
        this.id = id;
        this.typology = typology;
    }

    public int getId() {
        return id;
    }

    public String getTypology() {
        return typology;
    }

    public void setTypology(String typology) {
        this.typology = typology;
    }

}
