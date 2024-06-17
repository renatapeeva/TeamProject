package mk.ukim.finki.wp.teamprojects.service;


import mk.ukim.finki.wp.teamprojects.model.Professor;
import mk.ukim.finki.wp.teamprojects.model.ProjectTopic;
import mk.ukim.finki.wp.teamprojects.model.Student;

import java.time.LocalDateTime;
import java.util.List;

public interface ProjectTopicService {
    List<ProjectTopic> getAllTopics();
    ProjectTopic getTopicById(Long id);
    ProjectTopic getTopicByName(String name);
    ProjectTopic addTopic(String title, String description, Integer counter, Professor professor, LocalDateTime dueDate);
    ProjectTopic updateTopic(Long id,String title, String description,Integer counter,List<String> students, Professor professor,LocalDateTime dueDate);
    void deleteTopic(Long id);
    void choseTopic(Long topicId, Student student);
    void cancelTopic(Long id, Student student);
    void closeTopic(Long id);
}
