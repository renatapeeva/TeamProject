package mk.ukim.finki.wp.teamprojects.model.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"index", "email", "lastName", "firstName", "parentName", "studyProgramCode", "message"})
public class StudentDto {

    private String index;

    private String email;

    private String lastName;

    private String firstName;

    private String parentName;

    private String studyProgramCode;

    private String message;

}
