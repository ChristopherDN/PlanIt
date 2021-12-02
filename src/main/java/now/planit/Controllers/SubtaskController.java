package now.planit.Controllers;

import now.planit.Domain.Models.Subtask;
import now.planit.Domain.Models.User;
import now.planit.Domain.Services.SubtaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import java.util.ArrayList;

@Controller
public class SubtaskController {
    User user;
    String taskName;
    SubtaskService subtaskService = new SubtaskService();
    ArrayList<Subtask> subtasks = new ArrayList();

    //Endpoint to dynamic display(loop) all task and display user information
    @GetMapping("/createSubtask")
    public String createSubtasks(WebRequest request, Model model) {
        user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        subtasks = subtaskService.getSubtasks(taskName, user);
        model.addAttribute("userName", user.getName());
        model.addAttribute("subtasks", subtasks);
        return "/project/createSubtask";
    }

    //Endpoint to pass and connect data(id) from myProjects site to createtask site.
    @GetMapping("/rerun/{id}")
    public String updateSubtask(@PathVariable(value = "id") String id, WebRequest request, Model model) {
        taskName = id;
        user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        model.addAttribute("subtasks", subtasks);
        subtasks = subtaskService.getSubtasks(taskName, user);
        return "redirect:/createSubtask";
    }

    //Endpoint that stores parameters from task and pass them down to the service.
    @PostMapping("/createSubtaskParam")
    public String createSubtask(WebRequest request, Model model) {
        user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        model.addAttribute("userName", user.getName());
        subtaskService.createSubtask
                (request.getParameter("subtaskName"),
                        request.getParameter("startDate"),
                        request.getParameter("finishDate"),
                        Integer.parseInt(request.getParameter("cost")),
                        taskName, user);
        System.out.println(taskName);
        return "redirect:/createSubtask";
    }

    @GetMapping("/removeSubtask/{id}")
    public String deleteTask(@PathVariable(value = "id") String id, Model model) {
        System.out.println(id);
        subtaskService.deleteTask(id, user);
        subtasks = subtaskService.getSubtasks(id, user);
        model.addAttribute("subtasks", subtasks);
        return "redirect:/createTask";
    }


}