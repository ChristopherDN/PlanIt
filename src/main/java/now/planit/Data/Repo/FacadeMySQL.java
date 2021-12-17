package now.planit.Data.Repo;

import now.planit.Domain.Models.Project;
import now.planit.Domain.Models.Subtask;
import now.planit.Domain.Models.Task;
import now.planit.Domain.Models.User;

import java.util.ArrayList;

/**
 * @author Christopher
 */

public class FacadeMySQL {
  InterfaceProjectRepo projectRepo = new ProjectRepo(new MapperDB());
  UsersRepo usersRepo = new UsersRepo(new MapperDB());
  TaskRepo taskRepo = new TaskRepo(new MapperDB());
  SubtaskRepo subtaskRepo = new SubtaskRepo(new MapperDB());

  //Constructors
  //Dependency injection constructor.
  public FacadeMySQL(ProjectRepo projectRepo) {
    this.projectRepo = projectRepo;
  }

  //Dependency injection constructor.
  public FacadeMySQL(UsersRepo usersRepo) {
    this.usersRepo = usersRepo;
  }

  //Dependency injection constructor.
  public FacadeMySQL(TaskRepo taskRepo) {
    this.taskRepo = taskRepo;
  }

  //Dependency injection constructor.
  public FacadeMySQL(SubtaskRepo subtaskRepo) {
    this.subtaskRepo = subtaskRepo;
  }


  //User Repository
  public int registerUser(String name, String email, String password) {
    return usersRepo.registerUser(name, email, password);
  }

  public User validateLogin(String email, String password) {
    User user = usersRepo.validateLogin(email, password);
    return user;
  }

  public User getUserProjects(User user) {
    user.getProjects().clear();

    //Here we set Users projects.
    user.setProjects(projectRepo.loadProjects(getUserId(user)));
    String taskname;

    //Here we load tasks into users projects.
    for (int i = 0; i < user.getProjects().size(); i++) {
      user.getProjects().get(i).getTasks().addAll(getTasks(user.getProjects().get(i).getName(), user));

      //Here we load subtasks into Users Tasks connected to individual projects.
      for (int j = 0; j < user.getProjects().get(i).getTasks().size(); j++) {
        taskname = user.getProjects().get(i).getTasks().get(j).getName();
        user.getProjects().get(i).getTasks().get(j).getSubtasks().addAll(getSubtasks(taskname, user));
      }
    }
    return user;
  }

  public int getUserId(User user) {
    return usersRepo.getUserId(user);
  }

  public void updateName(String name, User user) {
    usersRepo.updateName(name, getUserId(user));
  }

  public void updateEmail(String email, User user) {
    usersRepo.updateEmail(email, getUserId(user));
  }

  public void updatePassword(String password, User user) {
    usersRepo.updatePassword(password, getUserId(user));
  }

  //For JUnit test.
  public void deleteUser(String email, String password) {
    usersRepo.deleteUser(email, password);
  }


  //Project Repository
  public ArrayList<Project> getProjects(User user) {
    return projectRepo.loadProjects(usersRepo.getUserId(user));
  }

  public void createProject(String name1, String start, String finish, int budget, User user) {
    projectRepo.createProject(name1, start, finish, budget, getUserId(user));
  }

  public int getProjectId(String projectName, int userId) {
    return projectRepo.getProjectId(projectName, userId);
  }

  public void deleteProject(String projectName, User user) {
    projectRepo.deleteProject(getProjectId(projectName, getUserId(user)), getUserId(user));
  }

  public String getProjectName(String taskName, int userId) {
    return projectRepo.getProjectName(taskName, userId);
  }


  //Task Repository
  public ArrayList<Task> getTasks(String projectName, User user) {
    return taskRepo.loadTasks(getProjectId(projectName, getUserId(user)));
  }

  public int getTaskId(String taskName, int projectId) {
    return taskRepo.getTaskId(taskName, projectId);
  }

  public void createTask(String taskName, String startDate, String finishDate, String projectName, User user) {
    taskRepo.createTask(taskName, startDate, finishDate, getProjectId(projectName, getUserId(user)));
  }

  public void deleteTask(String projectName, String taskName, User user) {
    int taskId = getTaskId(taskName, getProjectId(projectName, getUserId(user)));
    int projectID = getProjectId(projectName, getUserId(user));

    //Subtract hours and cost from Task and Project.
    projectRepo.subtractCost(taskRepo.getCost(taskId, projectID), projectID);
    projectRepo.subtractHours(taskRepo.getHours(taskId, projectID), projectID);

    //Delete Task
    taskRepo.deleteTask(taskId, projectID);
  }

  private void calculateHours(int hours, String taskName, int projectId) {
    taskRepo.addHours(hours, taskName, projectId);
    projectRepo.addActualHours(hours, projectId);
  }

  public void calculateCost(int cost, String taskName, int projectId) {
    taskRepo.addActualCost(cost, taskName, projectId);
    projectRepo.addActualCost(cost, projectId);
  }

  public int getProjectId(String taskName) {
    return taskRepo.getProjectID(taskName);
  }


  //Subtask Repository
  public ArrayList<Subtask> getSubtasks(String taskName, User user) {
    return subtaskRepo.loadSubtasks(getTaskId(taskName, getProjectId(getProjectName(taskName, getUserId(user)), getUserId(user))));
  }

  public void createSubtask(String subtaskName, int hours, int cost, String taskName) {
    subtaskRepo.createSubtask(subtaskName, hours, cost, getTaskId(taskName, getProjectId(taskName)));
    calculateHours(hours, taskName, getProjectId(taskName));
    calculateCost(cost, taskName, getProjectId(taskName));
  }

  public int getSubtaskId(String subtaskName, int taskId) {
    return subtaskRepo.getSubtaskId(subtaskName, taskId);
  }

  public void deleteSubtask(String taskName, String subtaskName, User user) {
    //Load up ID´s that we need
    int subtaskID = getSubtaskId(subtaskName, getTaskId(taskName, getProjectId(getProjectName(taskName, getUserId(user)), getUserId(user))));
    int taskId = getTaskId(taskName, getProjectId(getProjectName(taskName, getUserId(user)), getUserId(user)));
    int projectID = getProjectId(taskName);

    //Here we update hours and Cost in Tasks
    taskRepo.subtractHours(subtaskRepo.getHours(subtaskID, taskId), taskName, getProjectId(taskName));
    taskRepo.subtractCost(subtaskRepo.getCost(subtaskID, taskId), taskName, getProjectId(taskName));

    //Here we update hours and Cost in Projects
    projectRepo.subtractCost(subtaskRepo.getCost(subtaskID, taskId), projectID);
    projectRepo.subtractHours(subtaskRepo.getHours(subtaskID, taskId), projectID);

    //Here we delete subtask from a Task
    subtaskRepo.deleteSubtask(subtaskID, taskId);
  }
}
