package it.academy.corso.firstspringboot.service.imp;

import it.academy.corso.firstspringboot.model.Course;
import it.academy.corso.firstspringboot.model.Esame;
import it.academy.corso.firstspringboot.repository.CourseRepository;
import it.academy.corso.firstspringboot.repository.EsameRepository;
import it.academy.corso.firstspringboot.service.EsameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsameServiceImp implements EsameService {
    @Autowired
    EsameRepository esameRepository;

    @Autowired
    CourseRepository courseRepository;

    public List<Esame> getAll(){
        return esameRepository.findAll();
    }

    public List<Esame> getAllbyValutazione(int val){
        return esameRepository.findByValutazione(val).orElse(null);
    }



    public boolean insertExam(Esame e_, Long courseId){

        Course c = courseRepository.findById(courseId).orElse(null);
        if(c == null){
            return false;
        }

        Esame e = new Esame();
        e.setValutazione(e_.getValutazione());
        e.setGiorno(e_.getGiorno());
        e.setMese(e_.getMese());
        e.setAnno(e_.getAnno());
        e.setCourse(c);

        esameRepository.save(e);
        return true;
    }

    public boolean updateExam(Esame e_){
        Esame e = esameRepository.findById(e_.getId()).orElse(null);
        if(e == null){
            return false;
        }
        return true;
    }


    public void deleteEsame(Long id){
        esameRepository.deleteById(id);
        return;
    }
}
