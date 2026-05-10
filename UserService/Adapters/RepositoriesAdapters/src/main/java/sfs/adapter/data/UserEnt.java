package sfs.adapter.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserEnt {

    @Id
    private String id;

    private String login;
    private String password; // Tego brakowało!
    private String firstName;
    private String lastName;
    private String role;     // I tego do obsługi nowej struktury
    private boolean active;

    // Pusty konstruktor dla Spring Data
    public UserEnt() {
    }

    public UserEnt(String login, String password, String firstName, String lastName, String role, boolean active) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.active = active;
    }

    // --- GETTERY I SETTERY ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}