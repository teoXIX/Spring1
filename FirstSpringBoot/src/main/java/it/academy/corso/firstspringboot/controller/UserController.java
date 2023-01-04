package it.academy.corso.firstspringboot.controller;

import it.academy.corso.firstspringboot.model.Course;
import it.academy.corso.firstspringboot.repository.CourseRepository;
import it.academy.corso.firstspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import it.academy.corso.firstspringboot.model.User;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User utente = userRepository.save(user);
        return new ResponseEntity<>(utente, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUsers (){
        List<User> userArrayList = new ArrayList<User>();
        userRepository.findAll().forEach(userArrayList::add);
        if (userArrayList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(userArrayList, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
    @PutMapping(value = "/user/{id}")
    public ResponseEntity<Course> updateUser(@RequestBody User utente, @PathVariable("id") long id) {
        User user = userRepository.getReferenceById(id);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            user.setEmail(utente.getEmail());
            user.setUsername(utente.getUsername());
            user.setPassword(utente.getPassword());
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        }
    }
     */
}
