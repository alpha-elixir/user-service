package com.elixr.user.repository;

import com.elixr.user.model.User;
import com.elixr.user.model.UserNameAndUsernameProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<UserNameAndUsernameProjection> getByUsername(String username);
}
