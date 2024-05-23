package lalaina.zoul.ProjetBack.repository;

import lalaina.zoul.ProjetBack.model.Avis;
import lalaina.zoul.ProjetBack.model.Universite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvisRepository extends JpaRepository<Avis, Integer> {
    List<Avis> findByUniversite(Universite universite);
}
