package isep.webtechno.placeholder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class CommentairesNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CommentairesNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String commentaireNotFoundHandler(MaisonsNotFoundException ex) {
        return ex.getMessage();
    }
}