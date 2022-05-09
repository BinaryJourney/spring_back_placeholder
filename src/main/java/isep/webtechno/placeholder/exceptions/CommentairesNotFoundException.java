package isep.webtechno.placeholder.exceptions;

public class CommentairesNotFoundException extends RuntimeException {

    public CommentairesNotFoundException(Long id) {
        super("Ne parviens pas à trouver la maison " + id);
    }
}
