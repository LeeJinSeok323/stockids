package finalProject.service.inquire;

import finalProject.domain.AuthInfoDTO;
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

   public void execute(Integer page,String searchWord, Model model){
       Integer limit = 10;
       StartEndPageDTO sepDTO = startEndPageService.execute(page, limit, searchWord);
       List<InquireDTO> list = inquireMapper.inquireSelectList(sepDTO);
       Integer count = inquireMapper.inquireCount(sepDTO);
       startEndPageService.execute(page, limit, count, searchWord, list, model);
   }
}
