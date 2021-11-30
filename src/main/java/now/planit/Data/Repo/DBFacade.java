package now.planit.Data.Repo;

import now.planit.Domain.Models.User;

/**
 * @author Christopher
 */
public class DBFacade {
  RepoProject repoProject = new RepoProject();
  RepoUsers repoUsers = new RepoUsers();


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

    //TaskREPO



  //SUbTASKREPO


}
