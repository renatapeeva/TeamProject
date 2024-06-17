package mk.ukim.finki.wp.teamprojects.repository;

import mk.ukim.finki.wp.teamprojects.model.ProjectTopic;
import mk.ukim.finki.wp.teamprojects.model.TopicTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicTaskRepository extends JpaSpecificationRepository<TopicTask,Long> {
    List<TopicTask> findAllByTopic(ProjectTopic topic);
}
