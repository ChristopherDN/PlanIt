package now.planit.Domain.Services;

import now.planit.Data.Repo.DBFacade;
import now.planit.Domain.Models.Subtask;
import now.planit.Domain.Models.User;

import java.util.ArrayList;

public class SubtaskService {
    DBFacade dbFacade = new DBFacade();

    public ArrayList<Subtask> getSubtasks(String taskName, User user) {
        return dbFacade.getSubtask(taskName, user);
    }

    public void createTask(String subtaskName, String startDate, String finishDate, int cost, String taskName, User user) {
        dbFacade.createTask(subtaskName, startDate, finishDate, cost, taskName, user);
    }
}
