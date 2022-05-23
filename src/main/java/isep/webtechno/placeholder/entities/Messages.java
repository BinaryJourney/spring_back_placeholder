package isep.webtechno.placeholder.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Messages")
public class Messages {

    private Long id;

    @GeneratedValue
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String texte;

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    private LocalDateTime timestamp;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    private User sendingUser;

    @ManyToOne
    public User getSendingUser() {
        return sendingUser;
    }

    public void setSendingUser(User sendingUser) {
        this.sendingUser = sendingUser;
    }

    private User receivingUser;

    @ManyToOne
    public User getReceivingUser() {
        return receivingUser;
    }

    public void setReceivingUser(User receivingUser) {
        this.receivingUser = receivingUser;
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
