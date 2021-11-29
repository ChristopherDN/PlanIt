package now.planit.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ProjectController {



    @GetMapping("/createproject")
    public String createproject() {
        return "project/createproject";
    }






}
