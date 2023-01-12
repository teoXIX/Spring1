package it.academy.corso.firstspringboot.service;

import it.academy.corso.firstspringboot.model.Course;

import java.util.List;

public interface CourseService {
    public List<Course> getCourses ();
    public Course getCorseByID(Long id);
    public Course addCourse( Course c );
    public void deleteCourse ( Long id );
    public Course updateCourse( Long id, Course c ) throws  Exception;
}