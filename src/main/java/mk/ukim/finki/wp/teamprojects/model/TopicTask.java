package mk.ukim.finki.wp.teamprojects.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "topic_tasks")
public class TopicTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @ManyToOne
    private ProjectTopic topic;

    private Double percentComplete;
    private Boolean completed;

    public TopicTask() {
    }
    public TopicTask(String title, String description, ProjectTopic topic) {
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.percentComplete = 0.0;
        this.completed = false;
    }
}
