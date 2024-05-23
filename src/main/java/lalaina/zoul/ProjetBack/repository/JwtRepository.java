package lalaina.zoul.ProjetBack.repository;

import lalaina.zoul.ProjetBack.model.Jwt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.stream.Stream;

public interface JwtRepository extends JpaRepository<Jwt, Integer> {

    Optional<Jwt>  findByValeur(String value);

    Optional<Jwt> findByValeurAndDesactiveAndExpire(String valeur, boolean desactive, boolean Expire);
    @Query("FROM Jwt j WHERE j.expire = :Expire AND j.desactive = :desactive AND j.user.email = :email ")
    Optional<Jwt> findByUtilisateurValidToken(String email, boolean desactive, boolean Expire);

    @Query("FROM Jwt j WHERE j.user.email = :email ")
    Stream<Jwt> findByUtilisateur(String email);

    void deleteAllByExpireAndDesactive(boolean expire, boolean desactive);
}
