package lalaina.zoul.ProjetBack.repository;

import lalaina.zoul.ProjetBack.model.Etablissement;
import lalaina.zoul.ProjetBack.model.Mention;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentionRepository extends JpaRepository<Mention, Integer>  {
    List<Mention> findByEtablissement(Etablissement etablissement);
}
