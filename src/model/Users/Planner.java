package model.Users;

/**
 *
 * @author Group9
 */

public class Planner extends User implements UserModel {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private final Role role;

    public Planner() {
        this.role = Role.PLANNER;
    }
    
    public Planner(String username, String password, String name, String surname, String email, String phone) {
        super(username, password);
        this.name = name;
        this.surname = surname;
        this.role = Role.PLANNER;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        
        this.surname = surname;
    }   

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getRole() {   
        return role.toString();
    }
    
    @Override
    public String toString() {
        return "Planner{" + "name=" + name + ", surname=" + surname + ", email=" + email + ", phone=" + phone + ", role=" + role + '}';
    }
    
}