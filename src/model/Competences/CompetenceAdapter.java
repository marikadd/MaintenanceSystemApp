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

public class CompetenceAdapter extends Competence implements CompetenceTarget {
    
    private boolean competenceLinked;

    public CompetenceAdapter(boolean competenceLinked, Integer id, String description) {
        super(id, description);
        this.competenceLinked = competenceLinked;
    }

    public boolean isCompetenceLinked() {
        return competenceLinked;
    }

    public void setCompetenceLinked(boolean competenceLinked) {
        this.competenceLinked = competenceLinked;
    }

    @Override
    public String toString() {
        return "CompetenceAdapter{" + "competenceLinked=" + competenceLinked + '}';
    }

}