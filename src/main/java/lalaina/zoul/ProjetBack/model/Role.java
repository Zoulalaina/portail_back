package lalaina.zoul.ProjetBack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private TypeRole libelle;

    public Role(int id, TypeRole libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeRole getLibelle() {
        return libelle;
    }

    public void setLibelle(TypeRole libelle) {
        this.libelle = libelle;
    }
}
