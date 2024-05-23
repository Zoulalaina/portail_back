package lalaina.zoul.ProjetBack.controller;

import lalaina.zoul.ProjetBack.execp.ResourceNotFound;
import lalaina.zoul.ProjetBack.model.Filiere;
import lalaina.zoul.ProjetBack.repository.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/filiere")
public class FiliereCOntroller {
    @Autowired
    private FiliereRepository filiereRepository;

    @GetMapping
    public List<Filiere> getAll(){
        return filiereRepository.findAll();
    }

    @PostMapping
    public Filiere createFiliare(@RequestBody Filiere filiere){
        return filiereRepository.save(filiere);
    }
    public ResponseEntity<Filiere> findById(@PathVariable int id){
        Filiere filiere = filiereRepository.findById(id).orElseThrow(()->new ResourceNotFound("Filiere not exist with id : "+id));
        return ResponseEntity.ok(filiere);
    }
}
