package finalProject.controller;


import finalProject.service.member.MemberAutoNumService;
import finalProject.service.member.MemberInsertService;
import finalProject.command.MemberCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("member")
public class MemberController {
    @Autowired
    MemberInsertService memberInsertService;
    @Autowired
    MemberAutoNumService memberAutoNumService;
    @RequestMapping(value="memberList")
    public String list(){
        return "thymeleaf/member/memberlist";
    }
    @RequestMapping(value="memberRegist", method= RequestMethod.GET)
    public String form(Model model){
        memberAutoNumService.execute(model);
        return "thymeleaf/member/memberForm";
    }

    @RequestMapping(value="memberRegist", method=RequestMethod.POST)
    public String form(MemberCommand memberCommand, BindingResult result){
        if(result.hasErrors()){
            return "thymeleaf/member/memberForm";
        }
        if(!memberCommand.isMemberPwEqualMemberPwCon()){
           return "thymeleaf/member/memberForm";
        }
        else {
            memberInsertService.execute(memberCommand);
            return "redirect:memberList";
        }
        }


}
