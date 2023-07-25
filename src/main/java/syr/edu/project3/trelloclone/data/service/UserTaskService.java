package syr.edu.project3.trelloclone.data.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syr.edu.project3.trelloclone.data.models.History;
import syr.edu.project3.trelloclone.data.models.Task;
import syr.edu.project3.trelloclone.data.models.User;
import syr.edu.project3.trelloclone.data.models.UserTask;
import syr.edu.project3.trelloclone.data.repository.HistoryRepository;
import syr.edu.project3.trelloclone.data.repository.TaskRepository;
import syr.edu.project3.trelloclone.data.repository.UserRepository;
import syr.edu.project3.trelloclone.data.repository.UserTaskRepository;

//UserTaskService for implementing functions for usertask based api calls from UserTaskController

@Service
public class UserTaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserTaskRepository userTaskRepository;

    @Autowired
    HistoryRepository historyRepository;

    public UserTask assignUserToTask(long userId, long taskId) throws Exception {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new Exception("Not found Task with id = " + userId));

        Task task = taskRepository.findById(taskId).
                orElseThrow(() -> new Exception("Not found Task with id = " + taskId));

        UserTask u = new UserTask();

        u.setTask(task);
        u.setUser(user);

        //Creating history object to save the details of currently assigned user to a task in history table
        History history = new History();
        history.setTask(task);
        history.setChange_id(user.getUserId());
        history.setChange_id_type("User id");
        history.setChange_description("User : " + user.getName() + " , assigned to the task");
        history.setUpdated_on(task.getModified_on());
        historyRepository.save(history);

        return userTaskRepository.save(u);
    }

    public UserTask deleteUserTask(long userId,long taskId) throws Exception {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new Exception("Not found Task with id = " + userId));

        Task task = taskRepository.findById(taskId).
                orElseThrow(() -> new Exception("Not found Task with id = " + taskId));

        //Creating history object to save the details of currently unassigned suer from a task in history table
        History history = new History();
        history.setTask(task);
        history.setChange_id(user.getUserId());
        history.setChange_id_type("User id");
        history.setChange_description("User " + userId + " unassigned from the task");
        history.setUpdated_on(task.getModified_on());
        historyRepository.save(history);
        return userTaskRepository.deleteByTask(task);
    }

}

