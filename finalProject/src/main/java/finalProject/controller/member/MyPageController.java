package finalProject.controller.member;

import finalProject.command.admin.AdminCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin")
public class MyPageController {
    @Autowired
    MemberMyInfoService memberMyInfoService;
    @Autowired
    MemberMyUpdateService memberMyUpdateService;
    @Autowired
    MemberPwUpdateService memberPwUpdateService ;
    @Autowired
    MemberDropService memberDropService ;
    @Autowired
    AdminInfoService adminInfoService;
    @Autowired
    AdminModifyService adminModifyService;
    @GetMapping("memberMyPage")
    public String memMyPage(HttpSession session,Model model) {
        memberMyInfoService.execute(session, model);
        return "thymeleaf/myPage/memberMyPage";
    }
    @GetMapping("memberUpdate")
    public String memberUpdate(HttpSession session,Model model) {
        memberMyInfoService.execute(session, model);
        return "thymeleaf/myPage/myModify";
    }
    @PostMapping("memberModify")
    public String memberModify(MemberCommand memberCommand
            , HttpSession session) {
        memberMyUpdateService.execute(memberCommand, session);
        return "redirect:memberMyPage";
    }
    @RequestMapping(value="memberPwModify" ,method = RequestMethod.GET)
    public String memberPwModify() {
        return  "thymeleaf/myPage/myNewPw";
    }
    @RequestMapping(value="memberPwPro" ,method = RequestMethod.POST)
    public String newPw(
            String oldPw, String newPw,HttpSession session
    ) {
        memberPwUpdateService.execute(oldPw, newPw, session);
        return "redirect:memberMyPage";
    }
    @GetMapping("memberDrop")
    public String memberDrop() {
        return "thymeleaf/myPage/memberDrop";
    }
    @PostMapping("memberDropOk")
    public String memberDropOk(String memberPw, HttpSession session) {
        memberDropService.execute(memberPw, session);
        return "redirect:/login/logout";
    }
    @GetMapping("admModify")
    public @ResponseBody Map<String, Object> admPage(HttpSession session, Model model) {
        Map<String, Object> map = adminInfoService.execute(session, model );
        return map;
    }
    @PostMapping("admModify")
    public String admModify(AdminCommand adminCommand, HttpSession session) {
        adminModifyService.execute(adminCommand, session);
        return "redirect:admloyeeMyPage";
    }

    @GetMapping("employeeMyPage")
    public String empMyPage() {
        return "thymeleaf/myPage/employeeInfo";
    }
    @PostMapping("empMyPage")
    public ModelAndView empMyPage(HttpSession session,Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("jsonView");
        employeeInfoService.execute(session, model);
        return mav;
    }
    @Autowired
    EmployeePwUpdateService employeePwUpdateService;
    @PostMapping("empPwPro")
    public String empPwPro(@RequestParam("oldPw") String oldPw
            ,String newPw, HttpSession session) {
        employeePwUpdateService.execute(oldPw, newPw, session);
        return "redirect:employeeMyPage";
    }
}
