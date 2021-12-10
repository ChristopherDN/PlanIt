package now.planit.Controllers;

import now.planit.Data.Repo.FacadeMySQL;
import now.planit.Data.Repo.MapperDB;
import now.planit.Data.Repo.ProjectRepo;
import now.planit.Domain.Models.Project;
import now.planit.Domain.Models.Subtask;
import now.planit.Domain.Models.Task;
import now.planit.Domain.Models.User;
import now.planit.Domain.Services.ProjectService;
import now.planit.Domain.Services.SubtaskService;
import now.planit.Domain.Services.TaskService;
import now.planit.Domain.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * @author roed
 */
@Controller
public class ProjectOverviewController {
  //ProjectService projectService = new ProjectService(new FacadeMySQL(new ProjectRepo(new MapperDB())));
  //SubtaskService subtaskService = new SubtaskService();
  TaskService taskService = new TaskService();
  //TaskService taskService = new TaskService(new FacadeMySQL(new ProjectRepo(new MapperDB())));
  //SubtaskService subtaskService = new SubtaskService(new FacadeMySQL(new ProjectRepo(new MapperDB())));
  SubtaskService subtaskService = new SubtaskService(new FacadeMySQL(new ProjectRepo(new MapperDB())));
  User user;
  ArrayList<Task> tasks = new ArrayList<>();
  ArrayList<Subtask> subtasks = new ArrayList<>();
  UserService userService = new UserService();

  @GetMapping("/projectOverview")
  public String index(HttpSession session, Model model, WebRequest request) {
    user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
    model.addAttribute("user", user);
    if (session.getAttribute("user") != null) {
      userService.updateUserData(user);
      return "project/projectOverview";
    }
    return "index";
  }

  @GetMapping("/showTasks/{id}")
  public String deleteProject(@PathVariable(value = "id") String id, Model model) {
    System.out.println(id);

    return "redirect:/projectOverview";
  }


  public void updateProjects(WebRequest request, Model model) {
    user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
    model.addAttribute("projects", user.getProjects());
    model.addAttribute("user", user);
  }

  public void updateTasks(WebRequest request, Model model, String projectName) {
    user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
    tasks = taskService.getTasks(projectName, user);
    model.addAttribute("tasks", tasks);
  }

  public void updateSubtasks(WebRequest request, Model model, String taskName) {
    user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
    subtasks = subtaskService.getSubtasks(taskName, user);
    model.addAttribute("subtasks", subtasks);
    model.addAttribute("userName", user.getName());
  }

}
