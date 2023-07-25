package syr.edu.project3.trelloclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import syr.edu.project3.trelloclone.data.models.*;
import syr.edu.project3.trelloclone.data.repository.UserTaskRepository;
import syr.edu.project3.trelloclone.data.service.UserTaskService;

import java.util.List;

//UserTaskController for executing user based api calls
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class UserTaskController {

    @Autowired
    UserTaskService userTaskService;

    @Autowired
    UserTaskRepository userTaskRepository;

    //Insert userId with the corresponding taskId in the userTask table
    @PostMapping("/usertask/{userId}/{taskId}")
    public ResponseEntity<UserTask> assignUserTask(@PathVariable(value = "userId") Long userId,@PathVariable(value="taskId") Long taskId) throws Exception {

        UserTask userTask = userTaskService.assignUserToTask(userId,taskId);
        if(userTask!=null) {
            return new ResponseEntity<>(userTask, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Unassign userId with the corresponding taskId in the userTask table
    @DeleteMapping("/usertask/{userId}/{taskId}")
    public ResponseEntity<HttpStatus> deleteUserTask(@PathVariable(value = "userId") Long userId,@PathVariable(value="taskId") Long taskId) throws Exception {

        UserTask userTask = userTaskService.deleteUserTask(userId,taskId);
        if(userTask!=null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Returns user name, user id, task description and taskid
    @GetMapping("/getUserTaskDetailsWithDescription")
    public ResponseEntity<List<Object>> getUserTaskDetailsWithDescription(){

        return new ResponseEntity<>(userTaskRepository.getUserTaskDetailsWithDescription(), HttpStatus.OK);
    }

    //Returns userid,taskid and comments on the task
    @GetMapping("/getCommentsWithUserIdAndTaskId")
    public  ResponseEntity<List<Object>> getCommentsWithUserIdAndTaskId(){
        return new ResponseEntity<>(userTaskRepository.getCommentsWithUserIdAndTaskId(),HttpStatus.OK);
    }


}
