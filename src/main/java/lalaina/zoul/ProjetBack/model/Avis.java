package lalaina.zoul.ProjetBack.model;

import jakarta.persistence.*;

@Entity
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idAvis;
    private String message;
    @ManyToOne
    private User user;
    @ManyToOne
    private Universite universite;

    public Avis(int idAvis, String message, User user, Universite universite) {
        this.idAvis = idAvis;
        this.message = message;
        this.user = user;
        this.universite = universite;
    }

    public Avis() {
    }

    public Universite getUniversite() {
        return universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(int idAvis) {
        this.idAvis = idAvis;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
