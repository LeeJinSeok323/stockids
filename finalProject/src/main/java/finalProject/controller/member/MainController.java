package finalProject.controller.member;

import finalProject.command.login.LoginCommand;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
@Controller
public class MainController {
    @RequestMapping("/")
    public String index(){
        return "thymeleaf/index";
    }
}
