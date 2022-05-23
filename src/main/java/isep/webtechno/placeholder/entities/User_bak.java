package isep.webtechno.placeholder.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Users")
public class User_bak {

    private Long id;

    public User_bak(){}

    public User_bak(String prenom, String nom, String email, String password, String role){
        this.prenom=prenom;
        this.nom=nom;
        this.email=email;
        this.password=password;
        this.role=role;
    }
    @GeneratedValue
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String nom;

    @Basic(optional = false)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private String prenom;

    @Basic(optional = false)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    private String email;

    @Basic(optional = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String password;

    @Column(length = 1020, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String role;

    @Basic(optional = false)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private List<isep.webtechno.placeholder.entities.Maisons> Maisons;

    @OneToMany
    public List<isep.webtechno.placeholder.entities.Maisons> getMaisons() {
        return Maisons;
    }

    public void setMaisons(List<isep.webtechno.placeholder.entities.Maisons> maisons) {
        Maisons = maisons;
    }

    private List<Reservations> oneToMany;

    @OneToMany
    public List<Reservations> getOneToMany() {
        return oneToMany;
    }

    public void setOneToMany(List<Reservations> oneToMany) {
        this.oneToMany = oneToMany;
    }
}
