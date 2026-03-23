package sfs.domain.model;

public class Client extends User{

    public Client(){
        super();
    }

    public Client(String login, String firstName, String lastName) {
        super(login, firstName, lastName);
    }
}
