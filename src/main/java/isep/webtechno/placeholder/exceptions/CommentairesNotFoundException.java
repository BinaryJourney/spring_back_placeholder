package isep.webtechno.placeholder.exceptions;

public class CommentairesNotFoundException extends RuntimeException {

    public CommentairesNotFoundException(Long id) {
        super("Ne parvient pas à trouver le commentaire " + id);
    }
}
