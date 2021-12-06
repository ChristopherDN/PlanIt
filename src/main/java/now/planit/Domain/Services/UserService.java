package now.planit.Domain.Services;

import now.planit.Data.Repo.DBFacade;
import now.planit.Domain.Models.User;
import now.planit.Exceptions.QueryDomainViewFailedException;

public class UserService {
  DBFacade dbFacade = new DBFacade();


  public void registerUser(String name, String email, String password) throws QueryDomainViewFailedException {
    dbFacade.registerUser(name, email, password);
  }

  public User validateLogin(String email, String password) throws QueryDomainViewFailedException {

   return dbFacade.validateLogin(email, password);
  }
  public void editName(String newName, User user) throws QueryDomainViewFailedException {
    if (!newName.equals(user.getName()) && !newName.equals("")){
      dbFacade.editName(newName, user);
      user.setName(newName);
    }
  }

  public void editMail(String newEmail, User user) throws QueryDomainViewFailedException {
    if (!newEmail.equals(user.getName()) && !newEmail.equals("")){
      dbFacade.editMail(newEmail, user);
      user.setEmail(newEmail);
    }
  }

  public void changePassword(String newPassword, User user) throws QueryDomainViewFailedException {
    if (!newPassword.equals(user.getName()) && !newPassword.equals("")) {
      dbFacade.changePassword(newPassword, user);
      user.setPassword(newPassword);
    }
  }
}
