package isep.webtechno.placeholder.exceptions;

public class ReservationsNotFoundException extends RuntimeException {
    public ReservationsNotFoundException(Long id){
        super("Reservation introuvable");
    }
}
