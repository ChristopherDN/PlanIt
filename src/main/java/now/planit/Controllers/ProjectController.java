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
import java.util.ArrayList;


@Controller
public class ProjectController {
    User user;
    ProjectService projectService = new ProjectService();
    ArrayList<Project> projects = new ArrayList<>();


    @GetMapping("/myProjects")
    public String myProjects(Model model, WebRequest request) {
        updateProjects(request, model);
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
        model.addAttribute("loopList", projects); //Skal den add projects her igen?
        // Er det nødvendigt, når den adder i /myProjects, skal den ikke bare opdatere ArrayListen?
        return "redirect:/myProjects";
    }

    @GetMapping("/removeProject/{id}")
    public String deleteProject(@PathVariable(value = "id") String id, Model model, WebRequest request) {
        if (user == null){
            return "login/login";
        }
        projectService.deleteProject(id, user);
        updateProjects(request,model);
        return "redirect:/myProjects";
    }

    //Bruges til at opdatere Projects og adder til Model
    public void updateProjects(WebRequest request, Model model){
        user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        projects = projectService.getProjects(user);
        model.addAttribute("loopList", projects);
    }

}