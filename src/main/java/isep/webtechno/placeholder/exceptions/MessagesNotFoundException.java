package isep.webtechno.placeholder.exceptions;

public class MessagesNotFoundException extends RuntimeException {

    public MessagesNotFoundException(Long id) {
        super("Ne parviens pas à trouver la maison " + id);
    }
}
