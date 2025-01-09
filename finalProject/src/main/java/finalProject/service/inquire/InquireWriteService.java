package finalProject.service.inquire;

import finalProject.command.InquireCommand;
import finalProject.domain.AuthInfoDTO;
import finalProject.domain.InquireDTO;
import finalProject.domain.MemberDTO;
import finalProject.mapper.InquireMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class InquireWriteService {
    @Autowired
    InquireMapper inquireMapper;
    public void execute(InquireCommand inquireCommand, HttpSession session){
    InquireDTO inquireDTO = new InquireDTO();
    inquireDTO.setInquireNum(inquireCommand.getInquireNum());
    inquireDTO.setMemberNum(inquireCommand.getMemberNum());
    inquireDTO.setInquireSubjects(inquireCommand.getInquireSubjects());
    inquireDTO.setInquireContents(inquireCommand.getInquireContents());
    AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");

        String userId = auth.getUserId();

        // MEMBER 테이블에서 memberName과 memberNum 가져오기
        MemberDTO memberInfo = inquireMapper.getMemberInfo(userId);
        inquireDTO.setMemberNum(memberInfo.getMemberNum());
        inquireDTO.setMemberName(memberInfo.getMemberName());

        // 이제 등록 처리
        inquireMapper.insertInquire(inquireDTO);

    }
}
