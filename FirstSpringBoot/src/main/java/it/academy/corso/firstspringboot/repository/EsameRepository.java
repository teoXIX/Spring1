package it.academy.corso.firstspringboot.repository;

import it.academy.corso.firstspringboot.model.Esame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EsameRepository  extends JpaRepository<Esame, Long> {

    Optional<List<Esame>> findByValutazione(int valutazione);

}
