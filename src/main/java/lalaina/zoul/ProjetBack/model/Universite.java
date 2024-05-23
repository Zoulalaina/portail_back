package lalaina.zoul.ProjetBack.model;

import jakarta.persistence.*;

@Entity
@Table(name = "universite")
public class Universite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUniversite;
    @Column(name = "nom_univ")
    private String nomUniversite;
    @Column(name = "siege_univ")
    private String siegeUniversite;
    private String historique;



    private byte[] image;


    public Universite(int idUniversite, String nomUniversite, String siegeUniversite,String historique, byte[] image) {
        this.idUniversite = idUniversite;
        this.nomUniversite = nomUniversite;
        this.siegeUniversite = siegeUniversite;
        this.historique = historique;
        this.image=image;

    }

    public Universite() {
    }

    public int getIdUniversite() {
        return idUniversite;
    }

    public void setIdUniversite(int idUniversite) {
        this.idUniversite = idUniversite;
    }

    public String getNomUniversite() {
        return nomUniversite;
    }

    public void setNomUniversite(String nomUniversite) {
        this.nomUniversite = nomUniversite;
    }

    public String getSiegeUniversite() {
        return siegeUniversite;
    }

    public void setSiegeUniversite(String siegeUniversite) {
        this.siegeUniversite = siegeUniversite;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    public String getHistorique() {
        return historique;
    }

    public void setHistorique(String historique) {
        this.historique = historique;
    }
}
