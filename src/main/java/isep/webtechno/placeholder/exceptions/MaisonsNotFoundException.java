package isep.webtechno.placeholder.exceptions;

public class MaisonsNotFoundException extends RuntimeException {

    public MaisonsNotFoundException(Long id) {
        super("Ne parviens pas à trouver la maison " + id);
    }
}
