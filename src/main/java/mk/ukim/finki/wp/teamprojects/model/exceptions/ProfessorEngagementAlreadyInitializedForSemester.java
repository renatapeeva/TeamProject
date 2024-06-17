package mk.ukim.finki.wp.teamprojects.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ProfessorEngagementAlreadyInitializedForSemester extends RuntimeException {
    public ProfessorEngagementAlreadyInitializedForSemester(String semesterCode) {
        super("Professor engagement already initialized for semester: " + semesterCode);
    }
}
