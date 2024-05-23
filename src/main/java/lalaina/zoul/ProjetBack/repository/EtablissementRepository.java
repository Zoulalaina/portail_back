package lalaina.zoul.ProjetBack.repository;

import lalaina.zoul.ProjetBack.model.Etablissement;
import lalaina.zoul.ProjetBack.model.Universite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtablissementRepository extends JpaRepository<Etablissement, Integer> {

    List<Etablissement> findByUniversite(Universite universite);
}
