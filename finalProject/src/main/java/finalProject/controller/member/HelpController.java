package finalProject.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("help")
public class HelpController {
    @Autowired
    FindIdService findIdService;
    @Autowired
    FindPwService findPwService;
    @RequestMapping(value="findId", method = RequestMethod.GET)
    public String findId() {
        return "thymeleaf/help/findId";
    }
    @RequestMapping(value="findId", method = RequestMethod.POST)
    public String findId(
            @RequestParam("userNickname")String userNickname,
            Model model) {
        findIdService.execute(userNickname, model);
        return "thymeleaf/help/findIdOk";
    }
    @GetMapping("findPassword")
    public String findPassword() {
        return "thymeleaf/help/findPw";
    }
    @PostMapping("findPassword")
    public String findPassword(String userId,String userNickname,Model model) {
        findPwService.execute(userId, userNickname,model);
        return "thymeleaf/help/findPwOk";
    }
}