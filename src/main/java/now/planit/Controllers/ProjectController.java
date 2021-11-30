package now.planit.Controllers;

import now.planit.Domain.Models.Project;
import now.planit.Domain.Models.User;
import now.planit.Domain.Services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import java.util.ArrayList;


@Controller
public class ProjectController {

    User user;
    Project project;
    ArrayList<Project> projectArrayList = new ArrayList<>();
    String name = "Chris";
    ProjectService projectService = new ProjectService();

    @GetMapping("/myProjects")
    public String myProjects(Model model) {

        model.addAttribute("loopList", projectArrayList );
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
        projectService.createProject(name1, start, finish, budget);
        model.addAttribute("loopList", projectArrayList );
        return "redirect:/myProjects";
    }

    /*
    @GetMapping("/update/{id}")
    public String deleteProduct(@PathVariable(value = "id") String id, Model model) {
        System.out.println();
        return "redirect:/myprojects";
    }
    */







}
