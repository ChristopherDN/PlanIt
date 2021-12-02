package now.planit.Controllers;

import now.planit.Domain.Models.Project;
import now.planit.Domain.Models.Subtask;
import now.planit.Domain.Models.Task;
import now.planit.Domain.Models.User;
import now.planit.Domain.Services.ProjectService;
import now.planit.Domain.Services.SubtaskService;
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
    ArrayList<Task> tasks = new ArrayList();
    TaskService taskService = new TaskService();
    String projectName = "Project1";
    ArrayList<Subtask> subtasks = new ArrayList<>();
    String taskName;
    SubtaskService subtaskService = new SubtaskService();

    @PostMapping("/chooseProject/{id}")
    public String chooseProject(@PathVariable(value = "id") String id, WebRequest request, Model model){
        projectName = id;
        System.out.println(request.getParameter("projectName"));
        System.out.println("im in here");
        System.out.println(projectName);
        /*tasks = taskService.getTasks(projectName, user);
        model.addAttribute("tasks", tasks);*/

        return "redirect:/myProjects";
    }


    @GetMapping("/myProjects")
    public String myProjects( Model model, WebRequest request) {
        user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        //Display everything
        projects = projectService.getProjects(user);
        tasks = taskService.getTasks(projectName, user); //Tillæg
        //subtasks = subtaskService.getSubtasks(taskName, user);
        model.addAttribute("loopList", projects);
        model.addAttribute("tasks", tasks); //Tillæg
        model.addAttribute("subtasks", subtasks);


        model.addAttribute("userName", user.getName());
        return "project/project";// endpoint change
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

    @GetMapping("/remove/{id}")
    public String deleteProduct(@PathVariable(value = "id") String id, Model model) {
        projectService.deleteProject(id, user);
        projects = projectService.getProjects(user);
        model.addAttribute("loopList", projects);
        return "redirect:/addproduct";
    }

    //TASK
    //Endpoint to dynamic display(loop) all task and display user information
    @GetMapping("/createTask")
    public String createTasks(WebRequest request, Model model) {
        user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        tasks = taskService.getTasks(projectName, user);
        model.addAttribute("userName", user.getName());
        model.addAttribute("tasks", tasks);
        //return "/project/createTask";
        return "/project/project"; //change to endpoint
    }


    //Endpoint to pass and connect data(id) from myProjects site to createtask site.
    @GetMapping("/update/{id}")
    public String updateProject(@PathVariable(value = "id") String id, WebRequest request, Model model) {
        System.out.println(id);
        System.out.println("kommer den her ind??");
        projectName = id;
        user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        model.addAttribute("tasks", tasks);
        tasks = taskService.getTasks(id, user);
        return "redirect:/myProjects"; //Lige nu kan den ikke finde ud af at redirecte rigtigt
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

    //SUBTASK
    //Endpoint to dynamic display(loop) all task and display user information
    @GetMapping("/createSubtask")
    public String createSubtasks(WebRequest request, Model model) {
        user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        subtasks = subtaskService.getSubtasks(taskName, user);
        model.addAttribute("userName", user.getName());
        model.addAttribute("subtask", subtasks);
        return "/project/createSubTask";
    }

    //Endpoint to pass and connect data(id) from myProjects site to createtask site.
    @GetMapping("/rerun/{id}")
    public String updateSubtask(@PathVariable(value = "id") String id, WebRequest request, Model model) {
        System.out.println("subtask " + id);
        taskName = id;
        user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        subtasks = subtaskService.getSubtasks(id, user);
        model.addAttribute("subtasks", subtasks);
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
        return "redirect:/createSubtask";
    }
}
