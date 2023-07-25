The project name trellocloneDikshita is a TRELLO CLONE java application which allows data driven task staffing
and improvement over sticky notes on whiteboard.

**Additional Features**
  * Created a history table to save the history of tasks with respect to creation, state updation, description update,
  * comment addition,deletion also user assignment and removal on task*
* We have the following fields : id,change_id,change_description,task_id,change_id_type,updated_on
* respectively for the entity.
* Defined getter and setter methods.
 ______________________________________________________________________________________________________________
 
**HistoryController**
* The HistoryController includes api history/{taskId} to get the history of task specified in the taskId* 

**`I have the following Services for the project**
* TaskService,UserService,CommentService,UserTaskService,HistoryService for providing implementation to 
* api calls made from controller*


**Defined Data Model for JPA One to Many mapping**
**I have the following models for the project:**

1. Task 
* Task table will save the tasks with its id, state, description and time when it was created
* Defining an entity named task in the model.
* We have the following fields : taskId, description, state, toc (time of creation) respectively for the entity.
* Defined getter and setter methods for the fields.

2. User
* User table will save user records with id, name
* Defining an entity named user in the model.
* We have the following fields : userId, name respectively for the entity.
* Defined getter and setter methods for the fields.

3. UserTask
* UserTask will save taskId and the assigned userId in the table
* Defining an entity named usertask in the model.
* We have the following fields : id, taskId, userId respectively for the entity.
* Defined getter and setter methods for the fields.

4. Comment
* Comment table will save user comments for the task
* Defining an entity named comment in the model.
* We have the following fields : id, userId,taskId respectively for the entity.
* The userId and taskId are foreign keys in the table with manyToOne relation
* Defined getter and setter methods for the fields.

5. CommentSchema
* Class for getting userId and comment to create a new comment, used in createComment api

**Create Repository Interfaces for One To Many mapping**
**I have the following repository for the project:**
* TaskRepository, CommentRepository, UserRepository, UserTaskRepository are interfaces that extends JpaRepository 
* for CRUD methods and custom finder methods. It will be autowired in TaskController, CommentController, UserController
* UserTaskController respectively.

**Create Spring Rest APIs Controller**
* I have the following controllers in the project for
* creating, retrieving, updating, deleting and finding Tasks,Users and Comments

1. TaskController
* The TaskController includes api's for the following features:
* Get all tasks in the task table
* Get the task details with the specified taskId in the task table
* Insert task with the specified task details in the task table
* Update task with the given task details for the specified taskId in the task table
* Delete task with the specified taskId in the task table
* Delete all tasks in the task table

2. UserController
* The UserController includes api's for the following features:
* Get all users from the user table
* Get user details with the specified userId from user table
* Insert user with the specified user details in the user table
* Update user details for the specified userId  in the user table
* Delete user with the specified userId from the user table
* Delete all users from the user table

3. UserTaskController
* The UserTaskController includes api's for the following features:
* Insert userId with the corresponding taskId in the userTask table
* Return user name, user id, task description and taskid
* Returns userid,taskid and comments on the task
* Deletes taskId and userId with the specified task details
* Deletes taskId and userId with the specified user details

4. CommentController
* The CommentController includes api's for the following features:
* Get all task comments for the given taskId from the comment table
* Get the comment with the given commentId from the comment table
* Get all user comments for the given userId from the comment table
* Insert comment for the given taskId in the comment table
* Delete the comment with the given commentId from the comments table
* Delete comments for the task with the given taskId from the comments table
* Delete comments for the user with the given userId from the comments table
