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
  FacadeMySQL facadeMySQL = new FacadeMySQL(new TaskRepo(new MapperDB()));
  User user = new User("Junit test", "user@testing.com", "testing");
  ArrayList<Task> tasks = facadeMySQL.getTasks("Test project", user);
  String expected;

  @Test
  void createTaskName() {
    facadeMySQL.createTask("Test Task","2021-12-13","2021-12-14", "Test project", user);
  expected = "Test Task";
    for (int i = 0; i < tasks.size(); i++) {
      if (tasks.get(i).getTaskName().equals(expected)){
        assertEquals(expected, tasks.get(i).getTaskName());
      }
      facadeMySQL.deleteTask("Test project", expected, user);
    }
  }

  @Test
  void createTaskStart() {
    facadeMySQL.createTask("Test Task","2021-12-13","2021-12-14", "Test project", user);
    expected = "2021-12-13";
    for (int i = 0; i < tasks.size(); i++) {
      if (tasks.get(i).getTaskName().equals(expected)){
        assertEquals(expected, tasks.get(i).getStartDate());
      }
      facadeMySQL.deleteTask("Test project", expected, user);
    }
  }

  @Test
  void createTaskFinish() {
    facadeMySQL.createTask("Test Task","2021-12-13","2021-12-14", "Test project", user);
    expected = "2021-12-14";
    for (int i = 0; i < tasks.size(); i++) {
      if (tasks.get(i).getTaskName().equals(expected)){
        assertEquals(expected, tasks.get(i).getFinishDate());
      }
      facadeMySQL.deleteTask("Test project", expected, user);
    }
  }

  @Test
  void deleteTask() {
    facadeMySQL.createTask("Test Task","2021-12-13","2021-12-14", "Test project", user);
    facadeMySQL.deleteTask("Test project", expected, user);
    expected = "";
    for (int i = 0; i < tasks.size(); i++) {
      if (tasks.get(i).getTaskName().equals("Test Task")){
        assertNotEquals(expected, tasks.get(i).getTaskName());
      }
    }
  }
}