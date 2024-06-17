package mk.ukim.finki.wp.teamprojects.web;

import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.teamprojects.model.Professor;
import mk.ukim.finki.wp.teamprojects.model.Student;
import mk.ukim.finki.wp.teamprojects.service.ProfessorService;
import mk.ukim.finki.wp.teamprojects.service.ProjectTopicService;
import mk.ukim.finki.wp.teamprojects.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {
    private final ProjectTopicService projectTopicService;
    private final ProfessorService professorService;
    private final StudentService studentService;

    public LoginController(ProjectTopicService projectTopicService, ProfessorService professorService, StudentService studentService) {
        this.projectTopicService = projectTopicService;
        this.professorService = professorService;
        this.studentService = studentService;
    }
    @GetMapping("/login")
    public String showlogin(Model model,HttpSession session){
        session.setAttribute("professor", null);
        session.setAttribute("student",null);
        return "login";
    }
    @PostMapping ("/login")
    public String login(Model model, @RequestParam String index, HttpSession session){
        Optional<Student> student1 = studentService.getStudentById(index);
        if (student1.isPresent()){
            session.setAttribute("professor", null);

            Student student = student1.get();
            session.setAttribute("student",student);
        }else {
            session.setAttribute("student",null);

            Professor professor=professorService.getProfessorById(index);
            session.setAttribute("professor", professor);
        }
        return "redirect:/topics";
    }
}
