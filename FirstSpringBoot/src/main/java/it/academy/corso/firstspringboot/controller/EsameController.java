package it.academy.corso.firstspringboot.controller;

import it.academy.corso.firstspringboot.model.Esame;
import it.academy.corso.firstspringboot.service.EsameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controllerEsame")
public class EsameController {
    @Autowired
    EsameService esameService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Esame>> getEsame(){
        List<Esame> esami = esameService.getAll();
        return new ResponseEntity<>(esami, HttpStatus.OK);
    }
    @GetMapping("/getAllbyValutazione/{val}")
    public ResponseEntity<List<Esame>> getEsameByVal (@PathVariable("val") int val){
        List<Esame> esami = esameService.getAllbyValutazione(val);
        if(esami.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(esami, HttpStatus.OK);
    }

    @PostMapping("/insertEsame/{corsoId}")
    public ResponseEntity<?> createEsame(@PathVariable("corsoId") long corsoId, @RequestBody Esame e) {
        esameService.insertExam(e,corsoId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEsame(@PathVariable("id") long id) {
        esameService.deleteEsame(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}