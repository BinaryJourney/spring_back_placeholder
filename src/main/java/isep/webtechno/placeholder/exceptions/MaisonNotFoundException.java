package isep.webtechno.placeholder.exceptions;

public class MaisonNotFoundException extends RuntimeException {

    public MaisonNotFoundException(Long id) {
        super("Ne parviens pas à trouver la maison " + id);
    }
}
