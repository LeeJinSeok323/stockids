package finalProject.service.member;

import finalProject.domain.MemberDTO;
import finalProject.domain.TitleDTO;
import finalProject.mapper.MemberMapper;
import finalProject.service.admin.TitleListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class MemberListService {
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    TitleListService titleListService;

    public void execute(Model model, String searchWord){
     List<MemberDTO> list = memberMapper.selectAllWithTitle(searchWord);
     List<TitleDTO> title = titleListService.getAllTitles();
     model.addAttribute("dtos", list);
     model.addAttribute("searchWord", searchWord);
     model.addAttribute("title", title);
    }
    public void updateMemberTitle(String memberNum, String titleNum) {
        memberMapper.updateMemberTitle(memberNum, titleNum);
        memberMapper.updateMemberInMemberTable(memberNum, titleNum);
    }

}
