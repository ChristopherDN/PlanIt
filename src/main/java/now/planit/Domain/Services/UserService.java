package now.planit.Domain.Services;

import now.planit.Data.Repo.RepoUsers;
import now.planit.Domain.Models.User;

public class UserService {
  RepoUsers repoUsers = new RepoUsers();


  public void registerUser(String name, String email, String password) {
    repoUsers.registerUser(name, email, password);
  }

  public User validateLogin(String email, String password){
   return repoUsers.validateLogin(email, password);
  }

}
