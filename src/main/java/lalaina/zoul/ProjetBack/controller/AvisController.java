package lalaina.zoul.ProjetBack.controller;

import lalaina.zoul.ProjetBack.execp.ResourceNotFound;
import lalaina.zoul.ProjetBack.model.Avis;
import lalaina.zoul.ProjetBack.model.Ecole;
import lalaina.zoul.ProjetBack.model.Universite;
import lalaina.zoul.ProjetBack.model.User;
import lalaina.zoul.ProjetBack.repository.AvisRepository;
import lalaina.zoul.ProjetBack.repository.UnivRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/avis")
public class AvisController {
    @Autowired
    private AvisRepository avisRepository;
    @Autowired
    private UnivRepository univRepository;
    @PostMapping
    public Avis create(@RequestBody Avis avis){
        User utilisateur = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        avis.setUser(utilisateur);
        return avisRepository.save(avis);
    }
    @GetMapping("{id}")
    public List<Avis> getbyUniv(@PathVariable int id){
        Universite universite = univRepository.findById(id).orElseThrow(()->new ResourceNotFound("Univ not found: "+id));
        List <Avis> avis = avisRepository.findByUniversite(universite);
        return avis;
    }
}
