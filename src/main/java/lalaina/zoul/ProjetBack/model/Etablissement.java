package lalaina.zoul.ProjetBack.model;

import jakarta.persistence.*;
@Entity
@Inheritance
public class Etablissement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idEtab;
    private String nom;
    @ManyToOne
    private Universite universite;

    public Etablissement() {
    }

    public Etablissement(int idEtab, String nom, Universite universite) {
        this.idEtab = idEtab;
        this.nom = nom;
        this.universite = universite;
    }

    public int getIdEtab() {
        return idEtab;
    }

    public void setIdEtab(int idEtab) {
        this.idEtab = idEtab;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Universite getUniversite() {
        return universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }
}
