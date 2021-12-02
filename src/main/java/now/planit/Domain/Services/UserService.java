package now.planit.Domain.Services;

import now.planit.Data.Repo.DBFacade;
import now.planit.Domain.Models.User;

public class UserService {
  DBFacade dbFacade = new DBFacade();


  public void registerUser(String name, String email, String password)  {
    dbFacade.registerUser(name, email, password);
  }

  public User validateLogin(String email, String password){
    /*
    try{


    }catch(){

    }
    */
   return dbFacade.validateLogin(email, password);
  }


  public void editName(String name, User user) {
    dbFacade.editName(name, user);
  }

  public void editMail(String email, User user) {
    dbFacade.editMail(email, user);
  }

  public void changePassword(String password, User user) {
    dbFacade.changePassword(password, user);
  }
}
