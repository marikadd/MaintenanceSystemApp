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

public interface CompetenceInterface {
    
    public Integer getId();

    public void setId(Integer id);
    
    public String getDescription();

    public void setDescription(String description);
    
    public boolean isCompetenceLinked();
    
    public void setCompetenceLinked(boolean competenceLinked);
    
    public String toString();
    
}