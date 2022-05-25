package isep.webtechno.placeholder.forms;

import isep.webtechno.placeholder.entities.Images;
import isep.webtechno.placeholder.entities.Tags;
import isep.webtechno.placeholder.entities.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

public class MaisonForm {

    private String titre;

    @NotNull
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    private String description;

    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String listeServices;

    @NotNull
    public String getListeServices() {
        return listeServices;
    }

    public void setListeServices(String liste_services) {
        this.listeServices = liste_services;
    }

    private LocalDate dateDispoDebut;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    public LocalDate getDateDispoDebut() {
        return dateDispoDebut;
    }

    public void setDateDispoDebut(LocalDate dateDispoDebut) {
        this.dateDispoDebut = dateDispoDebut;
    }

    private LocalDate dateDispoFin;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    public LocalDate getDateDispoFin() {
        return dateDispoFin;
    }

    public void setDateDispoFin(LocalDate dateDispoFin) {
        this.dateDispoFin = dateDispoFin;
    }

//    private User user;
//
//    @Nullable
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

//    private Set<Tags> tags;
//
//    public Set<Tags> getTags() {
//        return tags;
//    }
//
//    public void setTags(Set<Tags> tags) {
//        this.tags = tags;
//    }

    @Override
    public String toString() {
        return "MaisonForm{" +
                "titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", listeServices='" + listeServices + '\'' +
                ", dateDispoDebut=" + dateDispoDebut +
                ", dateDispoFin=" + dateDispoFin +
                '}';
    }
}
