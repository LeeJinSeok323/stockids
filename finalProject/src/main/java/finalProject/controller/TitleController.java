package finalProject.controller;

import finalProject.command.AdminCommand;
import finalProject.command.TitleCommand;
import finalProject.domain.TitleDTO;
import finalProject.mapper.TitleMapper;
import finalProject.service.admin.TitleAutoNumService;
import finalProject.service.admin.TitleDeleteService;
import finalProject.service.admin.TitleListService;
import finalProject.service.admin.TitleWriteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user1")
public class TitleController {
    @Autowired
    TitleAutoNumService titleAutoNumService;
    @Autowired
    TitleListService titleListService;
    @RequestMapping(value="titleList",method= RequestMethod.GET)
    public String TitleList(
            @RequestParam(value="searchWord",required=false)String searchWord,
            @RequestParam(value="page",required=false,defaultValue="1") int page,
            Model model){
        titleListService.execute(searchWord,model,page);
        return "thymeleaf/admin/titleList";
    }
    @GetMapping("titleWrite")
    public String titleWrite(Model model){
        titleAutoNumService.execute(model);
        return "thymeleaf/admin/titleWrite";
    }
@Autowired
TitleWriteService titleWriteService;
    @RequestMapping(value="titleWrite", method=RequestMethod.POST)
    public String saveTitle(TitleCommand titleCommand, BindingResult result,
                            HttpSession session) {
        // 데이터 저장 로직
        titleWriteService.execute(titleCommand,session);
        return "redirect:/user1/titleWrite";
    }

    @Autowired
    TitleDeleteService titleDeleteService;
    @PostMapping("titleDelete")
    public String titleDelete(
            @RequestParam(value="titleDels") String titleDels[]){
        titleDeleteService.execute(titleDels);
        return "redirect:/user1/titleList";

    }


}
