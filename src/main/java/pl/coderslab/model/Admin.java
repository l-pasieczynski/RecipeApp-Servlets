package pl.coderslab.model;

import org.mindrot.jbcrypt.BCrypt;

public class Admin {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int superAdmin;
    private int enable;

    public Admin (){
    }

    @Override
    public String toString() {
        return "Admin id=" + id + ", name:" +firstName + ", surname:" + lastName + ", email:" + email;
    }

    public Admin(String firstName, String lastName, String email, String password, int superAdmin, int enable) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        setHashPassword(password);
        this.superAdmin = superAdmin;
        this.enable = enable;
    }


    public Admin(int id, String firstName, String lastName, String email, String password, int superAdmin, int enable) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.superAdmin = superAdmin;
        this.enable = enable;

    }

    public void setHashPassword(String password) {
            this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        }
    public void setPassword(String password){
            this.password = password;
        }


    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSuperAdmin(int superAdmin) {
        this.superAdmin = superAdmin;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getSuperAdmin() {
        return superAdmin;
    }

    public int getEnable() {
        return enable;
    }
}

