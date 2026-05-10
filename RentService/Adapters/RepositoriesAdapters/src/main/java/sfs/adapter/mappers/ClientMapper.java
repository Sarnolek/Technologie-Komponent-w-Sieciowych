package sfs.adapter.mappers;

import org.springframework.stereotype.Component;
import sfs.adapter.data.ClientEnt;
import sfs.domain.model.Client;

@Component
public class ClientMapper {
    public Client toDomain(ClientEnt ent) {
        if (ent == null) return null;
        return new Client(ent.getId(), ent.getFirstName(), ent.getLastName());
    }

    public ClientEnt toEntity(Client domain) {
        if (domain == null) return null;
        return new ClientEnt(domain.getId(), domain.getFirstName(), domain.getLastName());
    }
}