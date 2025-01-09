package finalProject.service.inquire;

import finalProject.domain.InquireDTO;
import finalProject.domain.MemberDTO;
import finalProject.domain.StartEndPageDTO;
import finalProject.mapper.InquireMapper;
import finalProject.service.admin.StartEndPageService;
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

   public void execute(Integer page,String searchWord, Model model, String userId){
       Integer limit = 10;
       MemberDTO memberInfo = inquireMapper.getMemberInfo(userId);
       StartEndPageDTO sepDTO = startEndPageService.execute(page, limit, searchWord);
       List<InquireDTO> list = inquireMapper.inquireSelectList(sepDTO);
       for (InquireDTO inquire : list) {
           MemberDTO author = inquireMapper.getMemberInfoByNum(inquire.getMemberNum());
           if (author != null) {
               inquire.setMemberName(author.getMemberName());
           }
       }

       Integer count = inquireMapper.inquireCount(sepDTO);
       startEndPageService.execute(page, limit, count, searchWord, list, model);
       model.addAttribute("memberName", memberInfo.getMemberName());
   }
}
