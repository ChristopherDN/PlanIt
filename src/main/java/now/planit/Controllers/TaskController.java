package now.planit.Controllers;

import now.planit.Data.Repo.RepoTask;
import now.planit.Domain.Models.Subtask;
import now.planit.Domain.Models.Task;
import now.planit.Domain.Models.User;
import now.planit.Domain.Services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import java.util.ArrayList;

@Controller
public class TaskController {
  User user;
  String projectName;
  TaskService taskService = new TaskService();
  RepoTask repoTask = new RepoTask();
  ArrayList<Task> tasks = new ArrayList();

  //Endpoint to dynamic display(loop) all task and display user information
  @GetMapping("/createTask")
  public String createTasks(WebRequest request, Model model) {
    user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
    int hours =0;
    model.addAttribute("hours", taskService.calculate(repoTask.calculate(hours)));
    tasks = taskService.getTasks(projectName, user);
    model.addAttribute("userName", user.getName());
    model.addAttribute("tasks", tasks);
    return "/project/createTask";
  }

  //Endpoint to pass and connect data(id) from myProjects site to createtask site.
  @GetMapping("/update/{id}")
  public String updateProject(@PathVariable(value = "id") String id, WebRequest request, Model model) {
    projectName = id;
    user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
    model.addAttribute("tasks", tasks);
    tasks = taskService.getTasks(id, user);
    return "redirect:/createTask";
  }

  //Endpoint that stores parameters from task and pass them down to the service.
  @PostMapping("/createTaskParam")
  public String createTask(WebRequest request, Model model) {
    user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
    model.addAttribute("userName", user.getName());
    taskService.createTask
            (request.getParameter("taskName"),
            request.getParameter("startDate"),
            request.getParameter("finishDate"),
            Integer.parseInt(request.getParameter("cost")),
            projectName, user);

    return "redirect:/createTask";
  }

  @GetMapping("/removeTask/{id}")
  public String deleteTask(@PathVariable(value = "id") String id, Model model) {
    System.out.println(id);
    taskService.deleteTask(projectName, id, user);
    tasks = taskService.getTasks(id, user);
    model.addAttribute("tasks", tasks);
    return "redirect:/createTask";
  }
}

