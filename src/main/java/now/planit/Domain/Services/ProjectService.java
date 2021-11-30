package now.planit.Domain.Services;

import now.planit.Data.Repo.DBFacade;
import now.planit.Domain.Models.User;

public class ProjectService{

    DBFacade dbFacade = new DBFacade();


    public void createProject(String projectName, String start, String finish, int budget, User user) {
        dbFacade.createProject(projectName, start, finish,budget, user);
    }
}
