package finalProject.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register")
public class MemberJoinController {
    @Autowired
    MemberJoinService memberJoinService;
    @ModelAttribute
    public UserCommand userCommand() {
        return new UserCommand();
    }

    @RequestMapping("userAgree")
    public String agree() {
        return "thymleaf/memberJoin/agree";

    }
    @GetMapping("userWrite")
    // 뒤에 있는 주소만 적음
    public String userWrite() {

        return "thymeleaf/memberJoin/userForm";
    }
    @PostMapping("userWrite")
    public String userWrite1(@Validated UserCommand userCommand
            ,BindingResult result) {
        if(result.hasErrors()) {
            return "thymeleaf/memberJoin/userForm";
        }
        return "thymeleaf/memberJoin/welcome";
    }
}
