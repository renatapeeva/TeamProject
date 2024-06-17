//package mk.ukim.finki.wp.teamprojects.web;
//
//import jakarta.servlet.http.HttpSession;
//import mk.ukim.finki.wp.teamprojects.model.Professor;
//import mk.ukim.finki.wp.teamprojects.service.ProfessorService;
//import mk.ukim.finki.wp.teamprojects.service.ProjectTopicService;
//import mk.ukim.finki.wp.teamprojects.service.StudentService;
//import mk.ukim.finki.wp.teamprojects.service.TopicTaskService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class TaskController {
//    private final ProjectTopicService projectTopicService;
//    private final ProfessorService professorService;
//    private final StudentService studentService;
//    private final TopicTaskService topicTaskService;
//
//    public TaskController(ProjectTopicService projectTopicService, ProfessorService professorService, StudentService studentService, TopicTaskService topicTaskService) {
//        this.projectTopicService = projectTopicService;
//        this.professorService = professorService;
//        this.studentService = studentService;
//        this.topicTaskService = topicTaskService;
//    }
//    @GetMapping("/topics/add")
//    public String showAdd(HttpSession session, Model model) {
//        Professor professor=(Professor)session.getAttribute("professor");
//        model.addAttribute("professor", professor);
//        return "form.html";
//    }
//}
