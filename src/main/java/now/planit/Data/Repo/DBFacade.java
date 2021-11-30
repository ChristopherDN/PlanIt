package now.planit.Data.Repo;

import now.planit.Domain.Models.User;

/**
 * @author Christopher
 */
public class DBFacade {
  RepoInterface repoProject = new RepoProject() {
  };
  RepoUsers repoUsers = new RepoUsers();

  //Skal Implementere Interface her, så den bliver brugt......

  public void registerUser(String name, String email, String password){
    repoUsers.registerUser(name, email, password);

  }

  public User validateLogin(String email, String password){
    return repoUsers.validateLogin(email, password);
  }

    public void createProject(String name1, String start, String finish, int budget) {

    }
}
