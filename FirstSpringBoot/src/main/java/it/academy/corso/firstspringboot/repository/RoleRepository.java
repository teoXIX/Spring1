package it.academy.corso.firstspringboot.repository;

import it.academy.corso.firstspringboot.model.ERole;
import it.academy.corso.firstspringboot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}