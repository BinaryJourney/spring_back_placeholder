package isep.webtechno.placeholder.exceptions;

public class MessagesNotFoundException extends RuntimeException {

    public MessagesNotFoundException(Long id) {
        super("Ne parvient pas à trouver le message " + id);
    }
}
