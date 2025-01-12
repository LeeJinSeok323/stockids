package finalProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api")
public class ChatController {
    @RequestMapping("chat")
    public String test() {
        return "thymeleaf/chat/chatTest";
    }
}
