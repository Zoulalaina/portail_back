package lalaina.zoul.ProjetBack.model;

import jakarta.persistence.*;

@Entity
public class Mention {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idMention;
    private String nomMention;
    private String responsable;
    @ManyToOne
    private Etablissement etablissement;

    public Mention() {
    }

    public Mention(int idMention, String nomMention, String responsable, Etablissement etablissement) {
        this.idMention = idMention;
        this.nomMention = nomMention;
        this.responsable = responsable;
        this.etablissement = etablissement;
    }

    public int getIdMention() {
        return idMention;
    }

    public void setIdMention(int idMention) {
        this.idMention = idMention;
    }

    public String getNomMention() {
        return nomMention;
    }

    public void setNomMention(String nomMention) {
        this.nomMention = nomMention;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }
}
