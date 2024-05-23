package lalaina.zoul.ProjetBack.model;

import jakarta.persistence.*;
import lombok.Builder;

@Builder
@Entity
public class Jwt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String valeur;
    private boolean desactive;
    private boolean expire;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "utilisateur_id")
    private User user;

    public Jwt() {
    }

    public Jwt(int id,String valeur, boolean desactive, boolean expire, User user) {
        this.id = id;
        this.valeur = valeur;
        this.desactive = desactive;
        this.expire = expire;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDesactive() {
        return desactive;
    }

    public void setDesactive(boolean desactive) {
        this.desactive = desactive;
    }

    public boolean isExpire() {
        return expire;
    }

    public void setExpire(boolean expire) {
        this.expire = expire;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
