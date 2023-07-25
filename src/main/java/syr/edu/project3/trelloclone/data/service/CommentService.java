package syr.edu.project3.trelloclone.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syr.edu.project3.trelloclone.data.models.*;
import syr.edu.project3.trelloclone.data.repository.CommentRepository;
import syr.edu.project3.trelloclone.data.repository.HistoryRepository;
import syr.edu.project3.trelloclone.data.repository.TaskRepository;
import syr.edu.project3.trelloclone.data.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

//CommentService for implementing functions for comment based api calls from CommentController
@Service
public class CommentService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoryRepository historyRepository;


    public  List<Comment> getAllCommentsByTaskId(long taskId) throws Exception {
        if (!taskRepository.existsById(taskId)) {
            throw new Exception("Not found Task with id = " + taskId);
        }
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new Exception("Not found"));

        List<Comment> comments = commentRepository.findByTask(task);
        return comments;
    }

    public Comment createComment(long taskId, CommentSchema commentRequest) throws Exception {
        User user = userRepository.findById(commentRequest.userId).
                orElseThrow(() -> new Exception("Not found User with id = " + commentRequest.userId));
        Comment c = new Comment();
        c.setComment(commentRequest.comment);
        c.setUser(user);
        Comment comment = taskRepository.findById(taskId).map(task -> {
            c.setTask(task);
            return commentRepository.save(c);
        }).orElseThrow(() -> new Exception("Not found Task with id = " + taskId));
        Task _task = taskRepository.findById(taskId)
                .orElseThrow(() -> new Exception("Not found Task with id = " + taskId));

        //Creating history object to save the details of comment added by user on a task in history table

        History history = new History();
        history.setTask(_task);
        history.setChange_id(c.getId());
        history.setChange_id_type("Comment id");
        history.setChange_description("Comment added by " + user.getName() + " : " + c.getComment());
        history.setUpdated_on(LocalDateTime.now());
        historyRepository.save(history);

        return comment;
    }

    public List<Comment> getAllCommentsByUserId(long userId) throws Exception {
        if (!userRepository.existsById(userId)) {
            throw new Exception("Not found User with id = " + userId);
        }
        User user = userRepository.findById(userId).orElseThrow(()->
                new Exception("Not found"));

        List<Comment> comments = commentRepository.findByUser(user);
        return comments;
    }

    public void deleteComment(long id) throws Exception {
        Comment _comment = commentRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found comment with id = " + id));

        Task _task = taskRepository.findById(_comment.getTask().getTaskId())
                .orElseThrow(() -> new Exception("Not found Task for this comment"));

        //Creating history object to save the details of currently removed comment on a task in history table

        History history = new History();
        history.setTask(_task);
        history.setChange_id(_comment.getId());
        history.setChange_id_type("Comment id");
        history.setChange_description("Comment deleted : " + _comment.getComment() + "by user : " + _comment.getUser().getName());
        history.setUpdated_on(LocalDateTime.now());
        historyRepository.save(history);
        commentRepository.deleteById(id);
    }

    public Comment getCommentsByCommentId(Long id) throws Exception {
        return commentRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found Comment with id = " + id));
    }

    public void deleteByTask(Long taskId) throws Exception {
        if (!taskRepository.existsById(taskId)) {
            throw new Exception("Not found Task with id = " + taskId);
        }
        Task task = taskRepository.findById(taskId).
                orElseThrow(() -> new Exception("Not found Task with id = " + taskId));
        commentRepository.deleteByTask(task);
    }

    public void deleteByUser(Long userId) throws Exception {
        if (!userRepository.existsById(userId)) {
            throw new Exception("Not found User with id = " + userId);
        }
        User user = userRepository.findById(userId).
                orElseThrow(() -> new Exception("Not found Task with id = " + userId));

        commentRepository.deleteByUser(user);
    }
}
