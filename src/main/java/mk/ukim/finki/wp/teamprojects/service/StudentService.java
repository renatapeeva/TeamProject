package mk.ukim.finki.wp.teamprojects.service;

import mk.ukim.finki.wp.teamprojects.model.DTO.StudentDto;
import mk.ukim.finki.wp.teamprojects.model.Student;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Optional<Student> getStudentById(String id);

    Page<Student> find(Integer page, Integer size, String nameOrIndex, String studyProgramCode);

    List<StudentDto> importStudents(List<StudentDto> students);
}
