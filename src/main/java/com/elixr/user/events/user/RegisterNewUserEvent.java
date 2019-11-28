package com.elixr.user.events.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterNewUserEvent {
    private String name;
    private String username;
    private String password;
}
