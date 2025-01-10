package finalProject.service.inquire;

import finalProject.command.InquireCommand;
import finalProject.domain.InquireDTO;
import finalProject.mapper.InquireMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InquireUpdateService {
    @Autowired
    InquireMapper inquireMapper;
    public void execute(InquireCommand inquireCommand){
        InquireDTO inquireDTO = new InquireDTO();
        inquireDTO.setInquireNum(inquireCommand.getInquireNum());
        inquireDTO.setMemberNum(inquireCommand.getMemberNum());
        inquireDTO.setInquireSubject(inquireCommand.getInquireSubject());
        inquireDTO.setInquireContents(inquireCommand.getInquireContents());
        inquireMapper.inquireUpdate(inquireDTO);
    }
}
