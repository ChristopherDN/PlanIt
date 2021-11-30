package now.planit.Data.Repo;

import now.planit.Domain.Models.Project;
import now.planit.Domain.Models.User;

import java.util.ArrayList;

/**
 * @author Christopher
 */
public class DBFacade {

  int userId;
  RepoProject repoProject = new RepoProject() {
  };
  RepoUsers repoUsers = new RepoUsers();

  //Skal Implementere Interface her, så den bliver brugt......

  public void registerUser(String name, String email, String password){
    repoUsers.registerUser(name, email, password);

  }

  public User validateLogin(String email, String password){
    return repoUsers.validateLogin(email, password);
  }

    public void createProject(String name1, String start, String finish, int budget, User user) {
      userId = repoUsers.getUserId(user);
      repoProject.createProject(name1, start, finish, budget, userId);
    }

  public ArrayList<Project>getProjecks(User user) {
    userId = repoUsers.getUserId(user);
    return repoProject.getProjects(userId);
  }

}
