package lalaina.zoul.ProjetBack.controller;

import lalaina.zoul.ProjetBack.execp.ResourceNotFound;
import lalaina.zoul.ProjetBack.model.Etablissement;
import lalaina.zoul.ProjetBack.model.Mention;
import lalaina.zoul.ProjetBack.repository.EtablissementRepository;
import lalaina.zoul.ProjetBack.repository.MentionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/mention")
public class MentionController {
    @Autowired
    private MentionRepository mentionRepository;
    @Autowired
    private EtablissementRepository etablissementRepository;
    @GetMapping
    public List<Mention> getAll(){
        return mentionRepository.findAll();
    }
    @GetMapping("all")
    public List<Mention> getAll2(){
        return mentionRepository.findAll();
    }
    @PostMapping
    public Mention create(@RequestBody Mention mention){
        return mentionRepository.save(mention);
    }

    @PutMapping("{id}")
    public ResponseEntity<Mention> update(@PathVariable int id, @RequestBody Mention mentionDetails ){
        Mention mentionUpdated = mentionRepository.findById(id).orElseThrow(()-> new ResourceNotFound("not found : "+id));
        mentionUpdated.setNomMention(mentionDetails.getNomMention());
        mentionUpdated.setResponsable(mentionDetails.getResponsable());
        mentionUpdated.setEtablissement(mentionDetails.getEtablissement());

        mentionRepository.save(mentionUpdated);
        return ResponseEntity.ok(mentionUpdated);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Mention> delete(@PathVariable int id){
        Mention mention = mentionRepository.findById(id).orElseThrow(()->new ResourceNotFound("not found : "+id));
        mentionRepository.delete(mention);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //get by etablissement
    @GetMapping("/id/{id}")
    public List<Mention> findByEtab(@PathVariable int id){
        Etablissement etablissement = etablissementRepository.findById(id).orElseThrow(()-> new ResourceNotFound("not found"+id));
        List <Mention> mentions = mentionRepository.findByEtablissement(etablissement);
        return mentions;
    }
    //get by Id
    @GetMapping("{id}")
    public ResponseEntity<Mention> getById(@PathVariable int id){
        Mention mention = mentionRepository.findById(id).orElseThrow(()-> new ResourceNotFound("mention not found"+id));
        return ResponseEntity.ok(mention);
    }

}
