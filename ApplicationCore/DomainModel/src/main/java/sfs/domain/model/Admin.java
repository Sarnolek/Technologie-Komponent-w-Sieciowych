package sfs.domain.model;

public class Admin extends User{

    public Admin()
    { super(); }

    public Admin(String login, String firstName, String lastName) {
        super(login, firstName, lastName);
    }
}
