package now.planit.Controllers;

import now.planit.Domain.Models.Project;
import now.planit.Domain.Models.User;
import now.planit.Domain.Services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.lang.reflect.Array;
import java.util.ArrayList;


@Controller
public class ProjectController {

    User user;
    Project project;
    ProjectService projectService = new ProjectService();
    ArrayList<Project> projects = new ArrayList<>();
    String name = "Chris";


    @GetMapping("/myProjects")
    public String myProjects(Model model, WebRequest request) {
        user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        projects = projectService.getProjects(user);
        model.addAttribute("loopList", projects);
        model.addAttribute("project", project);
        model.addAttribute("username", name);
        return "project/myProjects";
    }


    @PostMapping("/createProject")
    public String createProject(WebRequest request, Model model) {
        String name1 = request.getParameter("name");
        String start = request.getParameter("start");
        String finish = request.getParameter("finish");
        int budget = Integer.parseInt(request.getParameter("budget"));
        user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        projectService.createProject(name1, start, finish, budget, user);
        model.addAttribute("loopList", projects);
        return "redirect:/myProjects";
    }


}
