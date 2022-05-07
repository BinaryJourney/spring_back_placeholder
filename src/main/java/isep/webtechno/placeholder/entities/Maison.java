package isep.webtechno.placeholder.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Maison")
public class Maison {

    public Maison() {}

    public Maison(String titre, String description, String listeServices) {

        this.titre = titre;
        this.description = description;
        this.listeServices = listeServices;
    }

    @GeneratedValue
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional = false)
    private String titre;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        titre = titre;
    }

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    @Column(columnDefinition = "TEXT", nullable = false)
    private String listeServices;

    public String getListeServices() {
        return listeServices;
    }

    public void setListeServices(String liste_services) {
        this.listeServices = liste_services;
    }

    @Column(columnDefinition = "DATE", nullable = true)
    private LocalDate dateDispoDebut;

    public LocalDate getDateDispoDebut() {
        return dateDispoDebut;
    }

    public void setDateDispoDebut(LocalDate dateDispoDebut) {
        this.dateDispoDebut = dateDispoDebut;
    }

    @Column(columnDefinition = "DATE", nullable = true)
    private LocalDate dateDispoFin;

    public LocalDate getDateDispoFin() {
        return dateDispoFin;
    }

    public void setDateDispoFin(LocalDate dateDispoFin) {
        this.dateDispoFin = dateDispoFin;
    }

    // TODO Maison.images

    // TODO toutes les relations many-many many-one etc.

    @Override
    public String toString() {
        return "Maison{" + "id=" + this.id + ", titre='" + this.titre + '\'' + ", description='" + this.description + '\'' + '}';
    }
}
