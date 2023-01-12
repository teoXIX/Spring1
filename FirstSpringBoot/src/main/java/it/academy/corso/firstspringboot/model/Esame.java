package it.academy.corso.firstspringboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class Esame {
    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @NotBlank
    private int valutazione;
    @Setter
    @Getter
    @NotBlank
    @Size(max = 2, min = 2)
    private String giorno;
    @Setter
    @Getter
    @NotBlank
    @Size(max = 2, min = 2)
    private String mese;
    @Setter
    @Getter
    @NotBlank
    @Size(max = 4, min = 4)
    private String anno;

}
