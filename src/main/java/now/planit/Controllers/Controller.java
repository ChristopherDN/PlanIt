package now.planit.Controllers;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;

@org.springframework.stereotype.Controller


public class Controller {


  @GetMapping("/")
  public String index(HttpSession session) {
    if (session.getAttribute("user")!= null){
      return "redirect:/frontpage";
    }
    return "index";
  }

  @GetMapping("/login")
  public String login() {
    return "login/login";
  }


  //these are just endpoints ready to be used


  @GetMapping("/about")
  public String about() {
    return "info/about";
  }


  @GetMapping("/project")
  public String project() {
    return "project/project";
  }

}
