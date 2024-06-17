package mk.ukim.finki.wp.teamprojects.service.impl;


import mk.ukim.finki.wp.teamprojects.model.DTO.StudentDto;
import mk.ukim.finki.wp.teamprojects.model.Student;
import mk.ukim.finki.wp.teamprojects.repository.StudentRepository;
import mk.ukim.finki.wp.teamprojects.repository.StudyProgramRepository;
import mk.ukim.finki.wp.teamprojects.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudyProgramRepository studyProgramRepository;

    public StudentServiceImpl(StudentRepository studentRepository, StudyProgramRepository studyProgramRepository) {
        this.studentRepository = studentRepository;
        this.studyProgramRepository = studyProgramRepository;
    }


    @Override
    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }

    @Override
    public Page<Student> find(Integer page, Integer size, String nameOrIndex, String studyProgramCode) {
        return this.studentRepository.findAll(PageRequest.of(page - 1, size));
    }

    @Override
    public List<StudentDto> importStudents(List<StudentDto> students) {
        return students.stream()
                .map(dto -> saveStudent(dto))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<StudentDto> saveStudent(StudentDto dto) {
        try {
            Student student = new Student(dto.getIndex(),
                    dto.getEmail(),
                    dto.getFirstName(),
                    dto.getLastName(),
                    dto.getParentName(),
                    studyProgramRepository.getReferenceById(dto.getStudyProgramCode())
            );
            this.studentRepository.save(student);
            return Optional.empty();
        } catch (Exception e) {
            dto.setMessage(e.getMessage());
        }
        return Optional.of(dto);
    }
}
