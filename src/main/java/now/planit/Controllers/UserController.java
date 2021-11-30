package now.planit.Controllers;

import now.planit.Domain.Models.User;
import now.planit.Domain.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {
  UserService userService = new UserService();
  User user;

  @GetMapping("/registerUser")
  public String createUser() {
    return "register/register";
  }

  @GetMapping("/logged")
  public String loggedIn() {
    return "login/logged";
  }

  @PostMapping("/register")
  public String register(WebRequest request) {
    userService.registerUser(
        request.getParameter("name"),
        request.getParameter("email"),
        request.getParameter("password"));
    return "login/login";
  }

  @PostMapping("/validateLogin")
  public String validateLogin(WebRequest request, HttpSession session, Model model) {
    user = userService.validateLogin(
        request.getParameter("mail"),
        request.getParameter("password"));

    //Set Session to user, validate user is not null.
    if (session.getAttribute("user") == null) {
      if (user != null) {
        model.addAttribute("user", user);
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
        return "redirect:/myProjects";
      }
    }
    return "login/login";
  }

  @GetMapping("/logout")
  public String logOut(HttpSession session) {
    session.invalidate();
    user = null;
    return "redirect:/";
  }


}

