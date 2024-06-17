package mk.ukim.finki.wp.teamprojects.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSubjectAllocationIdException extends RuntimeException {
    public InvalidSubjectAllocationIdException(Long id) {
        super("Invalid subject allocation ID: " + id);
    }
}
