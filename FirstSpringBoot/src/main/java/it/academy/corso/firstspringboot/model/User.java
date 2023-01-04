package it.academy.corso.firstspringboot.model;

import jakarta.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username")
    @Getter
    @Setter
    @NotBlank
    @Size(max = 20)
    private String username;

    @Column(name = "email")
    @Getter
    @Setter
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Column(name = "password")
    @Getter
    @Setter
    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "course_user",
            joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id"))

    private Set<Course> course = new LinkedHashSet<>();

    public Set<Course> getCourse() {
        return course;
    }

    public void setCourse(Set<Course> course) {
        this.course = course;
    }
}
