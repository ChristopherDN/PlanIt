package now.planit.Domain.Services;

import now.planit.Data.Repo.FacadeMySQL;
import now.planit.Domain.Models.User;
import now.planit.Exceptions.UserEditException;
import now.planit.Exceptions.UserNotExistException;

import java.util.ArrayList;

public class UserService {
  FacadeMySQL facadeMySQL;

  public UserService(FacadeMySQL facadeMySQL) {
    this.facadeMySQL = facadeMySQL;
  }

  public void registerUser(String name, String email, String password) throws UserNotExistException {

    if (facadeMySQL.registerUser(name, email, password) == 0){
      throw new UserNotExistException("Email already exists");
    }
  }

  public User validateLogin(String email, String password) {
   return facadeMySQL.validateLogin(email, password);
  }

  public void editName(String newName, User user) throws UserEditException {
    if (!newName.equals(user.getName()) && !newName.equals("")){
      facadeMySQL.editName(newName, user);
      user.setName(newName);
    }
    throw new UserEditException("Failed to change name");

  }


  public void editMail(String newEmail, User user) throws UserEditException {
    if (!newEmail.equals(user.getName()) && !newEmail.equals("")){
      facadeMySQL.editMail(newEmail, user);
      user.setEmail(newEmail);

    }
    throw new UserEditException("Failed to change email");

  }

  public void changePassword(String newPassword, User user) throws UserEditException {
    if (!newPassword.equals(user.getName()) && !newPassword.equals("")) {
      facadeMySQL.changePassword(newPassword, user);
      user.setPassword(newPassword);
    }
    throw new UserEditException("Failed to change password");

  }

  public void deleteUser(String email, String password) {
    facadeMySQL.deleteUser(email, password);
  }

  public void updateUserData(User user) {
    facadeMySQL.loadUserData(user);
  }
}
