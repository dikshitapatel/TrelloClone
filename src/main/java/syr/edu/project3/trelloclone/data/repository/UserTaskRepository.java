package syr.edu.project3.trelloclone.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import syr.edu.project3.trelloclone.data.models.Comment;
import syr.edu.project3.trelloclone.data.models.Task;
import syr.edu.project3.trelloclone.data.models.User;
import syr.edu.project3.trelloclone.data.models.UserTask;

import javax.transaction.Transactional;
import java.util.List;

public interface UserTaskRepository extends JpaRepository<UserTask, Long> {

    //Returns  taskId and userId with the specified task details
    List<Comment> findByTask(Task task);

    //Returns  taskId and userId with the specified user details
    List<Comment> findByUser(User user);


    //Returns user name, user id, task description and taskid
    @Query(value = "SELECT TrelloCloneDb.user.name,TrelloCloneDb.usertask.user_id,TrelloCloneDb.task.description," +
            "TrelloCloneDb.usertask.task_id FROM TrelloCloneDb.usertask \n" +
            "INNER JOIN TrelloCloneDb.user On \n" +
            "TrelloCloneDb.user.user_id = TrelloCloneDb.usertask.user_id \n" +
            "INNER JOIN TrelloCloneDb.task ON\n" +
            " TrelloCloneDb.task.task_id = TrelloCloneDb.usertask.task_id;",
            nativeQuery = true)
    List<Object> getUserTaskDetailsWithDescription();

    //Returns userid,taskid and comments on the task
    @Query(value = "SELECT TrelloCloneDb.usertask.user_id,\n" +
            "            TrelloCloneDb.usertask.task_id,TrelloCloneDb.comment.comment FROM TrelloCloneDb.usertask\n" +
            "            INNER JOIN TrelloCloneDb.comment On \n" +
            "\t\tTrelloCloneDb.usertask.user_id = TrelloCloneDb.comment.user_id \n" +
            "            INNER JOIN TrelloCloneDb.task ON\n" +
            "\t\tTrelloCloneDb.usertask.task_id = TrelloCloneDb.comment.task_id;",nativeQuery = true)
    List<Object> getCommentsWithUserIdAndTaskId();

    //Deletes taskId and userId with the specified task details
    @Transactional
    UserTask deleteByTask(Task task);

    //Deletes taskId and userId with the specified user details
    @Transactional
    void deleteByUser(User user);



}
