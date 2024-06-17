package mk.ukim.finki.wp.teamprojects.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "project_topic")
public class ProjectTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description_text",length = 1000)
    private String description;
    @Column(name = "count")
    private Integer counter;
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    @OneToMany
    List<Student> students;
    @ManyToOne
    Professor professor;
    @Column(name = "tasks")
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)    List<TopicTask> tasks;
    public ProjectTopic() {
    }

    public ProjectTopic(String title, String description, Integer counter, List<Student> students, Professor professor,LocalDateTime dueDate) {
        this.title = title;
        this.description = description;
        this.counter = counter;
        this.students = students;
        this.professor = professor;
        this.dueDate = dueDate;
        this.tasks=new ArrayList<>();
    }
}
