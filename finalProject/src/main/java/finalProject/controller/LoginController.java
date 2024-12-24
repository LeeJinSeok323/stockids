package finalProject.controller;

import finalProject.service.login.UserLoginService;
import finalProject.command.LoginCommand;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    UserLoginService userLoginService;
    @PostMapping("login")
    public String login(LoginCommand loginCommand, BindingResult result, HttpSession session ) {
        userLoginService.execute(loginCommand, session, result);
        if (result.hasErrors()) {
            return "thymeleaf/login";
        }
        // 로그인 성공 시 이전 페이지로 리다이렉트
        String prevPage = (String) session.getAttribute("prevPage");
        if (prevPage == null || prevPage.equals("/login")) {
            return "redirect:/";
        }
        return "redirect:" + prevPage; // 이전 페이지로 리다이렉트
    }

    @GetMapping("")
    public String showLoginPage(Model model) {
        // 로그인 페이지를 위한 빈 LoginCommand 객체 추가
        if (!model.containsAttribute("loginCommand")) {
            model.addAttribute("loginCommand", new LoginCommand());
        }
        return "thymeleaf/login"; // 로그인 페이지 반환
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/"; // 로그아웃 후 메인 페이지로 이동
    }
}
