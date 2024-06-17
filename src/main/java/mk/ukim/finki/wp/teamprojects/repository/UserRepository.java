package mk.ukim.finki.wp.teamprojects.repository;

import mk.ukim.finki.wp.teamprojects.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaSpecificationRepository<User, String> {
}
