package mk.ukim.finki.wp.teamprojects.repository;


import mk.ukim.finki.wp.teamprojects.model.Student;

import java.util.List;

public interface StudentRepository extends JpaSpecificationRepository<Student, String> {
    List<Student> findAllByIndexIn(List<String> indexes);
}
