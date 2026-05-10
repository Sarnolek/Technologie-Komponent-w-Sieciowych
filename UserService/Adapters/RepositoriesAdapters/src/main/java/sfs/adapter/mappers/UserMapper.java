package sfs.adapter.mappers;

import sfs.adapter.data.UserEnt;
import sfs.domain.model.User;

public class UserMapper {

    public static UserEnt toEntity(User user) {
        if (user == null) return null;

        UserEnt ent = new UserEnt();
        ent.setId(user.getId());
        ent.setLogin(user.getLogin());
        ent.setPassword(user.getPassword());
        ent.setFirstName(user.getFirstName());
        ent.setLastName(user.getLastName());
        ent.setRole(user.getRole());
        ent.setActive(user.isActive());

        return ent;
    }

    public static User toDomain(UserEnt ent) {
        if (ent == null) return null;

        User user = new User(
                ent.getId(),
                ent.getLogin(),
                ent.getPassword(),
                ent.getFirstName(),
                ent.getLastName(),
                ent.getRole(),
                ent.isActive()
        );

        return user;
    }
}