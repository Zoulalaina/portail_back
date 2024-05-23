package lalaina.zoul.ProjetBack.repository;

import lalaina.zoul.ProjetBack.model.Mention;
import lalaina.zoul.ProjetBack.model.Parcours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParcoursRepository extends JpaRepository<Parcours, Integer> {
    List<Parcours> findByMention(Mention mention);
}
