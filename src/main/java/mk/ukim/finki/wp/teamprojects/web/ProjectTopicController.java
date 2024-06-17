package mk.ukim.finki.wp.teamprojects.web;

import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.teamprojects.model.Professor;
import mk.ukim.finki.wp.teamprojects.model.ProjectTopic;
import mk.ukim.finki.wp.teamprojects.model.Student;
import mk.ukim.finki.wp.teamprojects.model.TopicTask;
import mk.ukim.finki.wp.teamprojects.service.ProfessorService;
import mk.ukim.finki.wp.teamprojects.service.ProjectTopicService;
import mk.ukim.finki.wp.teamprojects.service.StudentService;
import mk.ukim.finki.wp.teamprojects.service.TopicTaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProjectTopicController {
    private final ProjectTopicService projectTopicService;
    private final ProfessorService professorService;
    private final StudentService studentService;
    private final TopicTaskService topicTaskService;

    public ProjectTopicController(ProjectTopicService projectTopicService, ProfessorService professorService, StudentService studentService, TopicTaskService topicTaskService) {
        this.projectTopicService = projectTopicService;
        this.professorService = professorService;
        this.studentService = studentService;
        this.topicTaskService = topicTaskService;
    }

    @GetMapping("/")
    public String homePage() {
        return "homePage.html";
    }

    @GetMapping( "/topics")
    public String showTopics(Model model, HttpSession session) {
        List<ProjectTopic> projectTopicList=projectTopicService.getAllTopics();
        model.addAttribute("topics", projectTopicList);

        model.addAttribute("student", session.getAttribute("student"));
        model.addAttribute("professor", session.getAttribute("professor"));

        System.out.println(session.getAttribute("student"));
        return "list.html";
    }

    @GetMapping( "/topics/{id}/details")
    public String showDetails(@PathVariable Long id, Model model,HttpSession session) {
        ProjectTopic topic=this.projectTopicService.getTopicById(id);
        model.addAttribute("topic", topic);
        model.addAttribute("id",id);

        Student student = (Student) session.getAttribute("student");
        model.addAttribute("student", student);

        //za proverka dali toj e prijaveniot student, za da mozhe da gi gleda taskovite
        boolean isEnrolled = topic.getStudents().contains(student);
        model.addAttribute("isEnrolled", isEnrolled);

        model.addAttribute("professor", session.getAttribute("professor"));
        return "details.html";
    }
    @GetMapping("/topics/add")
    public String showAdd(HttpSession session, Model model) {
        Professor professor=(Professor)session.getAttribute("professor");
        model.addAttribute("professor", professor);
        return "form.html";
    }
    @GetMapping("/topics/addTask/{id}")
    public String showAddTask(@PathVariable Long id,HttpSession session, Model model) {
        ProjectTopic topic=projectTopicService.getTopicById(id);
        model.addAttribute("topic", topic);
        return "addTask.html";
    }
    @PostMapping("/tasks")
    public String createTask(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam Long topicId) {

        topicTaskService.addTask(title,description,topicId);
        return "redirect:/topics/" + topicId + "/details";
    }
    @GetMapping("/topics/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        ProjectTopic topic=this.projectTopicService.getTopicById(id);
        model.addAttribute("topic", topic);
        model.addAttribute("id",id);
        List<Student> students=topic.getStudents();
        model.addAttribute("students", students);
        return "form.html";
    }
    @PostMapping("/topics")
    public String create(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam Integer counter,
            @RequestParam String professorId,
            @RequestParam LocalDateTime dueDate) {

        Professor professor=professorService.getProfessorById(professorId);
        projectTopicService.addTopic(title, description, counter, professor,dueDate);
        return "redirect:/topics";
    }
    @PostMapping("/topics/{id}/cancel")
    public String cancel(@PathVariable Long id,HttpSession session,Model model) {
        Student student=(Student)session.getAttribute("student");

        projectTopicService.cancelTopic(id,student);

        return "redirect:/topics";
    }
    @PostMapping("/topics/{id}/enroll")
    public String enroll(@PathVariable Long id,HttpSession session,Model model) {
        Student student=(Student)session.getAttribute("student");

        projectTopicService.choseTopic(id,student);

        return "redirect:/topics";
    }
    @GetMapping("/topics/completeTask/{id}")
    public String completeTask(@PathVariable Long id) {
        Optional<TopicTask> task=topicTaskService.findById(id);
        topicTaskService.completeTask(id);
        return "redirect:/topics/" + task.get().getTopic().getId() + "/details";
    }

    @PostMapping("/topics/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String title,
                         @RequestParam String description,
                         @RequestParam Integer counter,
                         @RequestParam (required = false) List<String> studentIds,
                         @RequestParam String professorId  ,
                         @RequestParam LocalDateTime dueDate) {
        Professor professor=professorService.getProfessorById(professorId);
        if (studentIds==null || studentIds.isEmpty()) {
            studentIds=new ArrayList<>();
        }
        projectTopicService.updateTopic(id, title, description, counter, studentIds, professor,dueDate);
        return "redirect:/topics";
    }
    @PostMapping("/topics/{id}/delete")
    public String delete(@PathVariable Long id) {
        projectTopicService.deleteTopic(id);
        return "redirect:/topics";
    }
    @PostMapping("/topics/{id}/close")
    public String close(@PathVariable Long id) {
        //proveri dali ima max 5 studenti ako ima poveke ne dozvoluvaj da se zatvori temata
        //dodadi prifateni studenti atribut na topic i prof da moze da prifaka studenti i studentite da prifakaat sorabotka

        projectTopicService.closeTopic(id);
        return "redirect:/topics";
    }
}
