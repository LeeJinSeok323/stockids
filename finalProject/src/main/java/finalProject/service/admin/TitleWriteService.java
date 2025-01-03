package finalProject.service.admin;

import finalProject.command.TitleCommand;
import finalProject.domain.AuthInfoDTO;
import finalProject.domain.TitleDTO;
import finalProject.mapper.AdminMapper;
import finalProject.mapper.TitleMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TitleWriteService {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    TitleMapper titleMapper;
    public void execute(TitleCommand titleCommand, HttpSession session){
    TitleDTO dto = new TitleDTO();
    dto.setTitleNum(titleCommand.getTitleNum());
    dto.setTitleContents(titleCommand.getTitleContents());
    dto.setTitleName(titleCommand.getTitleName());
    AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
    String adminId = auth.getUserId();
    String adminNum = adminMapper.getAdminNum(adminId);
    dto.setAdminNum(adminNum);

    titleMapper.titleInsert(dto);

    }
}
