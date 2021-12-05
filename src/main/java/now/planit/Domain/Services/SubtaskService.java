package now.planit.Domain.Services;

import now.planit.Data.Repo.DBFacade;
import now.planit.Domain.Models.Subtask;
import now.planit.Domain.Models.User;

import java.util.ArrayList;

public class SubtaskService {
    DBFacade dbFacade = new DBFacade();

    public ArrayList<Subtask> getSubtasks(String taskName, User user) {
        return dbFacade.getSubtasks(taskName, user);
    }

    public void createSubtask(String subtaskName, String hours, int cost, String taskName, User user) {
        dbFacade.createSubtask(subtaskName, hours, cost, taskName, user);
    }

    public void deleteSubtask(String taskName, String subtaskName, User user) {
        dbFacade.deleteSubtask(taskName, subtaskName, user);
    }
}