package isep.webtechno.placeholder.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservations {
    private Long id;

    @GeneratedValue
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String isValidated;

    @Basic(optional = false)
    public String getIsValidated() {
        return isValidated;
    }

    public void setIsValidated(String isValidated) {
        isValidated = isValidated;
    }

    private LocalDate startDate;

    @Column(columnDefinition = "DATE",nullable = false)
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    private LocalDate endDate;

    @Column(columnDefinition = "DATE",nullable = false)
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    private User_bak user;

    @ManyToOne(optional = false)
    public User_bak getUser() {
        return user;
    }

    public void setUser(User_bak user) {
        this.user = user;
    }

    private Maisons maison;

    @ManyToOne
    public Maisons getMaison() {
        return maison;
    }

    public void setMaison(Maisons maison) {
        this.maison = maison;
    }
}
