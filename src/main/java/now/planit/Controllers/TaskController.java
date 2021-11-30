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

  @GetMapping("/createTask")
  public String createTasks() {
    return "/project/createTask";
  }

  @GetMapping("/update/{id}")
  public String updateProject(@PathVariable(value = "id") String id, WebRequest request, Model model) {
    user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
    model.addAttribute("tasks", tasks);
    model.addAttribute("userName", user.getName());
    tasks = taskService.getTasks(id, user);
    return "redirect:/createTask";
  }


}
