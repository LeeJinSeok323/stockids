package finalProject.controller;

import finalProject.domain.AuthInfoDTO;
import finalProject.service.login.SessionCheckService;
import finalProject.service.login.UserLoginService;
import finalProject.command.LoginCommand;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    UserLoginService userLoginService;
    @Autowired
    SessionCheckService sessionCheckService;

    @PostMapping("login")
    public String login(LoginCommand loginCommand, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
        userLoginService.execute(loginCommand, session, result);
        if (result.hasErrors()) {
            return "thymeleaf/login";
        }
        // 로그인 성공 시 AuthInfoDTO 세션에 저장
        AuthInfoDTO authInfo = (AuthInfoDTO) session.getAttribute("auth");
        if (authInfo == null) {
            return "thymeleaf/login"; // auth 정보가 없으면 로그인 페이지로
        }

        if (authInfo != null) {
            redirectAttributes.addFlashAttribute("isLoggedIn", true);
            redirectAttributes.addFlashAttribute("isAdmin", authInfo.isAdmin());
        }

        // 모든 로그인 성공 시 adminhome.html로 리다이렉트
        return "redirect:/user1/home";
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
        session.invalidate();
        return "redirect:http://localhost:3000";
    }

    //React 로그인 부분
    @GetMapping("react")
    public String showLoginPageReact() {
        return "thymeleaf/react/LoginForReact";
    }

    @PostMapping("react/login")
    public String reactLogin(LoginCommand loginCommand, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        userLoginService.execute(loginCommand, session, result);
        if (result.hasErrors()) {
            return "thymeleaf/react/reactLogin";
        }
        AuthInfoDTO authInfo = (AuthInfoDTO) session.getAttribute("auth");
        if (authInfo == null) {
            return "thymeleaf/react/reactLogin";
        }
        if (authInfo != null) {
            redirectAttributes.addFlashAttribute("isLoggedIn", true);
            redirectAttributes.addFlashAttribute("isAdmin", authInfo.isAdmin());
            return "thymeleaf/react/reactLogin";
        }
        return "thymeleaf/react/reactLogin";
    }

    @GetMapping("react/logout")
    @ResponseBody
    public void reactLogout(HttpSession session) {
        session.invalidate();
    }

    ////

    @GetMapping("/sessionCheck")
    @ResponseBody
    public boolean memberSessionCheck(HttpSession session) {
        boolean check = sessionCheckService.execute(session);
        return check;
    }

    @GetMapping("/user1/home")
    public String userHome(HttpSession session, Model model) {
        AuthInfoDTO authInfo = (AuthInfoDTO) session.getAttribute("auth");
        if (authInfo != null) {
            model.addAttribute("isAdmin", authInfo.isAdmin());
            model.addAttribute("isLoggedIn", true);
            return "thymeleaf/adminhome";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/user1/titleWrite")
    public String titleWrite(HttpSession session, Model model) {
        AuthInfoDTO authInfo = (AuthInfoDTO) session.getAttribute("auth");
        if (authInfo != null && authInfo.isAdmin()) {
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("isAdmin", true);
            return "thymeleaf/titleWrite";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/user1/login")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @Controller
    public class MainController {
        @GetMapping("/")
        public String mainPage(HttpSession session, Model model) {
            AuthInfoDTO authInfo = (AuthInfoDTO) session.getAttribute("auth");
            boolean isLoggedIn = authInfo != null;
            model.addAttribute("isLoggedIn", isLoggedIn);
            if (isLoggedIn) {
                model.addAttribute("isAdmin", authInfo.isAdmin());
            }
            return "thymeleaf/index";
        }
    }

    @ControllerAdvice
    public class GlobalModelAttributes {
        @ModelAttribute
        public void addAttributes(Model model, HttpSession session) {
            AuthInfoDTO authInfo = (AuthInfoDTO) session.getAttribute("auth");
            boolean isLoggedIn = authInfo != null;
            model.addAttribute("isLoggedIn", isLoggedIn);
            if (isLoggedIn) {
                model.addAttribute("isAdmin", authInfo.isAdmin());
            }
        }
    }

    @ResponseBody
    @GetMapping("/session/data")
    public ResponseEntity<Boolean> fetchSessionPosition(HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        Boolean isAdmin = auth.isAdmin();
        return ResponseEntity.ok(isAdmin);
    }
}
