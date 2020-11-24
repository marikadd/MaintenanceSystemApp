/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.Procedures;
import configuration.Exceptions.*;
import configuration.Exceptions.ValueException;

/**
 *
 * @author Group9
 */

public class Department {
    private String site;
    private String area;

    public Department(String site, String area) throws ValueException{
        if (site.isBlank() || area.isEmpty()){
            throw new ValueException("Site or area is empty");
        }
        this.site = site;
        this.area = area;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) throws ValueException{
        if (site.isBlank())
            throw new ValueException("Site is empty");
        this.site = site;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) throws ValueException{
        if (area.isBlank())
            throw new ValueException("Area is empty");
        this.area = area;
    }
    
}
