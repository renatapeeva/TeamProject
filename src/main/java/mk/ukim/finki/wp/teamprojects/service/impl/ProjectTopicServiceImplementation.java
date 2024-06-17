package mk.ukim.finki.wp.teamprojects.service.impl;

import mk.ukim.finki.wp.teamprojects.model.Professor;
import mk.ukim.finki.wp.teamprojects.model.ProjectTopic;
import mk.ukim.finki.wp.teamprojects.model.Student;
import mk.ukim.finki.wp.teamprojects.model.exceptions.InvalidTopicIdEception;
import mk.ukim.finki.wp.teamprojects.model.exceptions.InvalidTopicNameException;
import mk.ukim.finki.wp.teamprojects.repository.ProjectTopicRepository;
import mk.ukim.finki.wp.teamprojects.repository.StudentRepository;
import mk.ukim.finki.wp.teamprojects.service.ProjectTopicService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProjectTopicServiceImplementation implements ProjectTopicService {
    private final ProjectTopicRepository projectTopicRepository;
    private final StudentRepository studentRepository;

    public ProjectTopicServiceImplementation(ProjectTopicRepository projectTopicRepository, StudentRepository studentRepository) {
        this.projectTopicRepository = projectTopicRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<ProjectTopic> getAllTopics() {
        return projectTopicRepository.findAll();
    }

    @Override
    public ProjectTopic getTopicById(Long id) {
        return projectTopicRepository.findById(id).orElseThrow(InvalidTopicIdEception::new);
    }

    @Override
    public ProjectTopic getTopicByName(String name) {
        return projectTopicRepository.findByTitle(name).orElseThrow(InvalidTopicNameException::new);
    }

    @Override
    public ProjectTopic addTopic(String title, String description, Integer counter, Professor professor, LocalDateTime dueDate) {
        List<Student> students=new ArrayList<>();
        ProjectTopic topic=new ProjectTopic(title,description,counter,students,professor,dueDate);
        return projectTopicRepository.save(topic);
    }

    @Override
    public ProjectTopic updateTopic(Long id, String title, String description, Integer counter, List<String> students, Professor professor,LocalDateTime dueDate) {
        ProjectTopic projectTopic=projectTopicRepository.findById(id).orElseThrow(InvalidTopicIdEception::new);
        projectTopic.setTitle(title);
        projectTopic.setDescription(description);
        projectTopic.setCounter(counter);

        List<Student> studentList=new ArrayList<>();
        for (String studentId1 : students) {
            String studentId = studentId1.replaceAll("[\\[\\]]", "");
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new NoSuchElementException("Student with ID " + studentId + " not found"));
            studentList.add(student);
        }
        projectTopic.setStudents(studentList);

        projectTopic.setProfessor(professor);
        projectTopic.setDueDate(dueDate);
            return projectTopicRepository.save(projectTopic);
    }

    @Override
    public void deleteTopic(Long id) {
        projectTopicRepository.deleteById(id);
    }

    @Override
    public void choseTopic(Long id,Student student) {
        ProjectTopic projectTopic=projectTopicRepository.findById(id).orElseThrow(InvalidTopicIdEception::new);

        projectTopic.setCounter(projectTopic.getCounter()+1);
        List<Student> studentList=projectTopic.getStudents();
        studentList.add(student);
        projectTopic.setStudents(studentList);

        projectTopicRepository.save(projectTopic);
    }

    @Override
    public void cancelTopic(Long id, Student student) {
        ProjectTopic projectTopic=projectTopicRepository.findById(id).orElseThrow(InvalidTopicIdEception::new);

        projectTopic.setCounter(projectTopic.getCounter()-1);
        List<Student> studentList=projectTopic.getStudents();
        studentList.remove(student);
        projectTopic.setStudents(studentList);

        projectTopicRepository.save(projectTopic);
    }

    @Override
    public void closeTopic(Long id) {
        ProjectTopic projectTopic=projectTopicRepository.findById(id).orElseThrow(InvalidTopicIdEception::new);
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime pastTime = currentTime.minusMinutes(1);
        projectTopic.setDueDate(pastTime);
        projectTopicRepository.save(projectTopic);
    }
}
