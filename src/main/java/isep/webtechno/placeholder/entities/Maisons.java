package isep.webtechno.placeholder.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity(name = "Maisons")
public class Maisons {

    public Maisons() {}

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
    @NotNull
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    private String description;

    @Column(columnDefinition = "TEXT", nullable = false)
    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String listeServices;

    @Column(columnDefinition = "TEXT", nullable = false)
    @NotNull
    public String getListeServices() {
        return listeServices;
    }

    public void setListeServices(String liste_services) {
        this.listeServices = liste_services;
    }

    private LocalDate dateDispoDebut;

    @Column(columnDefinition = "DATE", nullable = false)
    @NotNull
    public LocalDate getDateDispoDebut() {
        return dateDispoDebut;
    }

    public void setDateDispoDebut(LocalDate dateDispoDebut) {
        this.dateDispoDebut = dateDispoDebut;
    }

    private LocalDate dateDispoFin;

    @Column(columnDefinition = "DATE", nullable = false)
    @NotNull
    public LocalDate getDateDispoFin() {
        return dateDispoFin;
    }

    public void setDateDispoFin(LocalDate dateDispoFin) {
        this.dateDispoFin = dateDispoFin;
    }

    private User user;

    @ManyToOne(optional = false)
    @NotNull
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

//    @OneToMany
//    public List<Images> getImages() {
//        return Images;
//    }
//
//    public void setImages(List<Images> images) {
//        Images = images;
//    }
//
//    private List<Commentaires> Commentaires;
//
//    @OneToMany
//    public List<Commentaires> getCommentaires() {
//        return Commentaires;
//    }
//
//    public void setCommentaires(List<Commentaires> commentaires) {
//        Commentaires = commentaires;
//    }

    @Override
    public String toString() {
        return "Maisons{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", listeServices='" + listeServices + '\'' +
                ", dateDispoDebut=" + dateDispoDebut +
                ", dateDispoFin=" + dateDispoFin +
                '}';
    }


//    private List<Reservations> reservations;
//
//    @OneToMany
//    public List<Reservations> getReservations() {
//        return reservations;
//    }
//
//    public void setReservations(List<Reservations> reservations) {
//        this.reservations = reservations;
//    }

}
