package mk.ukim.finki.wp.teamprojects.service.impl;

import mk.ukim.finki.wp.teamprojects.model.ProjectTopic;
import mk.ukim.finki.wp.teamprojects.model.TopicTask;
import mk.ukim.finki.wp.teamprojects.repository.ProjectTopicRepository;
import mk.ukim.finki.wp.teamprojects.repository.TopicTaskRepository;
import mk.ukim.finki.wp.teamprojects.service.TopicTaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicTaskSerivceImpl implements TopicTaskService {
    private final TopicTaskRepository topicTaskRepository;
    private final ProjectTopicRepository projectTopicRepository;

    public TopicTaskSerivceImpl(TopicTaskRepository topicTaskRepository, ProjectTopicRepository projectTopicRepository) {
        this.topicTaskRepository = topicTaskRepository;
        this.projectTopicRepository = projectTopicRepository;
    }

    @Override
    public Optional<TopicTask> findById(Long id) {
        return topicTaskRepository.findById(id);
    }

    @Override
    public List<TopicTask> findAllByProjectTopic(Long topicId) {
        ProjectTopic topic=projectTopicRepository.findById(topicId).get();
        return topicTaskRepository.findAllByTopic(topic);
    }

    @Override
    public void deleteTask(Long id) {
        topicTaskRepository.deleteById(id);
    }

    @Override
    public TopicTask addTask(String title, String description, Long topicId) {
        ProjectTopic topic=projectTopicRepository.findById(topicId).get();
        TopicTask topicTask=new TopicTask(title,description,topic);

        return topicTaskRepository.save(topicTask);
    }

    @Override
    public TopicTask updateTask(Long id, String title, String description, Double percentComplete,Boolean status) {
        TopicTask topicTask=topicTaskRepository.findById(id).get();
        topicTask.setTitle(title);
        topicTask.setDescription(description);
        topicTask.setPercentComplete(percentComplete);
        topicTask.setCompleted(status);
        return topicTaskRepository.save(topicTask);
    }

    @Override
    public TopicTask completeTask(Long id) {
        TopicTask topicTask=topicTaskRepository.findById(id).get();
        topicTask.setCompleted(Boolean.TRUE);
        return topicTaskRepository.save(topicTask);

    }
}
