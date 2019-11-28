package com.elixr.user.services;

import com.elixr.user.events.user.RegisterNewUserEvent;
import com.elixr.user.model.User;
import com.elixr.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServicesTest {

    @InjectMocks
    UserServices subject;

    @Mock
    UserRepository userRepository;

    @Test
    void addNewUser() {
        final RegisterNewUserEvent registerNewUserEvent = RegisterNewUserEvent.builder()
                .name("Elmo")
                .username("elmo")
                .password("password").build();

        when(userRepository.save(any())).thenReturn(User.builder()
                .name(registerNewUserEvent.getName())
                .username(registerNewUserEvent.getUsername())
                .password(registerNewUserEvent.getPassword()).id(1L).build());

        User user = this.subject.addNewUser(registerNewUserEvent);
        assertAll(
                () -> assertNotNull(user),
                () -> assertEquals(registerNewUserEvent.getUsername(), user.getUsername()),
                () -> assertEquals(registerNewUserEvent.getName(), user.getName()),
                () -> assertNotNull(user.getId())
        );
    }
}