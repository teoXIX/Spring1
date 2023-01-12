package it.academy.corso.firstspringboot.service.imp;

import it.academy.corso.firstspringboot.model.Course;
import it.academy.corso.firstspringboot.repository.CourseRepository;
import it.academy.corso.firstspringboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImp implements CourseService {

    @Autowired
    CourseRepository courseRepository;


    public List<Course> getCourses (){
        List<Course> studentArrayList = new ArrayList<Course>();
        courseRepository.findAll().forEach(studentArrayList::add);
        return studentArrayList;
    }

    public Course getCorseByID(Long id){
        Course curr = courseRepository.findById(id).orElse(null);
        return curr;
    }

    public Course addCourse( Course c ){
        Course curr = courseRepository.save(c);
        return curr;
    }

    public void deleteCourse ( Long id ) throws RuntimeException {
        courseRepository.deleteById(id);
    }

    public Course updateCourse( Long id, Course c ) throws  Exception {
        //Course currCourse = courseRepository.findById(id).orElseThrow(() -> new Exception("TagId " + id + "not found"));
        Course currCourse = courseRepository.findById(id).orElseThrow(() -> new Exception("TagId " + id + "not found"));
        currCourse.setName(c.getName());
        courseRepository.save(currCourse);
        return currCourse;
    }

}
