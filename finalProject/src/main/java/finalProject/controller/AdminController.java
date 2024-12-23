package finalProject.controller;

import finalProject.command.AdminCommand;
import finalProject.domain.AdminDTO;
import finalProject.service.admin.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user1")
public class AdminController {
    @Autowired
    AdminRegistService adminRegistService;
    @Autowired
    AdminValidationService adminValidationService;
    @Autowired
    AdminListService adminListService;
    @Autowired
    AdminInfoService adminInfoService;
    @Autowired
    AdminEditService adminEditService;
    @Autowired
    AdminDeleteService adminDeleteService;
    @RequestMapping("home")
    public String home() {
        return "thymeleaf/admin/adminHome";
    }
    @GetMapping("form")
    public String admin(AdminCommand adminCommand, Model model) {
        model.addAttribute("adminCommand", adminCommand);
        return "thymeleaf/admin/adminForm";
    }
    @PostMapping("form")
    public String regist(@Validated AdminCommand adminCommand, BindingResult bindingResult, Model model ){
        String command = adminValidationService.execute(adminCommand, bindingResult, model);
        if(!command.equals("pass")){
            return command;
        }
        adminRegistService.execute(adminCommand);
        return "redirect:/user1/home";
    }


    @GetMapping("list")
    public @ResponseBody ResponseEntity<List<AdminDTO>> list() {
        List<AdminDTO> list = adminListService.execute();
        return ResponseEntity.ok(list);
    }
    @GetMapping("info")
    public @ResponseBody ResponseEntity<AdminDTO> info(String adminNum) {
        AdminDTO dto = adminInfoService.execute(adminNum);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("edit")
    public @ResponseBody String edit(@RequestBody AdminCommand adminCommand) {
        return adminEditService.execute(adminCommand);
    }

    // TODO: Check Auth Valiable
    @PostMapping("del")
    public @ResponseBody String del(@RequestParam String adminNum, HttpServletRequest req) {
        return adminDeleteService.execute(adminNum, req);
    }

}
