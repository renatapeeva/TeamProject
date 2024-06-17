package mk.ukim.finki.wp.teamprojects.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeacherSubjectAllocationNotFoundException extends RuntimeException {
    public TeacherSubjectAllocationNotFoundException(String message) {
        super(message);
    }
}


