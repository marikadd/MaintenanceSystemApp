package model.Users;

/**
 * This abstract class has the purpose of representing the features (methods and
 * attributes) that are in common between the various types of users. Each user
 * has an username and a pssword that have to be initialized.
 *
 * @author Group9
 */
public abstract class User {

    private String username;
    protected String password;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     *
     * @return a string representing the user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets the user's username
     *
     * @param username a string representing the user's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return a string representing the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets the user's password
     *
     * @param password a string representing the user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
