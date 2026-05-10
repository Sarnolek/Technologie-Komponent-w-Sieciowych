//package sfs.adapter.aggregates;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import sfs.adapter.repository.MongoUserRepository;
//import sfs.domain.model.Client;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class UserRepositoryAdapterTest {
//
//    @Mock
//    private MongoUserRepository mongoUserRepository;
//
//    @InjectMocks
//    private UserRepositoryAdapter userRepositoryAdapter;
//
//    @Test
//    void shouldSaveUserAndReturnDomainObject() {
//        Client domainUser = new Client("login1", "Mariusz", "Bączek");
//        ClientEnt savedEnt = new ClientEnt("login1", "Mariusz", "Bączek");
//        savedEnt.setId("123");
//
//        when(mongoUserRepository.save(any())).thenReturn(savedEnt);
//
//        User result = userRepositoryAdapter.save(domainUser);
//
//        assertNotNull(result.getId());
//        assertEquals("login1", result.getLogin());
//        verify(mongoUserRepository, times(1)).save(any());
//    }
//
//    @Test
//    void shouldFindUserById() {
//        ClientEnt ent = new ClientEnt("dupa", "dupa1", "dupa2");
//        ent.setId("321");
//        when(mongoUserRepository.findById("321")).thenReturn(Optional.of(ent));
//        Optional<User> result = userRepositoryAdapter.findById("321");
//        assertTrue(result.isPresent());
//        assertEquals("dupa1", result.get().getFirstName());
//    }
//
//
//}
