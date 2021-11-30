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
ProjectService projectService = new ProjectService();
ArrayList<Project> projects = new ArrayList();
User user;


    @GetMapping("/createproject")
    public String createproject() {
        return "project/createProject";
    }






}
