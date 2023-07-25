package syr.edu.project3.trelloclone.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syr.edu.project3.trelloclone.data.models.History;
import syr.edu.project3.trelloclone.data.models.Task;
import syr.edu.project3.trelloclone.data.repository.HistoryRepository;
import syr.edu.project3.trelloclone.data.repository.TaskRepository;
import syr.edu.project3.trelloclone.data.repository.UserRepository;
import syr.edu.project3.trelloclone.data.repository.UserTaskRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//TaskService for implementing functions for task based api calls from TaskController
@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserTaskRepository userTaskRepository;

    @Autowired
    HistoryRepository historyRepository;


    public List<Task> getAllTasks(){
        List<Task> tasks = new ArrayList<Task>();
        taskRepository.findAll().forEach(tasks::add);
        return  tasks;
    }

    public Task createTask(Task task){
        Task _task = taskRepository.save(new Task(task.getState(), task.getDescription()));

        //Creating history object to save the details of currently added task in history table
        History history = new History();
        history.setTask(_task);
        history.setChange_id(_task.getTaskId());
        history.setChange_id_type("Task id");
        history.setChange_description("Task Inserted");
        history.setUpdated_on(_task.getModified_on());
        historyRepository.save(history);
        return  _task;
    }

    public Task updateTaskDescription(Task task, long id) throws Exception {
        Task _task = taskRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found Task with id = " + id));

        //Creating history object to save the details of currently changed task description in history table
        History history = new History();
        history.setTask(_task);
        history.setChange_id(_task.getTaskId());
        history.setChange_id_type("Task id");
        _task.setDescription(task.getDescription());
        history.setChange_description("Task description updated to : "+ _task.getDescription());
        _task.setModified_on(LocalDateTime.now());
        history.setUpdated_on(_task.getModified_on());
        historyRepository.save(history);
        taskRepository.save(_task);
        return _task;
    }

    public Task updateTaskState(Task task, Long id) throws Exception {
        Task _task = taskRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found Task with id = " + id));

        //Creating history object to save the details of currently changed task state in history table
        History history = new History();
        history.setTask(_task);
        history.setChange_id(_task.getTaskId());
        history.setChange_id_type("Task id");
        history.setChange_description("Task state updated from : " + _task.getState() +" to : "
                + task.getState());
        _task.setState(task.getState());
        _task.setModified_on(LocalDateTime.now());
        history.setUpdated_on(_task.getModified_on());
        historyRepository.save(history);
        return _task;
    }

    public Task findById(long id) throws Exception {
        return taskRepository.findTaskByTaskId(id);
    }

    public void deleteTask(long id) throws Exception {
         taskRepository.deleteById(id);
    }
}
