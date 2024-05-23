package lalaina.zoul.ProjetBack.controller;

import lalaina.zoul.ProjetBack.execp.ResourceNotFound;
import lalaina.zoul.ProjetBack.model.Mention;
import lalaina.zoul.ProjetBack.model.Parcours;
import lalaina.zoul.ProjetBack.repository.MentionRepository;
import lalaina.zoul.ProjetBack.repository.ParcoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/parcours")
public class ParcoursController {
    @Autowired
    private ParcoursRepository parcoursRepository;
    @Autowired
    private MentionRepository mentionRepository;
    @GetMapping
    public List<Parcours> getAll(){
        return parcoursRepository.findAll();
    }
    @GetMapping("all")
    public List<Parcours> getAll2(){
        return parcoursRepository.findAll();
    }
    @PostMapping
    public Parcours create(@RequestBody Parcours parcours){
        return parcoursRepository.save(parcours);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Parcours> getById(@PathVariable int id){
        Parcours parcours = parcoursRepository.findById(id).orElseThrow(()-> new ResourceNotFound("not found "+id));
        return ResponseEntity.ok(parcours);
    }
    @PutMapping("{id}")
    public ResponseEntity<Parcours> update(@PathVariable int id, @RequestBody Parcours parcoursDetails){
        Parcours parcoursUpdated = parcoursRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Not found : "+id));
        parcoursUpdated.setNomParcours(parcoursDetails.getNomParcours());
        parcoursUpdated.setNiveau(parcoursDetails.getNiveau());
        parcoursUpdated.setCodition(parcoursDetails.getCodition());
        parcoursUpdated.setCapasite(parcoursDetails.getCapasite());
        parcoursUpdated.setLangue(parcoursDetails.getLangue());
        parcoursUpdated.setCout(parcoursDetails.getCout());
        parcoursUpdated.setBourse(parcoursDetails.getBourse());
        parcoursUpdated.setResponsable(parcoursDetails.getResponsable());
        parcoursUpdated.setMention(parcoursDetails.getMention());

        parcoursRepository.save(parcoursUpdated);
        return ResponseEntity.ok(parcoursUpdated);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Parcours> delete(@PathVariable int id){
        Parcours parcours = parcoursRepository.findById(id).orElseThrow(()->new ResourceNotFound("not found : "+id));
        parcoursRepository.delete(parcours);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //get by Mention
    @GetMapping("{id}")
    public List<Parcours> getByMention(@PathVariable int id){
        Mention mention = mentionRepository.findById(id).orElseThrow(()->new ResourceNotFound("not found"+id));
        List<Parcours> parcours = parcoursRepository.findByMention(mention);
        return parcours;
    }
}
