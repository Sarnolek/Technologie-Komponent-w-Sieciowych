package sfs.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sfs.domain.model.User;
import sfs.ports.infrastructure.UserRepositoryPort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldActivateInactiveUser() throws Exception {
        User inactiveUser = new User("1", "login", "pass", "A", "B", "CLIENT", false);
        when(userRepositoryPort.findById("1")).thenReturn(Optional.of(inactiveUser));
        when(userRepositoryPort.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        User result = userService.activateUser("1");

        assertTrue(result.isActive());
        verify(userRepositoryPort, times(1)).save(inactiveUser);
    }

    @Test
    void shouldThrowExceptionWhenActivatingAlreadyActiveUser() {
        User activeUser = new User("1", "login", "pass", "A", "B", "CLIENT", true);
        when(userRepositoryPort.findById("1")).thenReturn(Optional.of(activeUser));

        Exception exception = assertThrows(Exception.class, () -> {
            userService.activateUser("1");
        });

        assertTrue(exception.getMessage().contains("już został aktywowany"));
        verify(userRepositoryPort, never()).save(any());
    }
}