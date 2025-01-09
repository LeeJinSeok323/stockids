package finalProject.service.inquire;

import finalProject.domain.AuthInfoDTO;
import finalProject.domain.InquireDTO;
import finalProject.domain.MemberDTO;
import finalProject.domain.StartEndPageDTO;
import finalProject.mapper.InquireMapper;
import finalProject.mapper.MemberMapper;
import finalProject.service.admin.StartEndPageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class InquireListService {
    @Autowired
    InquireMapper inquireMapper;
    @Autowired
    StartEndPageService startEndPageService;
    @Autowired
    MemberMapper memberMapper;

    public void execute(Integer page, String searchWord, Model model, HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        String memberNum = null;
        if (!auth.isAdmin()) {
           memberNum = memberMapper.getMemberNum(auth.getUserId());
        }
        Integer limit = 10;
        StartEndPageDTO sepDTO = startEndPageService.execute(page, limit, searchWord);
        List<InquireDTO> list = inquireMapper.inquireSelectList(sepDTO, memberNum);
        Integer count = inquireMapper.inquireCount(sepDTO, memberNum);
        startEndPageService.execute(page, limit, count, searchWord, list, model);
    }
}
