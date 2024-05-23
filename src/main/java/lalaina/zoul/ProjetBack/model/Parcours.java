package lalaina.zoul.ProjetBack.model;

import jakarta.persistence.*;

@Entity
@Table(name = "parcours")
public class Parcours {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idParcours;
    private String nomParcours;
    private String niveau;
    private String responsable;
    private String codition;
    private String langue;
    private String capasite;
    private String cout;
    private String bourse;
    @ManyToOne
    private Mention mention;

    public Parcours() {
    }

    public Parcours(int idParcours, String nomParcours, String niveau, String responsable, String codition, String langue, String capasite, Mention mention, String cout, String bourse) {
        this.idParcours = idParcours;
        this.nomParcours = nomParcours;
        this.niveau = niveau;
        this.responsable = responsable;
        this.codition = codition;
        this.langue = langue;
        this.capasite = capasite;
        this.cout = cout;
        this.bourse = bourse;
        this.mention = mention;
    }

    public int getIdParcours() {
        return idParcours;
    }

    public String getBourse() {
        return bourse;
    }

    public void setBourse(String bourse) {
        this.bourse = bourse;
    }

    public String getCout() {
        return cout;
    }

    public void setCout(String cout) {
        this.cout = cout;
    }

    public void setIdParcours(int idParcours) {
        this.idParcours = idParcours;
    }

    public String getNomParcours() {
        return nomParcours;
    }

    public void setNomParcours(String nomParcours) {
        this.nomParcours = nomParcours;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getCodition() {
        return codition;
    }

    public void setCodition(String codition) {
        this.codition = codition;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getCapasite() {
        return capasite;
    }

    public void setCapasite(String capasite) {
        this.capasite = capasite;
    }

    public Mention getMention() {
        return mention;
    }

    public void setMention(Mention mention) {
        this.mention = mention;
    }
}
