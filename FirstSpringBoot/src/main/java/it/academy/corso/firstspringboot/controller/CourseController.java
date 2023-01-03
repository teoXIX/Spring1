package it.academy.corso.firstspringboot.controller;

import it.academy.corso.firstspringboot.model.Course;
import it.academy.corso.firstspringboot.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    @PostMapping("/course")
    public ResponseEntity<Course> createCourse(@RequestBody Course corso) {
        Course _corso = courseRepository.save(corso);
        return new ResponseEntity<>(_corso, HttpStatus.CREATED);
    }

    @GetMapping("/course")
    public ResponseEntity<List<Course>> getCourses (){
        List<Course> courseArrayList = new ArrayList<Course>();
        courseRepository.findAll().forEach(courseArrayList::add);
        if (courseArrayList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(courseArrayList, HttpStatus.OK);
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") long id) {
        courseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/course/{id}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course corso, @PathVariable("id") long id) {
        Course _corso = courseRepository.getReferenceById(id);
        if(_corso == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            _corso.setNomeCorso(corso.getNomeCorso());
            return new ResponseEntity<>(courseRepository.save(_corso), HttpStatus.OK);
        }
    }
}
