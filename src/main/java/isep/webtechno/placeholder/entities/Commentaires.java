package isep.webtechno.placeholder.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private LocalDateTime timestamp;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    private Users users;
    @ManyToOne
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    private Maisons maison;

    @ManyToOne
    public Maisons getMaison() {
        return maison;
    }

    public void setMaison(Maisons maison) {
        this.maison = maison;
    }

    //TODO Commentaire.image + Commentaire.toString

}
