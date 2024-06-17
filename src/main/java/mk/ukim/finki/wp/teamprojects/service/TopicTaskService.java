package mk.ukim.finki.wp.teamprojects.service;

import mk.ukim.finki.wp.teamprojects.model.TopicTask;

import java.util.List;
import java.util.Optional;

public interface TopicTaskService {
    Optional<TopicTask> findById(Long id);
    List<TopicTask> findAllByProjectTopic(Long topicId);
    void deleteTask(Long id);
    TopicTask addTask(String title, String description, Long topicId);
    TopicTask updateTask(Long id, String title, String description, Double percentComplete,Boolean status);
    TopicTask completeTask(Long id);
}
