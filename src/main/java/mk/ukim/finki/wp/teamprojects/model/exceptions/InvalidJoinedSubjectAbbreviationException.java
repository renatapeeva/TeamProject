package mk.ukim.finki.wp.teamprojects.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJoinedSubjectAbbreviationException extends RuntimeException {
    public InvalidJoinedSubjectAbbreviationException(String abbreviation) {
        super("Invalid joined subject abbreviation: " + abbreviation);
    }
}