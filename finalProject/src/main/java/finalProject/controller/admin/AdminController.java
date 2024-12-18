package finalProject.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import finalProject.command.EmployeeCommand;
import finalProject.sevice.AutoNumService;
import finalProject.sevice.admin.AdminDetailService;
import finalProject.sevice.admin.AdminInsertService;
import finalProject.sevice.admin.AdminListService;
import finalProject.sevice.admin.AdminUpdateService;
import finalProject.sevice.admin.AdminDeleteService;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AutoNumService autoNumService;
    @Autowired
    AdminInsertService adminInsertService;
    @Autowired
    AdminListService adminListService;
    @Autowired
    AdminDeleteService adminDeleteService;
    @Autowired
    AdminDetailService adminDetailService;
    @RequestMapping(value="adminList", method=RequestMethod.GET)
    //페이징과 검색을 위한 코드를 추가하겠습니다.
    public String admList(
            @RequestParam(value="page", required = false, defaultValue = "1" ) int page,
            @RequestParam(value="searchWord" , required = false) String searchWord,
            Model model) {
        //직원 목록을 가져오도록 해보자.
        adminListService.execute(searchWord, page,model);
        return "thymeleaf/admin/adminList";
    }
    @GetMapping("admRegist")
    public String form(Model model ) {
        String autoNum = autoNumService.execute("adm_", "adm_num", 5, "admin");
        AdminCommand  adminCommand = new AdminCommand();
        adminCommand.setadmNum(autoNum);
        model.addAttribute("adminCommand", adminCommand);
        return "thymeleaf/admin/adminForm";
    }
    @RequestMapping(value="admRegist", method=RequestMethod.POST)

    public String form(@Validated AdminCommand adminCommand,BindingResult result , Model model ) {

        if(result.hasErrors()) {

            return "thymeleaf/admin/adminForm";
        }
        // 모두 입력을 했다면 비밀번호확인 검사
        if (!adminCommand.isAdmPwEqualsAdmPwCon()) {
            System.out.println("비밀번호 확인이 다릅니다.");
            //틀렸으면 다시 employeeForm페이지가 열리게 합니다.
            return "thymeleaf/admin/adminForm";
        }
        //모든 오류가 없으면 디비에 저장
        adminInsertService.execute(adminCommand);
        return "redirect:adminList";
    }

    @PostMapping("admDelete")
    public String membersDelete(
            @RequestParam(value="admDels") String admDel []) {
        adminDeleteService.execute(admDel);
        return "redirect:adminList";
    }
    @RequestMapping(value="adminDetail",method=RequestMethod.GET)
    public String adminDetail(@RequestParam(value = "admNum") String admNum, Model model) {
        adminDetailService.execute(admNum, model);
        return "thymeleaf/admin/admDetail";
    }
    @RequestMapping(value = "admModify", method = RequestMethod.GET)
    public String adminUpdate(@RequestParam(value = "admNum") String admNum, Model model) {
        adminDetailService.execute(admNum, model);
        return "thymeleaf/admin/adminUpdate";
    }
    //데이터를 디비에 저장하기 위해 service를 만들어 줍니다.
    @Autowired
    AdminUpdateService adminUpdateService;
    @RequestMapping(value = "admModify", method = RequestMethod.POST)
    public String adminUpdate(@Validated AdminCommand adminCommand, BindingResult result) {
        // 유효성 검사하기 위해서 validated를 해줍니다.그리고 BindingResult 을 추합니다.
        // 오류 체크를 합니다.
        if (result.hasErrors()) {
            /// 오류가 있으면 현 페이지가 열리게 합니다.
            return "thymeleaf/admin/adminUpdate";
        }
        adminUpdateService.execute(adminCommand);
        //수정하고 직원상세페이지로
        return "redirect:adminDetail?admNum=" + adminCommand.getAdmNum();
    }

    @Autowired
    AdminDeleteService adminDeleteService;
    @GetMapping("admDelete")
    public String adminDelete(@RequestParam(value = "admNum") String admNum) {

        adminDeleteService.execute(admNum);

        return "redirect:adminList";

    }
}
