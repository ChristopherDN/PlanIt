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
  User user;

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

  @GetMapping("/createUser")
  public String createUser() {
    return "createUser";
  }

  @PostMapping("/register")
  public String register(WebRequest request) {
    userService.registerUser(
        request.getParameter("name"),
        request.getParameter("email"),
        request.getParameter("password"));
    return "login";
  }


  @PostMapping("/frontpage")
  public String validateLogin(WebRequest request, HttpSession session, Model model) {
    //user = userService.validateLogin(
        //request.getParameter("user"),
        //request.getParameter("password"));

    //Set Session to user, validate user is not null.
    if (session.getAttribute("user") == null) {
      if (user != null) {
        model.addAttribute("user", user);
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
        return "redirect:/frontpage";
      }
    }
    return "login";
  }

  @GetMapping("/logout")
  public String logOut(HttpSession session) {
    session.invalidate();
    user = null;
    return "redirect:/";
  }
  //these are just endpoints ready to be used


  @GetMapping("/about")
  public String about() {
    return "info/about";
  }



  @GetMapping("/register")
  public String register() {
  return "register/register";
}






}
