package isep.webtechno.placeholder.exceptions;

public class TagsNotFoundException extends RuntimeException{
    public TagsNotFoundException(Long id){super("Tag introuvable");}
}
