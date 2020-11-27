/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Departments;

/**
 *
 * @author Group9
 */

public class Department {
    
    private String area;

    public Department(String site, String area) {
        
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        
        this.area = area;
    }
    
}