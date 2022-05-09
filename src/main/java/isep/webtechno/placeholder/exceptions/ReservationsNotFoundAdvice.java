package isep.webtechno.placeholder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ReservationsNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ReservationsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ReservationNotFoundHandler(TagsNotFoundException ex){return ex.getMessage();}
}
