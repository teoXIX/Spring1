package it.academy.corso.firstspringboot.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "User",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email"), @UniqueConstraint(columnNames = "password")
        }
)
public class User {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @NotBlank
    @Column(name = "email")
    @Size(max = 100)
    private String email;

    @Getter
    @Setter
    @NotBlank
    @Column(name = "password")
    @Size(max = 100)
    private String password;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.REMOVE
            })
    @JoinTable(name = "user_course",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") })
    @JsonIgnore
    private Set<Course> courses = new HashSet<>();


    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.REMOVE
            })
    @JoinTable(name = "user_ruolo",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    public User(String username, String email, String password) {
        this.name = username;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public void addRole(Role r) {
        this.roles.add(r);
    }

    //metodi
    public Set<Role> getRoles(){ return this.roles; }

    public Set<Course> getCourses(){ return this.courses; }

    public void addCourse(Course c) {
        this.courses.add(c);
        //c.addUser(this);
    }

    public void removeCourse(long courseID) {
        Course c = this.courses.stream().filter(t -> t.getId() == courseID).findFirst().orElse(null);
        if(c != null){
            this.courses.remove(c);
            c.getUsers().remove(this);
        }
    }


    public String getUsername() {
        return this.name;
    }


}