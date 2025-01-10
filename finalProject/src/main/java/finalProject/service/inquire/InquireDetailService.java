package finalProject.service.inquire;

import finalProject.domain.InquireDTO;
import finalProject.mapper.InquireMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class InquireDetailService {
    @Autowired
    InquireMapper inquireMapper;
    public void execute(Model model, String inquireNum){
        InquireDTO inquireDTO = inquireMapper.inquireSelectOne(inquireNum);
        String inquireAuthorld = inquireMapper.findAuthorldByInquireNum(inquireNum);
        model.addAttribute("inquireCommand", inquireDTO);
        model.addAttribute("inquireAuthorld", inquireAuthorld);

    }
}
