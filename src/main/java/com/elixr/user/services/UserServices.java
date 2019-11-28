package com.elixr.user.services;

import com.elixr.user.events.user.RegisterNewUserEvent;
import com.elixr.user.model.User;
import com.elixr.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    private final UserRepository userRepository;

    UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addNewUser(RegisterNewUserEvent newUserEvent) {
        User user = this.userRepository.save(User.builder()
                .username(newUserEvent.getUsername()).password(newUserEvent.getPassword())
                .name(newUserEvent.getName()).build());
        return user;
    }
}
