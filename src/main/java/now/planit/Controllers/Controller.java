package now.planit.Controllers;

import now.planit.Domain.Models.User;
import now.planit.Domain.Services.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import javax.servlet.http.HttpSession;

@org.springframework.stereotype.Controller


public class Controller {
  UserService userService = new UserService();


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

}
