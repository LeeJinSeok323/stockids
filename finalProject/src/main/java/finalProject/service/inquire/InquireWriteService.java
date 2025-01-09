package finalProject.service.inquire;

import finalProject.command.InquireCommand;
import finalProject.domain.InquireDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

@Controller
public class InquireWriteService {
    public void execute(InquireCommand inquireCommand, HttpSession session){
    InquireDTO inquireDTO = new InquireDTO();
    inquireDTO.setInquireNum(inquireCommand.getInquireNum());


    }
}
