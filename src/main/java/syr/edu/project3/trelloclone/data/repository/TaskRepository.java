package syr.edu.project3.trelloclone.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;
import syr.edu.project3.trelloclone.data.models.Task;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public interface TaskRepository extends JpaRepository<Task, Long> {

    //Returns all task in the specified state
    Task findTaskByTaskId(Long id);
}
