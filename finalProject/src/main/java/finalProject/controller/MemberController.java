package finalProject.controller;


import finalProject.domain.AuthInfoDTO;
import finalProject.mapper.MemberMapper;
import finalProject.service.member.MemberAutoNumService;
import finalProject.service.member.MemberInsertService;
import finalProject.command.MemberCommand;
import finalProject.service.member.MemberListService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user1")
public class MemberController {
    @Autowired
    MemberInsertService memberInsertService;
    @Autowired
    MemberAutoNumService memberAutoNumService;
    @Autowired
    MemberListService memberListService;
    @Autowired
    MemberMapper memberMapper;

    @RequestMapping(value="memberList")
    public String list(
            @RequestParam(value ="searchWord", required=false) String searchWord,
            Model model){
        memberListService.execute(model, searchWord);
        return "thymeleaf/member/memberlist";
    }

    // 회원가입 폼 보기
    @RequestMapping(value="memberRegist", method=RequestMethod.GET)
    public String form(Model model){
        memberAutoNumService.execute(model);  // 회원 번호 자동 생성 서비스 호출
        return "thymeleaf/member/memberForm";  // GET 요청 시 회원가입 폼 반환
    }

    // 회원가입 처리
    @RequestMapping(value="memberRegist", method=RequestMethod.POST)
    public String form(MemberCommand memberCommand, BindingResult result){
        if(result.hasErrors()){
            return "thymeleaf/member/memberForm";  // 에러가 있으면 폼으로 돌아감
        }
        if(!memberCommand.isMemberPwEqualMemberPwCon()){
            return "thymeleaf/member/memberForm";  // 비밀번호가 일치하지 않으면 폼으로 돌아감
        } else {
            memberInsertService.execute(memberCommand);  // 회원가입 처리
            return "redirect:/user1/home";  // 회원가입 후 홈 페이지로 리다이렉트
        }
    }

    @PostMapping("updateTitle")
    public String updateTitle(@RequestParam("memberNum") String memberNum,
                              @RequestParam("titleNum") String titleNum) {
        memberListService.updateMemberTitle(memberNum, titleNum);
        return "redirect:/user1/memberList";
    }

}
