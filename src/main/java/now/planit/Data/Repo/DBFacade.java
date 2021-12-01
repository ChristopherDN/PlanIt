package now.planit.Data.Repo;

import now.planit.Domain.Models.Project;
import now.planit.Domain.Models.Task;
import now.planit.Domain.Models.User;

import java.util.ArrayList;

/**
 * @author Christopher
 */

public class DBFacade {
  RepoProject repoProject = new RepoProject();
  RepoUsers repoUsers = new RepoUsers();
  RepoTask repoTask = new RepoTask();
  int userId;


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

  //ProjectREPO

  public void createProject(String name1, String start, String finish, int budget, User user) {
    repoProject.createProject(name1, start, finish, budget, getUserId(user));
  }

  public int getProjectId(String projectName, int userId) {
    return repoProject.getProjectId(projectName, userId);
  }

  public ArrayList<Project> getProjects(User user) {
    return repoProject.getProjects(repoUsers.getUserId(user));
  }


  //TaskREPO
  public ArrayList<Task> getTasks(String projectName, User user) {
    return repoTask.getTasks(getProjectId(projectName, getUserId(user)));
  }

  public void deleteProject(String projectName, User user) {
    repoProject.deleteProject(getProjectId(projectName, getUserId(user)), getUserId(user));
  }


  public void createTask(String taskName, String startDate, String finishDate, int cost, String projectName, User user) {
    repoTask.createTask(taskName, startDate, finishDate, cost, getProjectId(projectName, getUserId(user)));
  }

  //SUbTASKREPO


}
