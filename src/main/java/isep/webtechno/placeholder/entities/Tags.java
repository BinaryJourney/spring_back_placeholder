package isep.webtechno.placeholder.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    @Basic(optional = false)
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic(optional = false)
    private String libelle;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    @Basic(optional = false)
    private Boolean isOptionnel;

    public Boolean getOptionnel() {
        return isOptionnel;
    }

    public void setOptionnel(Boolean optionnel) {
        isOptionnel = optionnel;
    }


}
