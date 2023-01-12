package it.academy.corso.firstspringboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="exams")
public class Esame {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    private int valutazione;
    @Getter
    @Setter
    private int giorno;
    @Getter
    @Setter
    private int mese;
    @Getter
    @Setter
    private int anno;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="course_id", nullable=false)
    private Course course;


}
