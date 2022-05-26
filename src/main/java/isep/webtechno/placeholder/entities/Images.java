package isep.webtechno.placeholder.entities;

import javax.persistence.*;

@Entity(name = "Images")
public class Images {

    public Images(String filename, Maisons maison) {
        this.filename = filename;
        this.maison = maison;
    }

    public Images(String filename) {
        this.filename = filename;
    }

    public Images() {

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

    private String filename;

    @Basic(optional = false)
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    private Commentaires commentaires;

    @ManyToOne
    public Commentaires getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Commentaires commentaires) {
        this.commentaires = commentaires;
    }

    private Maisons maison;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "maison_id")
    public Maisons getMaison() {
        return maison;
    }

    public void setMaison(Maisons maison) {
        this.maison = maison;
    }

    private Messages messages;

    @ManyToOne
    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    @Transient
    public String getPhotosImagePath() {
        if (filename == null || id == null) return null;

        return "/images/" + filename;
    }
}
