package syr.edu.project3.trelloclone.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syr.edu.project3.trelloclone.data.models.User;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    //Returns all the users with the given name
    List<User> findByName(String userName);
}
