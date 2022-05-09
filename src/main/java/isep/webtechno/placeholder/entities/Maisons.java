package isep.webtechno.placeholder.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity(name = "Maisons")
public class Maisons {

    public Maisons() {}

    public Maisons(String titre, String description, String listeServices) {

        this.titre = titre;
        this.description = description;
        this.listeServices = listeServices;
    }

    private Long id;

    @GeneratedValue
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String titre;

    @Basic(optional = false)
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        titre = titre;
    }

    private String description;

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    private String listeServices;

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getListeServices() {
        return listeServices;
    }

    public void setListeServices(String liste_services) {
        this.listeServices = liste_services;
    }

    private LocalDate dateDispoDebut;

    @Column(columnDefinition = "DATE", nullable = false)
    public LocalDate getDateDispoDebut() {
        return dateDispoDebut;
    }

    public void setDateDispoDebut(LocalDate dateDispoDebut) {
        this.dateDispoDebut = dateDispoDebut;
    }

    private LocalDate dateDispoFin;

    @Column(columnDefinition = "DATE", nullable = false)
    public LocalDate getDateDispoFin() {
        return dateDispoFin;
    }

    public void setDateDispoFin(LocalDate dateDispoFin) {
        this.dateDispoFin = dateDispoFin;
    }

    private Users user;

    @ManyToOne(optional = false)
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    private Set<Tags> tags;

    @ManyToMany(mappedBy = "maison")
    public Set<Tags> getTags() {
        return tags;
    }

    public void setTags(Set<Tags> tags) {
        this.tags = tags;
    }

    private List<Images> Images;

    @OneToMany
    public List<Images> getImages() {
        return Images;
    }

    public void setImages(List<Images> images) {
        Images = images;
    }

    private List<isep.webtechno.placeholder.entities.Commentaires> Commentaires;

    @OneToMany
    public List<isep.webtechno.placeholder.entities.Commentaires> getCommentaires() {
        return Commentaires;
    }

    public void setCommentaires(List<isep.webtechno.placeholder.entities.Commentaires> commentaires) {
        Commentaires = commentaires;
    }

    // TODO Maison.images

    // TODO toutes les relations many-many many-one etc.

    @Override
    public String toString() {
        return "Maison{" + "id=" + this.id + ", titre='" + this.titre + '\'' + ", description='" + this.description + '\'' + '}';
    }

    private List<Reservations> reservations;

    @OneToMany
    public List<Reservations> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservations> reservations) {
        this.reservations = reservations;
    }
}
