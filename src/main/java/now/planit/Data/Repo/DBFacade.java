package now.planit.Data.Repo;

import now.planit.Domain.Models.Project;
import now.planit.Domain.Models.Subtask;
import now.planit.Domain.Models.Task;
import now.planit.Domain.Models.User;
import now.planit.Domain.Services.ExceptionService;

import java.util.ArrayList;

/**
 * @author Christopher
 */

public class DBFacade {
  RepoProject repoProject = new RepoProject();
  RepoUsers repoUsers = new RepoUsers();
  RepoTask repoTask = new RepoTask();
  RepoSubtask repoSubtask = new RepoSubtask();
  int userId;
  int subtaskId;


  //UserREPO
  public void registerUser(String name, String email, String password) {
    repoUsers.registerUser(name, email, password);
  }

  public User validateLogin(String email, String password) {
    return repoUsers.validateLogin(email, password);
  }

  public int getUserId(User user) {
    return repoUsers.getUserId(user);
  }

  public void editName(String name, User user) {
    repoUsers.editName(name, getUserId(user));
  }

  public void editMail(String email, User user) {
    repoUsers.editEmail(email, getUserId(user));
  }

  public void changePassword(String password, User user) {
    repoUsers.editPassword(password, getUserId(user));
  }


  //ProjectREPO

  public void createProject(String name1, String start, String finish, int budget, User user) {
    repoProject.createProject(name1, start, finish, budget, getUserId(user));
  }

  public int getProjectId(String projectName, int userId) {
    return repoProject.getProjectId(projectName, userId);
  }

  public int getProjectId2(int taskId, int userId) {
    return repoProject.getProjectId2(taskId, userId);
  }

  public ArrayList<Project> getProjects(User user) {
    return repoProject.getProjects(repoUsers.getUserId(user));
  }

  public void deleteProject(String projectName, User user) {
    repoProject.deleteProject(getProjectId(projectName, getUserId(user)), getUserId(user));
  }

  //TaskREPO
  public ArrayList<Task> getTasks(String projectName, User user) {
    return repoTask.getTasks(getProjectId(projectName, getUserId(user)));
  }

  public int getTaskId(String taskName, int projectId) {
    return repoTask.getTaskId(taskName, projectId);
  }

  public int getTaskId2(int subtaskId, int projectId) {
    return repoTask.getTaskId2(subtaskId, userId);
  }

  public void createTask(String taskName, String startDate, String finishDate, int cost, String projectName, User user) {
    repoTask.createTask(taskName, startDate, finishDate, cost, getProjectId(projectName, getUserId(user)));
  }

  public void deleteTask(String taskName, User user) {
    repoTask.deleteTask(getTaskId(taskName, getUserId(user)), getProjectId2(getTaskId(taskName, getUserId(user)), getUserId(user)));
  }

  //SUbTASKREPO

  public ArrayList<Subtask> getSubtask(String taskName, User user) {
    return repoSubtask.getSubtasks(getTaskId(taskName, getUserId(user)));

  }

  public void createSubtask(String subtaskName, String startDate, String finishDate, int cost, String taskName, User user) {
    repoSubtask.createSubtask(subtaskName, startDate, finishDate, cost, getTaskId(taskName, getUserId(user)));
  }

  public int getSubtaskId(String subtaskName, int projectID){
    return repoSubtask.getSubtaskId(subtaskName, projectID);
  }


  public void deleteSubtask(String subtaskName, User user) {
    subtaskId = getSubtaskId(subtaskName, getUserId(user));
    System.out.println(subtaskId);
    int taskId;
        int projectId;
    //repoSubtask.deleteSubtask();
  }
}