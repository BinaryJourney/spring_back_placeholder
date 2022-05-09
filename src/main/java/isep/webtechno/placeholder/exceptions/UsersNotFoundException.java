package isep.webtechno.placeholder.exceptions;

public class UsersNotFoundException extends RuntimeException{
    public UsersNotFoundException(Long id){super("Utilisateur introuvable");}
}
