package controller.Utility;

import model.Users.UserModel;

/**
 * This class is an utility class parameterized on the parameter type T that
 * extends UserModel. Its purpose is to set the attributes of a specific
 * UserModel (like a Maintainer ecc) starting from an instance of the interface
 * UserModel.
 *
 * @author Group9
 * @param <T> represents a specific UserModel
 */
public class UtilityUser<T extends UserModel> {

    /**
     * Initializes the attributes surname, name, username, email, password,
     * phone, of a specific user, given in input the generic UserModel.
     *
     * @param um represents the generic UserModel
     * @param user represents a specific user (e.g. Maintainer ecc)
     */
    public void setUserModel(UserModel um, T user) {

        user.setEmail(um.getEmail());
        user.setName(um.getName());
        user.setPassword(um.getPassword());
        user.setPhone(um.getPhone());
        user.setSurname(um.getSurname());
        user.setUsername(um.getUsername());
    }

}
