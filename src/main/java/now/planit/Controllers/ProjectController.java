package now.planit.Controllers;

import now.planit.Domain.Models.Project;
import now.planit.Domain.Models.Task;
import now.planit.Domain.Models.User;
import now.planit.Domain.Services.ProjectService;
import now.planit.Domain.Services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import java.util.ArrayList;


@Controller
public class ProjectController {
    User user;
    ProjectService projectService = new ProjectService();
    ArrayList<Project> projects = new ArrayList<>();
    TaskService taskService = new TaskService();
    ArrayList<Task> tasks = new ArrayList();
    String projectName;


    @GetMapping("/myProjects")
    public String myProjects(Model model, WebRequest request) {
        user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        projects = projectService.getProjects(user);
        tasks = taskService.getTasks(projectName, user);
        model.addAttribute("tasks", tasks);
        model.addAttribute("loopList", projects);
        // model.addAttribute("project", project); Den her er ikke nødvendig. Den kender til den igennem ArrayListen med Projects :)
        model.addAttribute("userName", user.getName());
        return "project/myProjects";// endpoint change
    }


    @PostMapping("/createProject")
    public String createProject(WebRequest request, Model model) {
        user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        projectService.createProject(request.getParameter("name"),
                request.getParameter("start"),
                request.getParameter("finish"),
                Integer.parseInt(request.getParameter("budget")), user);
        model.addAttribute("loopList", projects);
        //model.addAttribute("userName", user.getName());
        return "redirect:/myProjects";
    }

    @GetMapping("/removeProject/{id}")
    public String deleteProject(@PathVariable(value = "id") String id, Model model) {
        projectService.deleteProject(id, user);
        projects = projectService.getProjects(user);
        model.addAttribute("loopList", projects);
        return "redirect:/myProjects";
    }
}