package model.Users;

/**
 * This interface has the purpose of representing the common methods that each
 * kind of user must implement. Each user has an username that has to be
 * initialized, an email ecc.
 *
 * @author Group9
 */
public interface UserModel {

    /**
     *
     * @return a string representing the user's username
     */
    public String getUsername();

    /**
     * sets the user's username
     *
     * @param username a string representing the user's username
     */
    public void setUsername(String username);

    /**
     *
     * @return a string representing the user's password
     */
    public String getPassword();

    /**
     * sets the user's password
     *
     * @param password a string representing the user's password
     */
    public void setPassword(String password);

    /**
     *
     * @return a string representing the user's email
     */
    public String getEmail();

    /**
     * sets the user's email
     *
     * @param email a string representing the user's email
     */
    public void setEmail(String email);

    /**
     *
     * @return a string representing the user's phone number
     */
    public String getPhone();

    /**
     * sets the user's phone number
     *
     * @param phone a string representing the user's phone number
     */
    public void setPhone(String phone);

    /**
     *
     * @return a string representing the user's name
     */
    public String getName();

    /**
     * sets the user's name
     *
     * @param name a string representing the user's name
     */
    public void setName(String name);

    /**
     *
     * @return a string representing the user's surname
     */
    public String getSurname();

    /**
     * sets the user's surname
     *
     * @param surname a string representing the user's surname
     */

    public void setSurname(String surname);

    /**
     *
     * @return a string representing the user's role
     */
    public String getRole();

}
