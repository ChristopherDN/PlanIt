package now.planit.Domain.Services;

import now.planit.Data.Repo.FacadeMySQL;
import now.planit.Domain.Models.User;
import now.planit.Exceptions.UserEditException;
import now.planit.Exceptions.UserAlreadyExistException;

public class UserService {
  FacadeMySQL facadeMySQL;

  //Dependency injection constructor
  public UserService(FacadeMySQL facadeMySQL) {
    this.facadeMySQL = facadeMySQL;
  }

  public void registerUser(String name, String email, String password) throws UserAlreadyExistException {

    if (facadeMySQL.registerUser(name, email, password) == 0){
      throw new UserAlreadyExistException("Email already exists");
    }
  }

  public User validateLogin(String email, String password) {
   return facadeMySQL.validateLogin(email, password);
  }

  public void updateName(String newName, User user) throws UserEditException {
    if (!newName.equals(user.getName()) && !newName.equals("")){
      facadeMySQL.updateName(newName, user);
      user.setName(newName);
    }
    throw new UserEditException("Failed to change name");

  }

  public void updateEmail(String newEmail, User user) throws UserEditException {
    if (!newEmail.equals(user.getName()) && !newEmail.equals("")){
      facadeMySQL.updateEmail(newEmail, user);
      user.setEmail(newEmail);

    }
    throw new UserEditException("Failed to change email");

  }

  public void updatePassword(String newPassword, User user) throws UserEditException {
    if (!newPassword.equals(user.getName()) && !newPassword.equals("")) {
      facadeMySQL.updatePassword(newPassword, user);
      user.setPassword(newPassword);
    }
    throw new UserEditException("Failed to change password");

  }

  public void deleteUser(String email, String password) {
    facadeMySQL.deleteUser(email, password);
  }

  public void updateUserData(User user) {
    facadeMySQL.getUserProjects(user);
  }
}
