package it.academy.corso.firstspringboot.business;

import it.academy.corso.firstspringboot.model.Course;
import it.academy.corso.firstspringboot.model.User;
import it.academy.corso.firstspringboot.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.Set;

@Service
public class Register {
    CourseRepository courseRepository;
    UserRepository userRepository;


}
