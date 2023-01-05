package it.academy.corso.firstspringboot.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "nomeCorso")
    private String nomeCorso;

    public Course(){

    }

    public long getId(){
        return id;
    }

    public String getNomeCorso() {
        return nomeCorso;
    }

    public void setNomeCorso(String nomeCorso) {
        this.nomeCorso = nomeCorso;
    }

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "course_user",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> user = new LinkedHashSet<>();

    public Set<User> getUser(){
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

}
