package isep.webtechno.placeholder.controllers;

import isep.webtechno.placeholder.entities.Reservations;
import isep.webtechno.placeholder.exceptions.ReservationsNotFoundException;
import isep.webtechno.placeholder.repositories.ReservationsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationsController {

    private final ReservationsRepository reservationsRepository;

    ReservationsController(ReservationsRepository reservationRepository) {
        this.reservationsRepository = reservationRepository;
    }

    @GetMapping("/reservations")
    List<Reservations> all() {
        return reservationsRepository.findAll();
    }

    @PostMapping("/reservations")
    Reservations newReservation(@RequestBody Reservations newReservation) {
        return reservationsRepository.save(newReservation);
    }

    @GetMapping("reservations/{id}")
    Reservations one(@PathVariable Long id) {

        return reservationsRepository.findById(id)
                .orElseThrow(() -> new ReservationsNotFoundException(id));
    }

    @PutMapping("/reservations/{id}")
    Reservations replaceReservation(@RequestBody Reservations newReservation, @PathVariable Long id) {

        return reservationsRepository.findById(id)
                .map(reservation -> {
                    reservation.setUser(newReservation.getUser());
                    reservation.setStartDate(newReservation.getStartDate());
                    reservation.setEndDate(newReservation.getEndDate());
                    reservation.setIsValidated(newReservation.getIsValidated());
                    return reservationsRepository.save(reservation);
                })
                .orElseGet(() -> {
                    newReservation.setId(id);
                    return reservationsRepository.save(newReservation);
                });
    }

    @DeleteMapping("/reservations/{id}")
    void deleteReservation(@PathVariable Long id) {
        reservationsRepository.deleteById(id);
    }
}
