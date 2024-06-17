package mk.ukim.finki.wp.teamprojects.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTeacherSubjectRequestIdException extends RuntimeException {
    public InvalidTeacherSubjectRequestIdException(Long id) {
        super("Invalid teacher subject request ID: " + id);
    }
}
