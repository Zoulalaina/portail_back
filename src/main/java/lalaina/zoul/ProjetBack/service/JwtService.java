package lalaina.zoul.ProjetBack.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lalaina.zoul.ProjetBack.model.Jwt;
import lalaina.zoul.ProjetBack.model.User;
import lalaina.zoul.ProjetBack.repository.JwtRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class JwtService {
    public static final String BEARER = "bearer";
    private final String ENCRIPTION_KEY = "608f36e92dc66d97d5933f0e6371493cb4fc05b1aa8f8de64014732472303a7c";
    @Autowired
    private UserService userService;
    @Autowired
    private JwtRepository jwtRepository;
    public Jwt tokenByValue(String value) {
        return this.jwtRepository.findByValeurAndDesactiveAndExpire(value,false,false)
                .orElseThrow(()-> new RuntimeException("token inconnu"));
    }
public Map<String, String> generate(String username){


    User user = (User) this.userService.loadUserByUsername(username);
    this.disableToken(user);
    final Map<String, String> jwtMap = this.generateJwt(user);

    Jwt jwt  = new Jwt();
    jwt.setValeur(jwtMap.get(BEARER));
    jwt.setDesactive(false);
    jwt.setExpire(false);
    jwt.setUser(user);
    this.jwtRepository.save(jwt);

    return jwtMap;
}
    private void disableToken(User utilisateur){
        List<Jwt> jwtList = this.jwtRepository.findByUtilisateur(utilisateur.getEmail()).peek(
                jwt -> {
                    jwt.setDesactive(true);
                    jwt.setExpire(true);
                }
        ).collect(Collectors.toList());
        this.jwtRepository.saveAll(jwtList);
    }


    public String extractUsername(String token) {
        return this.getClaim(token, Claims::getSubject);
    }
    public boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return this.getClaim(token, Claims::getExpiration);
    }
    private <T> T getClaim(String token, Function<Claims, T> function) {
        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }
    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Map<String, String> generateJwt(User user) {
        final long currentTime = System.nanoTime();
        final long expirationTime = currentTime + 30 * 60 * 1000;

        final Map<String, Object> claims = Map.of(
                "nom", user.getUserName(),
                Claims.EXPIRATION, new Date(expirationTime),
                Claims.SUBJECT, user.getEmail()
        );

        final String bearer = Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .setSubject(user.getEmail())
                .setClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        return Map.of(BEARER, bearer) ;
}
private Key getKey(){
    final byte[] decoder = Decoders.BASE64.decode(ENCRIPTION_KEY);
    return Keys.hmacShaKeyFor(decoder);
}


    public void deconnexion() {
        User utilisateur =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Jwt jwt = this.jwtRepository.findByUtilisateurValidToken(utilisateur.getEmail(),false,false).orElseThrow(()-> new RuntimeException("token invalide"));
        jwt.setExpire(true);
        jwt.setDesactive(true);
        this.jwtRepository.save(jwt);

    }
    @Scheduled(cron = "0 */1 * * * *")
    public void removeUselessJwt(){

        this.jwtRepository.deleteAllByExpireAndDesactive(true,true);
    }
}
