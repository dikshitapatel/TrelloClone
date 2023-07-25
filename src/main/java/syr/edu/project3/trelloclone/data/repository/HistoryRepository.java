package syr.edu.project3.trelloclone.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import syr.edu.project3.trelloclone.data.models.*;

import javax.transaction.Transactional;
import java.util.List;


public interface HistoryRepository extends JpaRepository<History,Long> {

    //Returns the history of a task
    List<History> findByTask(Task task);

    @Transactional
    void deleteByTask(Task task);
}