package now.planit.Domain.Services;

import now.planit.Data.Repo.DBFacade;
import now.planit.Domain.Models.User;

public class UserService {
  DBFacade dbFacade = new DBFacade();


  public void registerUser(String name, String email, String password) {
    dbFacade.registerUser(name, email, password);
  }

  public User validateLogin(String email, String password){
   return dbFacade.validateLogin(email, password);
  }

}
