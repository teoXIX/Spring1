package it.academy.corso.firstspringboot.service;

import it.academy.corso.firstspringboot.model.Course;
import it.academy.corso.firstspringboot.model.Role;
import it.academy.corso.firstspringboot.model.User;
import it.academy.corso.firstspringboot.payload.request.SignupRequest;

import java.util.ArrayList;
import java.util.Set;

public interface UserService {
    public ArrayList<User> getUsers();
    public User getUserById(Long id);
    public void setUserRoles(User user, Set<String> strRoles) throws Exception;
    public Boolean checkUserName(String nome);
    public Boolean checkUserMail(String mail);
    public void addNewBasicUser(SignupRequest signUpRequest);
    public void deleteUser(Long id);
    public Set<Role> getUserRoles(Long id);
    public Set<Course> getUserCourses (Long id);
    public Boolean addUserCourse(Long courseId, Long userId);
}
