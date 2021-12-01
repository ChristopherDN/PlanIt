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

  public void editName(String newName, User user) {
    if (!newName.equals(user.getName()) && !newName.equals("")){
      dbFacade.editName(newName, user);
    }
  }

  public void editMail(String newEmail, User user) {
    if (!newEmail.equals(user.getName()) && !newEmail.equals("")){
      dbFacade.editMail(newEmail, user);
    }
  }

  public void changePassword(String newPassword, User user) {
    if (!newPassword.equals(user.getName()) && !newPassword.equals("")){
      dbFacade.editPassword(newPassword, user);
    }
  }
}
