package now.planit.Domain.Services;

import now.planit.Data.Repo.DBFacade;
import now.planit.Domain.Models.Project;
import now.planit.Domain.Models.User;
import java.util.ArrayList;

public class ProjectService{

    DBFacade dbFacade = new DBFacade();


    public void createProject(String projectName, String start, String finish, int budget, User user)  {
        dbFacade.createProject(projectName, start, finish,budget, user);
    }

    public ArrayList<Project> getProjects(User user) {
        return dbFacade.getProjects(user);
    }


  public void deleteProject(String projectName, User user)  {
      dbFacade.deleteProject(projectName, user);
  }
}
