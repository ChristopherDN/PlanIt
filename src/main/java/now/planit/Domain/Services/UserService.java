package now.planit.Domain.Services;

import now.planit.Data.Repo.RepoUsers;

public class UserService {
  RepoUsers repoUsers = new RepoUsers();


  public void registerUser(String name, String email, String password) {
    repoUsers.registerUser(name, email, password);
  }

}
