package now.planit.Data.Repo;

import now.planit.Domain.Models.Project;
import now.planit.Domain.Models.Subtask;
import now.planit.Domain.Models.Task;
import now.planit.Domain.Models.User;
import now.planit.Exceptions.QueryDomainViewFailedException;

import java.util.ArrayList;

/**
 * @author Christopher
 */

public class DBFacade {
  RepoProject repoProject = new RepoProject();
  RepoUsers repoUsers = new RepoUsers();
  RepoTask repoTask = new RepoTask();
  RepoSubtask repoSubtask = new RepoSubtask();


  //UserREPO
  public void registerUser(String name, String email, String password) throws QueryDomainViewFailedException {
    repoUsers.registerUser(name, email, password);
  }

  public User validateLogin(String email, String password) throws QueryDomainViewFailedException {
    return repoUsers.validateLogin(email, password);
  }

  public int getUserId(User user) throws QueryDomainViewFailedException {
    return repoUsers.getUserId(user);
  }

  public void editName(String name, User user) throws QueryDomainViewFailedException {
    repoUsers.editName(name, getUserId(user));
  }

  public void editMail(String email, User user) throws QueryDomainViewFailedException {
    repoUsers.editEmail(email, getUserId(user));
  }

  public void changePassword(String password, User user) throws QueryDomainViewFailedException {
    repoUsers.editPassword(password, getUserId(user));
  }


  //ProjectREPO

  public void createProject(String name1, String start, String finish, int budget, User user) throws QueryDomainViewFailedException {
    repoProject.createProject(name1, start, finish, budget, getUserId(user));
  }

  public int getProjectId(String projectName, int userId) throws QueryDomainViewFailedException {
    return repoProject.getProjectId(projectName, userId);
  }
  public int getProjectId2(int taskId, int userId) throws QueryDomainViewFailedException {return repoProject.getProjectId2(taskId, userId);}

  public ArrayList<Project> getProjects(User user) throws QueryDomainViewFailedException {
    return repoProject.getProjects(repoUsers.getUserId(user));
  }

  public void deleteProject(String projectName, User user) throws QueryDomainViewFailedException {
    repoProject.deleteProject(getProjectId(projectName, getUserId(user)), getUserId(user));
  }

  //TaskREPO
  public ArrayList<Task> getTasks(String projectName, User user) throws QueryDomainViewFailedException {
    return repoTask.getTasks(getProjectId(projectName, getUserId(user)));
  }

  public int getTaskId(String taskName, int projectId) throws QueryDomainViewFailedException {
    return repoTask.getTaskId(taskName, projectId);
  }

  public void createTask(String taskName, String startDate, String finishDate, int cost, String projectName, User user) throws QueryDomainViewFailedException {
    repoTask.createTask(taskName, startDate, finishDate, cost, getProjectId(projectName, getUserId(user)));
  }
  public void deleteTask(String projectName, String taskName, User user) throws QueryDomainViewFailedException {
    int taskId = getTaskId(taskName, getProjectId(projectName, getUserId(user)));
        int projectID = getProjectId(projectName, getUserId(user));
    repoTask.deleteTask(taskId, projectID);
  }

  //SUbTASKREPO

  public ArrayList<Subtask> getSubtasks(String taskName, User user) throws QueryDomainViewFailedException {
    //TODO FEJL HER Skal vi joine?
    return repoSubtask.getSubtasks(getTaskId(taskName, getProjectId(getProjectName(taskName), getUserId(user))));
  }

 public int getProjectIDFromTasks(String taskName) throws QueryDomainViewFailedException {
    return repoTask.getProjectID(taskName);
  }

  public void createSubtask(String subtaskName, String hours, int cost, String taskName, User user) throws QueryDomainViewFailedException {
    repoSubtask.createSubtask(subtaskName, hours, cost, getTaskId(taskName, getProjectIDFromTasks(taskName)));
    repoTask.addHours(hours, taskName, getProjectIDFromTasks(taskName));
  }

  public int getSubtaskId(String subtaskName, int taskId) throws QueryDomainViewFailedException {
    return repoSubtask.getSubtaskId(subtaskName, taskId);
  }



  public void deleteSubtask(String taskName, String subtaskName, User user) throws QueryDomainViewFailedException {
    int subtaskID =  getSubtaskId(subtaskName, getTaskId(taskName, getProjectId(getProjectName(taskName) , getUserId(user))));
    int taskId = getTaskId(taskName, getProjectId(getProjectName(taskName) , getUserId(user)));
    repoSubtask.deleteSubtask(subtaskID, taskId);
  }

  //Test
  public String getProjectName(String taskName) throws QueryDomainViewFailedException {
    return repoProject.getProjectName(taskName);
  }


}