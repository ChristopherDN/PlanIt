package now.planit.Controllers;

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
  ProjectService projectService = new ProjectService();
  TaskService taskService = new TaskService();
  SubtaskService subtaskService = new SubtaskService();
  UserService userService = new UserService();
  User user;
  ArrayList<Project> projects = new ArrayList<>();
  ArrayList<Task> tasks = new ArrayList<>();
  ArrayList<Subtask> subtasks = new ArrayList<>();

  @GetMapping("/projectOverview")
  public String index(HttpSession session, Model model, WebRequest request) {
    user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
    model.addAttribute("projects", user.getProjects());
    model.addAttribute("user", user);

   /* try {
      for (int i = 0; i < user.getProjects().size(); i++) {
        System.out.println(user.getProjects().get(i).getTasks().get(i).getTaskName());
        for (int j = 0; j < user.getProjects().get(j).getTasks().size(); j++) {
          System.out.println(user.getProjects().get(j).getTasks().get(j).getSubtasks().get(j).getSubtaskName());

        }
        System.out.println("End of Iteration");
      }
    }catch (Exception e){
      e.getMessage();
    }*/
    if (session.getAttribute("user")!= null){

      return "project/projectOverview";
    }
    return "index";
  }

  @GetMapping("/showTasks/{id}")
  public String deleteProject(@PathVariable(value = "id") String id, Model model) {
    System.out.println(id);

    return "redirect:/projectOverview";
  }



  public void updateProjects(WebRequest request, Model model){
    user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
    model.addAttribute("projects", user.getProjects());
    model.addAttribute("user", user);
  }

  public void updateTasks(WebRequest request, Model model, String projectName)  {
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
