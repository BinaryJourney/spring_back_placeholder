package isep.webtechno.placeholder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class MaisonsNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(MaisonsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String maisonNotFoundHandler(MaisonsNotFoundException ex) {
        return ex.getMessage();
    }
}