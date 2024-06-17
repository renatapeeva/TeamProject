package mk.ukim.finki.wp.teamprojects.repository;


import mk.ukim.finki.wp.teamprojects.model.StudyProgram;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyProgramRepository extends JpaSpecificationRepository<StudyProgram, String> {
}
