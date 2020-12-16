package model.Competences;

/**
 * This interface has the purpose of representing the common methods that each
 * competence must implement. Each competence has an ID that has to be
 * initialized, a description ecc.
 *
 * @author Group9
 */
public interface CompetenceInterface {

    /**
     *
     * @return an integer representing the competence's ID
     */
    public Integer getId();

    /**
     * sets the competence's ID
     *
     * @param id an integer representing the competence's ID
     */
    public void setId(Integer id);

    /**
     *
     * @return a string representing the competence's description
     */
    public String getDescription();

    /**
     * sets the competence's description
     *
     * @param description a string representing the competence's description
     */
    public void setDescription(String description);

    /**
     * Checks is the competence is linked to a maintainer
     *
     * @return true if the competence is linked to a maintainer, otherwise false
     */
    public boolean isCompetenceLinked();

    /**
     * Links a competence to a maintainer
     *
     * @param competenceLinked a boolean that indicates if the competence is
     * associated to a maintainer or not.
     */
    public void setCompetenceLinked(boolean competenceLinked);

    public String toString();

}
