package isep.webtechno.placeholder.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import isep.webtechno.placeholder.entities.Images;

@Entity(name = "Tags")
public class Tags {

    private Long id;

    @GeneratedValue
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String type;

    @Basic(optional = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String libelle;

    @Basic(optional = false)
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    private Boolean isOptionnel;

    @Basic(optional = false)
    public Boolean getOptionnel() {
        return isOptionnel;
    }

    public void setOptionnel(Boolean optionnel) {
        isOptionnel = optionnel;
    }

    private Set<Maisons> maison;

    @ManyToMany
    @JoinTable(
            name = "tags_maison",
            joinColumns = @JoinColumn(name = "tags_id"),
            inverseJoinColumns = @JoinColumn(name = "maison_id")
    )
    public Set<Maisons> getMaison() {
        return maison;
    }

    public void setMaison(Set<Maisons> maison) {
        this.maison = maison;
    }
}
