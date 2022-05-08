package isep.webtechno.placeholder.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private Users sendingUser;

    @ManyToOne
    public Users getSendingUser() {
        return sendingUser;
    }

    public void setSendingUser(Users sendingUser) {
        this.sendingUser = sendingUser;
    }

    private Users receivingUser;

    @ManyToOne
    public Users getReceivingUser() {
        return receivingUser;
    }

    public void setReceivingUser(Users receivingUser) {
        this.receivingUser = receivingUser;
    }
}
