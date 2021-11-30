package now.planit.Data.Repo;

import now.planit.Domain.Models.Project;
import now.planit.Domain.Models.Task;
import now.planit.Domain.Models.User;

import java.util.ArrayList;

/**
 * @author Christopher
 */
import java.util.ArrayList;

public class DBFacade {
  RepoProject repoProject = new RepoProject();
  RepoUsers repoUsers = new RepoUsers();
  RepoTask repoTask = new RepoTask();


  //UserREPO
  public void registerUser(String name, String email, String password){
    repoUsers.registerUser(name, email, password);

  }

  public User validateLogin(String email, String password){
    return repoUsers.validateLogin(email, password);
  }

  public int getUserId(User user){
    return repoUsers.getUserId(user);
  }

  //ProjectREPO

    public void createProject(String name1, String start, String finish, int budget, User user) {
      repoProject.createProject(name1, start, finish, budget, getUserId(user));
    }

    public int getProjectId(String projectName, int userId){
    return repoProject.getProjectId(projectName,userId);
    }




  //TaskREPO
  public ArrayList<Task> getTasks(String projectName, User user) {
    repoTask.getTasks(getProjectId(projectName,getUserId(user)));

  }




  //SUbTASKREPO



  public ArrayList<Project>getProjecks(User user) {
    userId = repoUsers.getUserId(user);
    return repoProject.getProjects(userId);
  }

}
