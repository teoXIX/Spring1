package it.academy.corso.firstspringboot.repository;

import it.academy.corso.firstspringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String username);

    Boolean existsByName(String username);

    Boolean existsByEmail(String email);
}
