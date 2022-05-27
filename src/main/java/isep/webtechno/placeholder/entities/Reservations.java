package isep.webtechno.placeholder.entities;

import org.checkerframework.checker.units.qual.A;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Basic
    public String getIsValidated() {
        return isValidated;
    }

    public void setIsValidated(String isValidated) {
        isValidated = isValidated;
    }

    private LocalDate startDate;

    @Column(columnDefinition = "DATE",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    private LocalDate endDate;

    @Column(columnDefinition = "DATE",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Maisons maison;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Maisons getMaison() {
        return maison;
    }

    public void setMaison(Maisons maison) {
        this.maison = maison;
    }

    @Override
    public String toString() {
        return "Reservations{" +
                "id=" + id +
                ", isValidated='" + isValidated + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", user=" + user +
                ", maison=" + maison +
                '}';
    }
}
