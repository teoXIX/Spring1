package it.academy.corso.firstspringboot.repository;

import it.academy.corso.firstspringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EsameRepository extends JpaRepository<User, Long> {
}
