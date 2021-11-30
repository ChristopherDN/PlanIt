package now.planit.Controllers;

import now.planit.Domain.Models.Task;
import now.planit.Domain.Models.User;
import now.planit.Domain.Services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

@Controller
public class TaskController {
  User user;
  TaskService taskService = new TaskService();
  ArrayList<Task> tasks = new ArrayList();

  @GetMapping("/update/{id}")
  public String updateProject(@PathVariable(value = "id") String id, WebRequest request, Model model) {
    System.out.println("Kommer den her ind?????");
    user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
    model.addAttribute("tasks", tasks);
    tasks = taskService.getTasks(id, user);
    return "redirect:/createTask";
  }

  @GetMapping("/createTask")
  public String createTasks() {
    System.out.println("Kommer den ind i GetMapping?");
    return "/project/createTask";
  }
}
