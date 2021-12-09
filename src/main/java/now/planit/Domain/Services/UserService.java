package now.planit.Domain.Services;

import now.planit.Data.Repo.DBFacade;
import now.planit.Domain.Models.Project;
import now.planit.Domain.Models.User;
import now.planit.Exceptions.UserNotExistException;

import java.util.ArrayList;

public class UserService {
  DBFacade dbFacade = new DBFacade();


  public void registerUser(String name, String email, String password) throws UserNotExistException {

    if (dbFacade.registerUser(name, email, password) == 0){
      throw new UserNotExistException("Email already excists");
    }
  }

  public User validateLogin(String email, String password) {


    /*Not usefull, we allready have javascript validation.
    if(user == null) {
      throw new UserNotExistException("Sorry, this is not a correct user!!!");
    }
    */
   return dbFacade.validateLogin(email, password);
  }
  public void editName(String newName, User user) {
    if (!newName.equals(user.getName()) && !newName.equals("")){
      dbFacade.editName(newName, user);
      user.setName(newName);
    }
    //TODO throw new exception, failed to change password redirect to errror page.
  }

  //TODO Mangler validering ift. om mail er i systemet...
  public void editMail(String newEmail, User user) {
    if (!newEmail.equals(user.getName()) && !newEmail.equals("")){
      dbFacade.editMail(newEmail, user);
      user.setEmail(newEmail);
    }
    //TODO throw new exception, failed to change password redirect to errror page.
  }

  public void changePassword(String newPassword, User user) {
    if (!newPassword.equals(user.getName()) && !newPassword.equals("")) {
      dbFacade.changePassword(newPassword, user);
      user.setPassword(newPassword);
    }

    //TODO throw new exception, failed to change password redirect to errror page.
  }

  public void deleteUser(String email, String password) {
    dbFacade.deleteUser(email, password);
  }
}
