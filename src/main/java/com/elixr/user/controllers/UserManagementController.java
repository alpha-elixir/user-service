package com.elixr.user.controllers;

import com.elixr.user.events.user.RegisterNewUserEvent;
import com.elixr.user.model.User;
import com.elixr.user.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserManagementController {

    private final UserServices userServices;

    UserManagementController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/register")
    ResponseEntity registerUser(@RequestBody RegisterNewUserEvent newUserEvent) {
        User projection = this.userServices.addNewUser(newUserEvent);
        return ResponseEntity.ok(projection);
    }

    ResponseEntity getSubscribers() {
        return null;
    }

}
