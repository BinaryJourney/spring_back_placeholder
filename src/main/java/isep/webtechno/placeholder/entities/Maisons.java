package isep.webtechno.placeholder.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity(name = "Maisons")
public class Maisons {

    public Maisons() {}

    public Maisons(String titre, String description, String listeServices, LocalDate dateDispoDebut, LocalDate dateDispoFin, User user) {
        this.titre = titre;
        this.description = description;
        this.listeServices = listeServices;
        this.dateDispoDebut = dateDispoDebut;
        this.dateDispoFin = dateDispoFin;
        this.user = user;
    }

    private Long id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    public LocalDate getDateDispoDebut() {
        return dateDispoDebut;
    }

    public void setDateDispoDebut(LocalDate dateDispoDebut) {
        this.dateDispoDebut = dateDispoDebut;
    }

    private LocalDate dateDispoFin;

    @Column(columnDefinition = "DATE", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    public LocalDate getDateDispoFin() {
        return dateDispoFin;
    }

    public void setDateDispoFin(LocalDate dateDispoFin) {
        this.dateDispoFin = dateDispoFin;
    }

    private User user;

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Set<Tags> tags;

    @ManyToMany
    public Set<Tags> getTags() {
        return tags;
    }

    public void setTags(Set<Tags> tags) {
        this.tags = tags;
    }

    public void addTags(Tags tag){
        //As said Hibernate will ignore it when persist this relationship.
        //Add it mainly for the consistency of this relationship for both side in the Java instance
        this.tags.add(tag);
//        image.setMaison(this);
    }

    private List<Images> images = new ArrayList<>();

    @OneToMany(mappedBy = "maison", cascade = CascadeType.ALL)
    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }
    public void addImages(Images image){
        //As said Hibernate will ignore it when persist this relationship.
        //Add it mainly for the consistency of this relationship for both side in the Java instance
        this.images.add(image);
//        image.setMaison(this);
    }

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
                ", user=" + user +
                ", images" + images +
                ", tags" + tags +
                '}';
    }

    private List<Reservations> reservations;

    @OneToMany(targetEntity = Reservations.class, mappedBy = "maison", cascade = CascadeType.ALL)
    public List<Reservations> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservations> reservations) {
        this.reservations = reservations;
    }

}
