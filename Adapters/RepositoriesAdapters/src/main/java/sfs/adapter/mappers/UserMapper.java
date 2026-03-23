package sfs.adapter.mappers;

import sfs.adapter.data.AdminEnt;
import sfs.adapter.data.ClientEnt;
import sfs.adapter.data.FacilityManagerEnt;
import sfs.adapter.data.UserEnt;
import sfs.domain.model.Admin;
import sfs.domain.model.Client;
import sfs.domain.model.FacilityManager;
import sfs.domain.model.User;

public class UserMapper {

    public static UserEnt toEntity(User user) {
        if (user == null) return null;

        UserEnt ent;
        if (user instanceof Client client) {
            ent = new ClientEnt(client.getLogin(), client.getFirstName(), client.getLastName());
        } else if (user instanceof Admin admin) {
            ent = new AdminEnt(admin.getLogin(), admin.getFirstName(), admin.getLastName());
        } else if (user instanceof FacilityManager manager) {
            ent = new FacilityManagerEnt(manager.getLogin(), manager.getFirstName(), manager.getLastName());
        } else {
            throw new IllegalArgumentException("Nieznany typ użytkownika: " + user.getClass());
        }

        ent.setId(user.getId());
        ent.setActive(user.isActive());
        return ent;
    }

    public static User toDomain(UserEnt ent) {
        if (ent == null) return null;

        User user;
        if (ent instanceof ClientEnt clientEnt) {
            user = new Client(clientEnt.getLogin(), clientEnt.getFirstName(), clientEnt.getLastName());
        } else if (ent instanceof AdminEnt adminEnt) {
            user = new Admin(adminEnt.getLogin(), adminEnt.getFirstName(), adminEnt.getLastName());
        } else if (ent instanceof FacilityManagerEnt managerEnt) {
            user = new FacilityManager(managerEnt.getLogin(), managerEnt.getFirstName(), managerEnt.getLastName());
        } else {
            throw new IllegalArgumentException("Nieznany typ encji: " + ent.getClass());
        }

        user.setId(ent.getId());
        user.setActive(ent.isActive());
        return user;
    }
}