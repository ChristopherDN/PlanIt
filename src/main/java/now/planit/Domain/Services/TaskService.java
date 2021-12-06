package now.planit.Domain.Services;

import now.planit.Data.Repo.DBFacade;
import now.planit.Domain.Models.Task;
import now.planit.Domain.Models.User;
import now.planit.Exceptions.QueryDomainViewFailedException;

import java.util.ArrayList;

public class TaskService {
    DBFacade dbFacade = new DBFacade();


    public ArrayList<Task> getTasks(String projectName, User user) throws QueryDomainViewFailedException {
        return dbFacade.getTasks(projectName, user);
    }

    public void createTask(String taskName, String startDate, String finishDate, int cost, String projectName, User user) throws QueryDomainViewFailedException {
        dbFacade.createTask(taskName, startDate, finishDate, cost, projectName, user);
    }

    public void deleteTask(String projectName, String taskName, User user) throws QueryDomainViewFailedException {
        dbFacade.deleteTask(projectName, taskName, user);
    }
}
