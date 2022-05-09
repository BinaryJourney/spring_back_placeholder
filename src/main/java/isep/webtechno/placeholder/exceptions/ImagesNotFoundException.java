package isep.webtechno.placeholder.exceptions;

public class ImagesNotFoundException extends RuntimeException {

    public ImagesNotFoundException(Long id) {
        super("Ne parviens pas à trouver la maison " + id);
    }
}
