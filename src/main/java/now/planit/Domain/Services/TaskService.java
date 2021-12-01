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

    public void createTask(String taskName, String startDate, String finishDate, int cost, String projectName, User user) {
        System.out.println("1" +taskName);
        System.out.println("2" +startDate);
        System.out.println("3" +finishDate);
        System.out.println("4" +cost);
        System.out.println("5" +projectName);
        System.out.println("6" +user);
        dbFacade.createTask(taskName, startDate, finishDate, cost, projectName, user);
    }
}
