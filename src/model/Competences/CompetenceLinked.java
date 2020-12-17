package model.Competences;

/**
 *
 * @author Group9
 */

public class CompetenceLinked extends Competence implements CompetenceInterface {
    
    private boolean competenceLinked;

    public CompetenceLinked(boolean competenceLinked, Integer id, String description) {
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