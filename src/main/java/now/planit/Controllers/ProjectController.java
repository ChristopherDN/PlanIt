package now.planit.Controllers;

import now.planit.Data.Repo.RepoProject;
import now.planit.Domain.Models.Project;
import now.planit.Domain.Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Controller
public class ProjectController {

    User user;
    Project project = new Project( "test1", "idag", "imorgen", "1000");
    //Test Arraylist + hardcoded values
    ArrayList<Project> projectArrayList = new ArrayList<>();
    String name = "Chris";

    @GetMapping("/createproject")
    public String createproject(Model model) {
        //Test Arraylist
        System.out.println(project);
        projectArrayList.add(project);
        model.addAttribute("loopList", projectArrayList );
        model.addAttribute("project", project);
        model.addAttribute("username", name);
        return "project/createproject";
    }

    @GetMapping("/frontpage")
    public String showWishlists(Model model, HttpSession session) {
        user = (User) session.getAttribute("user");

        return "frontpage";
    }

    @PostMapping("/createWishlist")
    public String createWishlist(WebRequest request, HttpSession session) {
        String event = request.getParameter("event");
        user = (User) session.getAttribute("user");
        //wishlistService.createWishlist(user, event);
        return "redirect:/frontpage";
    }

    @GetMapping("/removeWishlist/{id}")
    public String deleteWishList(@PathVariable(value = "id") String id) {
        //wishlistService.deleteWishlist(user, id);
        return "redirect:/frontpage";
    }








}
