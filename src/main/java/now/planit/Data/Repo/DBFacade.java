package now.planit.Data.Repo;

import now.planit.Domain.Models.Project;
import now.planit.Domain.Models.Subtask;
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
  RepoSubtask repoSubtask = new RepoSubtask();


  //UserREPO
  public int registerUser(String name, String email, String password) {
    return repoUsers.registerUser(name, email, password);
  }

  public User validateLogin(String email, String password) {
    User user = repoUsers.validateLogin(email, password);
    user.setProjects(repoProject.getProjects(getUserId(user)));
    String projectName;

    //TODO Fejl Her, loader kun task 5-6 i projekt 1 og 2. Tror det er fra linje 32, hvor den skal hente Tasks. Der er ikke data nok til at
    //hente de rigtige data. Måske et forloop i service laget kan hente 1 array liste af gangen?
    for (int i = 0; i < user.getProjects().size(); i++) {
      projectName = user.getProjects().get(i).getName();
      System.out.println(projectName);
      user.getProjects().get(i).setTasks(getTasks(projectName, user));
      for (int j = 0; j < user.getProjects().get(j).getTasks().size(); j++) {
        user.getProjects().get(j).getTasks().get(j).setSubtasks(getSubtasks(user.getProjects().get(j).getTasks().get(j).getTaskName(), user));
      }
    }
    return user;
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
  public ArrayList<Project> getProjects(User user) {
    return repoProject.getProjects(repoUsers.getUserId(user));
  }

  public void createProject(String name1, String start, String finish, int budget, User user) {
    repoProject.createProject(name1, start, finish, budget, getUserId(user));
  }

  public int getProjectId(String projectName, int userId) {
    return repoProject.getProjectId(projectName, userId);
  }


  public void deleteProject(String projectName, User user) {
    repoProject.deleteProject(getProjectId(projectName, getUserId(user)), getUserId(user));
  }

  public String getProjectName(String taskName) {
    return repoProject.getProjectName(taskName);
  }

  //TaskREPO
  public ArrayList<Task> getTasks(String projectName, User user) {
    System.out.println(projectName);
    int projectId = getProjectId(projectName, getUserId(user));
    System.out.println(projectId);
    return repoTask.getTasks(projectId);
  }

  public int getTaskId(String taskName, int projectId) {
    return repoTask.getTaskId(taskName, projectId);
  }

  public void createTask(String taskName, String startDate, String finishDate, String projectName, User user) {
    repoTask.createTask(taskName, startDate, finishDate, getProjectId(projectName, getUserId(user)));
  }

  public void deleteTask(String projectName, String taskName, User user) {
    int taskId = getTaskId(taskName, getProjectId(projectName, getUserId(user)));
    int projectID = getProjectId(projectName, getUserId(user));

    repoProject.subtractCost(repoTask.getCost(taskId, projectID), projectID);
    repoProject.subtractHours(repoTask.getHours(taskId, projectID), projectID);


    repoTask.deleteTask(taskId, projectID);
  }

  private void calculateHours(int hours, String taskName, int projectId) {
    repoTask.addHours(hours, taskName, projectId);
    repoProject.addActualHours(hours, projectId);
  }

  public void calculateCost(int cost, String taskName, int projectId) {
    repoTask.addActualCost(cost, taskName, projectId);
    repoProject.addActualCost(cost, projectId);
  }

  //SUbTASKREPO

  public ArrayList<Subtask> getSubtasks(String taskName, User user) {
    return repoSubtask.getSubtasks(getTaskId(taskName, getProjectId(getProjectName(taskName), getUserId(user))));
  }

  public int getProjectIDFromTasks(String taskName) {
    return repoTask.getProjectID(taskName);
  }

  public void createSubtask(String subtaskName, int hours, int cost, String taskName, User user) {
    repoSubtask.createSubtask(subtaskName, hours, cost, getTaskId(taskName, getProjectIDFromTasks(taskName)));
    calculateHours(hours, taskName, getProjectIDFromTasks(taskName));
    calculateCost(cost, taskName, getProjectIDFromTasks(taskName));
  }


  public int getSubtaskId(String subtaskName, int taskId) {
    return repoSubtask.getSubtaskId(subtaskName, taskId);
  }


  public void deleteSubtask(String taskName, String subtaskName, User user) {
    //Load up ID´s that we need
    int subtaskID = getSubtaskId(subtaskName, getTaskId(taskName, getProjectId(getProjectName(taskName), getUserId(user))));
    int taskId = getTaskId(taskName, getProjectId(getProjectName(taskName), getUserId(user)));
    int projectID = getProjectIDFromTasks(taskName);

    //Here we update hours and Cost in Tasks
    repoTask.subtractHours(repoSubtask.getHours(subtaskID, taskId), taskName, getProjectIDFromTasks(taskName));
    repoTask.subtractCost(repoSubtask.getCost(subtaskID, taskId), taskName, getProjectIDFromTasks(taskName));

    //Here we update hours and Cost in Projects
    repoProject.subtractCost(repoSubtask.getCost(subtaskID, taskId), projectID);
    repoProject.subtractHours(repoSubtask.getHours(subtaskID, taskId), projectID);

    //Here we delete subtask from a Task
    repoSubtask.deleteSubtask(subtaskID, taskId);
  }

  //For JUnit test.
  public void deleteUser(String email, String password) {
    repoUsers.deleteUser(email, password);
  }


  //Jeff viewPage


}