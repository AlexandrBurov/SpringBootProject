package springBootProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springBootProject.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
