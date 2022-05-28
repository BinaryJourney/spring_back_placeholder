package isep.webtechno.placeholder.exceptions;

public class ImagesNotFoundException extends RuntimeException {

    public ImagesNotFoundException(Long id) {
        super("Ne parvient pas à trouver l'image " + id);
    }
}
