package lalaina.zoul.ProjetBack.controller;

import lalaina.zoul.ProjetBack.execp.ResourceNotFound;
import lalaina.zoul.ProjetBack.model.Institus;
import lalaina.zoul.ProjetBack.repository.InstitusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/v1/institus")
public class InstitusController {
    @Autowired
    private InstitusRepository institusRepository;

    @GetMapping
    public List<Institus> getAll(){
        return institusRepository.findAll();
    }
    @PostMapping
    public Institus create(@RequestBody Institus institus){
        return institusRepository.save(institus);
    }
    @GetMapping("{id}")
    public ResponseEntity<Institus> getByid(@PathVariable int id){
        Institus institus = institusRepository.findById(id).orElseThrow(()->new ResourceNotFound("institus not found with id : "+id));
        return ResponseEntity.ok(institus);
    }
    @PutMapping("{id}")
    public ResponseEntity<Institus> update(@PathVariable int id, @RequestBody Institus institusDetails){
        Institus institusUpdated = institusRepository.findById(id).orElseThrow(()->new ResourceNotFound("institus not found with id : "+id));
        institusUpdated.setNom(institusDetails.getNom());
        institusUpdated.setUniversite(institusDetails.getUniversite());

        institusRepository.save(institusUpdated);
        return ResponseEntity.ok(institusUpdated);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Institus> delete(@PathVariable int id) {
        Institus institus = institusRepository.findById(id).orElseThrow(()->new ResourceNotFound("Institus not found with id : "+id));
        institusRepository.delete(institus);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
