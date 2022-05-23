package isep.webtechno.placeholder.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Commentaires")
public class Commentaires {

    private Long id;
    @GeneratedValue
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Integer note;

    @Basic(optional = false)
    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    private String text;

    @Column(columnDefinition = "TEXT")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String titre;

    @Column(columnDefinition = "TEXT",nullable = true)
    public String getTitre(){return titre;}

    public void setTitre(String titre){this.titre=titre;}

    private LocalDateTime timestamp;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    private User users;
    @ManyToOne(optional = false)
    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    private Maisons maison;

    @ManyToOne(optional = false)
    public Maisons getMaison() {
        return maison;
    }

    public void setMaison(Maisons maison) {
        this.maison = maison;
    }

    private List<Images> Images;

    @OneToMany
    public List<Images> getImages() {
        return Images;
    }

    public void setImages(List<Images> images) {
        Images = images;
    }
}
