package now.planit.Domain.Services;

import now.planit.Data.Repo.DBFacade;
import now.planit.Domain.Models.Task;
import now.planit.Domain.Models.User;

import java.util.ArrayList;

public class TaskService {
    DBFacade dbFacade = new DBFacade();

    public ArrayList<Task> getTasks(String projectName, User user) {
        return dbFacade.getTasks(projectName, user);
    }


    public void createTaks(String projectName, String start, String finish, int budget, User user) {
        dbFacade.createProject(projectName, start, finish,budget, user);
    }
}
