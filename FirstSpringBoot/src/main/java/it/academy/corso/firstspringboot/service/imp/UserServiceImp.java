package it.academy.corso.firstspringboot.service.imp;
import it.academy.corso.firstspringboot.model.Course;
import it.academy.corso.firstspringboot.model.Role;
import it.academy.corso.firstspringboot.model.User;
import it.academy.corso.firstspringboot.model.ERole;
import it.academy.corso.firstspringboot.payload.request.SignupRequest;
import it.academy.corso.firstspringboot.repository.CourseRepository;
import it.academy.corso.firstspringboot.repository.RoleRepository;
import it.academy.corso.firstspringboot.repository.UserRepository;
import it.academy.corso.firstspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    PasswordEncoder encoder;

    public UserServiceImp(){}

    public ArrayList<User> getUsers(){
        ArrayList<User> uArrayList = new ArrayList<User>();
        userRepository.findAll().forEach(uArrayList::add);
        return uArrayList;
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public void setUserRoles(User user, Set<String> strRoles) throws Exception{
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);

                    break;
                case "mod":
                    Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(modRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        userRepository.save(user);
    }


    public Boolean checkUserName(String nome){
        if(userRepository.existsByName(nome)){
            return false;
        }
        return true;
    }

    public Boolean checkUserMail(String mail){
        if(userRepository.existsByEmail(mail)){
            return false;
        }
        return true;
    }

    public void addNewBasicUser(SignupRequest signUpRequest){
        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        return;
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public Set<Role> getUserRoles(Long id){
        Set<Role> roleArrayList;
        User choosenUser = userRepository.findById(id).orElse(null);
        if (choosenUser == null) {
            return null;
        }
        roleArrayList = choosenUser.getRoles();
        return roleArrayList;
    }

    public Set<Course> getUserCourses (Long id){
        User choosenUser = userRepository.findById(id).orElse(null);
        if (choosenUser == null) {
            return null;
        }
        return choosenUser.getCourses();
    }

    public Boolean addUserCourse(Long courseId, Long userId){
        Course courseToAdd = courseRepository.findById(courseId).orElse(null);
        User choosenUser = userRepository.findById(userId).orElse(null);
        if( courseToAdd != null && choosenUser != null){
            choosenUser.addCourse(courseToAdd);
            userRepository.save(choosenUser);
            return true;
        }
        return false;
    }

}