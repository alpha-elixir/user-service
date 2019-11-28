package com.elixr.user.repository;

import com.elixr.user.model.User;
import com.elixr.user.model.UserNameAndUsernameProjection;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
class UserRepositoryTest {

    @Autowired
    UserRepository subject;

    @Test
    void getByUsername() {
        final User user = addUser();
        Optional<UserNameAndUsernameProjection> actual = this.subject.getByUsername(user.getUsername());
        assertAll(
                () -> assertTrue(actual.isPresent()),
                () -> assertEquals(user.getUsername(), actual.get().getUsername()),
                () -> assertEquals(user.getName(), actual.get().getName())
        );
    }

    User addUser() {
        return subject.save(User.builder().id(2L).name("Anirudh").username("anirudh.aggarwal").password("password").build());
    }

}