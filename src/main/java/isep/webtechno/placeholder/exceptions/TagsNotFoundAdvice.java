package isep.webtechno.placeholder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class TagsNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(TagsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String TagsNotFoundHandler(TagsNotFoundException ex){return ex.getMessage();}
}
