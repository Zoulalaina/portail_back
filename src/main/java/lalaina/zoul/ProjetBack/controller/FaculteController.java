package lalaina.zoul.ProjetBack.controller;

import lalaina.zoul.ProjetBack.execp.ResourceNotFound;
import lalaina.zoul.ProjetBack.model.Faculte;
import lalaina.zoul.ProjetBack.repository.FacutleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/faculte")
public class FaculteController {
    @Autowired
    private FacutleRepository facutleRepository;
    @GetMapping
    public List<Faculte> getAll(){
        return facutleRepository.findAll();
    }
    @PostMapping
    public Faculte CreateFaculte(@RequestBody Faculte faculte){
        return facutleRepository.save(faculte);

    }
    @GetMapping("{id}")
    public ResponseEntity<Faculte> getById(@PathVariable int id){
        Faculte faculte = facutleRepository.findById(id).orElseThrow(()->new ResourceNotFound("Faculte not found with id"+id));
        return ResponseEntity.ok(faculte);

    }
    @PutMapping("{id}")
    public  ResponseEntity<Faculte> updateFac(@PathVariable int id, @RequestBody Faculte faculteDetails){
        Faculte faculteupdated = facutleRepository.findById(id).orElseThrow(()->new ResourceNotFound("Faculte not found with id : "+id));
        faculteupdated.setNom(faculteDetails.getNom());
        faculteupdated.setUniversite(faculteDetails.getUniversite());

        facutleRepository.save(faculteupdated);
        return ResponseEntity.ok(faculteupdated);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Faculte> delete(@PathVariable int id){
        Faculte faculte = facutleRepository.findById(id).orElseThrow(()->new ResourceNotFound("Fac not found with id :: "+id));
        facutleRepository.delete(faculte);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
