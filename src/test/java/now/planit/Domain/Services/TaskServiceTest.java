package now.planit.Domain.Services;

import now.planit.Data.Repo.FacadeMySQL;
import now.planit.Data.Repo.MapperDB;
import now.planit.Data.Repo.TaskRepo;
import now.planit.Domain.Models.Task;
import now.planit.Domain.Models.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author roed
 */
class TaskServiceTest {
  TaskService taskService = new TaskService(new FacadeMySQL(new TaskRepo(new MapperDB())));
  User user = new User("Junit test", "user@testing.com", "testing");
  ArrayList<Task> tasks = taskService.getTasks("Test project", user);
  String expected;

  @Test
  void createTaskName() {
    taskService.createTask("Test Task","2021-12-13","2021-12-14", "Test project", user);
  expected = "Test Task";
    for (int i = 0; i < tasks.size(); i++) {
      if (tasks.get(i).getTaskName().equals(expected)){
        assertEquals(expected, tasks.get(i).getTaskName());
      }
    }
  }

  @Test
  void createTaskStart() {

  }

  @Test
  void createTaskFinish() {

  }

  @Test
  void deleteTask() {

  }
}