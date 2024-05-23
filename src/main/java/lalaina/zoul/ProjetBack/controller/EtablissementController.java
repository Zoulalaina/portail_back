package lalaina.zoul.ProjetBack.controller;

import lalaina.zoul.ProjetBack.execp.ResourceNotFound;
import lalaina.zoul.ProjetBack.model.Etablissement;
import lalaina.zoul.ProjetBack.model.Universite;
import lalaina.zoul.ProjetBack.repository.EtablissementRepository;
import lalaina.zoul.ProjetBack.repository.UnivRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/etablissement")
public class EtablissementController {

    @Autowired
    private EtablissementRepository etablissementRepository;
    @Autowired
    private UnivRepository univRepository;

    @GetMapping
    public List<Etablissement> getAll(){
        return etablissementRepository.findAll();
    }

    @GetMapping("{id}")
    public List<Etablissement> getByidUniv(@PathVariable int id){

        Universite universite = univRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Universite not found with id : "+id));
        List <Etablissement> etablissement = etablissementRepository.findByUniversite(universite);
        return etablissement;
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Etablissement> getById(@PathVariable int id){
        Etablissement etablissement = etablissementRepository.findById(id).orElseThrow(()-> new ResourceNotFound("not found : "+id));
        return ResponseEntity.ok(etablissement);
    }
}
