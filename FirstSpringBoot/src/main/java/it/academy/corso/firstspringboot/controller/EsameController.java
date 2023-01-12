package it.academy.corso.firstspringboot.controller;

import it.academy.corso.firstspringboot.model.User;
import it.academy.corso.firstspringboot.repository.EsameRepository;
import it.academy.corso.firstspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/controllerUser")
public class EsameController {

    @Autowired
    EsameRepository esameRepository;
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getEsami (){
        List<User> uArrayList = new ArrayList<User>();
        esameRepository.findAll().forEach(uArrayList::add);
        if (uArrayList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(uArrayList, HttpStatus.OK);
    }


    @PostMapping("/insert")
    public ResponseEntity<User> createEsame(@RequestBody User u) {
        User insertedU = esameRepository.save(u);
        return new ResponseEntity<>(insertedU, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEsame(@PathVariable("id") long id) {
        esameRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
