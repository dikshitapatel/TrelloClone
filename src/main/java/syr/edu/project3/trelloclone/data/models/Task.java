package syr.edu.project3.trelloclone.data.models;
import javax.persistence.*;
import java.time.LocalDateTime;

//Task table to store the task details
@Entity
@Table(name = "task")
public class Task {

    //Primary key for table task
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "taskId")
    private long taskId;

    //state column  to save task state: TODO, DOING, DONE
    @Column(name = "state")
    private String state;

    //description column to save the explanation for tasks
    @Column(name = "description")
    private String description;

    //toc column to save the time of creation of the task
    @Column(name = "toc")
    private LocalDateTime toc;

    //Modified On column to save the time of last modified state
    @Column(name = "modified_on")
    private LocalDateTime modified_on;

    //Default constructor
    public Task() {
    }

    //Task constructor to save the state and decription of the task
    public Task(String state, String description) {
        this.state = state;
        this.description = description;
        this.toc = LocalDateTime.now();
        this.modified_on = LocalDateTime.now();
    }

    //Getter and Setters for the field of task entity

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long id) {
        this.taskId = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getModified_on() {
        return modified_on;
    }

    public void setModified_on(LocalDateTime modified_on) {
        this.modified_on = modified_on;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getToc() {
        return toc;
    }

    public void setToc(LocalDateTime toc) {
        this.toc = toc;
    }
}