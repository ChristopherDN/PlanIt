package now.planit.Domain.Services;

import now.planit.Data.Repo.DBFacade;

public class ProjectService{

    DBFacade dbFacade = new DBFacade();


    public void createProject(String projectName, String start, String finish, int budget) {
        dbFacade.createProject(projectName, start, finish,budget);
    }
}
