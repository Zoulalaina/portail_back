package lalaina.zoul.ProjetBack.model;

import jakarta.persistence.*;

@Table(name = "filiere")
@Entity
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idFiliere;
    private String nomFiliere;
    private String ConditionAdmission;
    private double cout;
    @ManyToOne
    private Universite universite;
    @ManyToOne
    private Etablissement etablissement;

    public Filiere(int idFiliere, String nomFiliere, String conditionAdmission, double cout, Universite universite) {
        this.idFiliere = idFiliere;
        this.nomFiliere = nomFiliere;
        ConditionAdmission = conditionAdmission;
        this.cout = cout;
        this.universite = universite;
    }

    public Filiere() {
    }

    public int getIdFiliere() {
        return idFiliere;
    }

    public void setIdFiliere(int idFiliere) {
        this.idFiliere = idFiliere;
    }

    public String getNomFiliere() {
        return nomFiliere;
    }

    public void setNomFiliere(String nomFiliere) {
        this.nomFiliere = nomFiliere;
    }

    public String getConditionAdmission() {
        return ConditionAdmission;
    }

    public void setConditionAdmission(String conditionAdmission) {
        ConditionAdmission = conditionAdmission;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public Universite getUniversite() {
        return universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }
}
