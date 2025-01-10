package finalProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("chat")
public class ChatController {
    @RequestMapping("test")
    public String test() {
        return "thymeleaf/chat/ChatTest";
    }
}
