package finalProject.service.member;

import finalProject.domain.MemberDTO;
import finalProject.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class MemberAutoNumService {
    @Autowired
    MemberMapper memberMapper;

    public void execute(Model model) {
        String memberNum = memberMapper.memberAutoNum();
        MemberDTO dto = new MemberDTO();
        dto.setMemberNum(memberNum);  // memberNum 설정
        model.addAttribute("memberCommand", dto);
    }
}