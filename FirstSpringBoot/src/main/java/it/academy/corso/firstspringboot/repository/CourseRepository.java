package it.academy.corso.firstspringboot.repository;

import it.academy.corso.firstspringboot.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course getReferenceById(long id);
}
