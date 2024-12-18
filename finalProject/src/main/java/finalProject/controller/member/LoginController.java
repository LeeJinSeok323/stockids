package finalProject.controller.member;

import finalProject.command.member.LoginCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    UserLoginService userLoginService;

    @PostMapping("")
    public String login(@Validated LoginCommand loginCommand, BindingResult result, HttpSession session, HttpServletResponse response) {
        userLoginService.execute(loginCommand, session, result, response);

        if (result.hasErrors()) {
            return "thymeleaf/login";
        }

        // 로그인 성공 시 이전 페이지로 리다이렉트
        String prevPage = (String) session.getAttribute("prevPage");
        if (prevPage == null || prevPage.equals("/login")) {
            return "redirect:/";
        }
        return "redirect:" + prevPage;
    }

    @GetMapping("")
    public String showLoginPage(Model model) {
        if (!model.containsAttribute("loginCommand")) {
            model.addAttribute("loginCommand", new LoginCommand());
        }
        return "thymeleaf/login";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
