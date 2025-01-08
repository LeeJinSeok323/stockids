package finalProject.service.admin;

import finalProject.command.AlertCommand;
import finalProject.domain.AlertDTO;
import finalProject.domain.AuthInfoDTO;
import finalProject.domain.MemberDTO;
import finalProject.mapper.AdminMapper;
import finalProject.mapper.AlertMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AlertWriteService {
    @Autowired
    AlertMapper alertMapper;
    @Autowired
    AdminMapper adminMapper;
    public void execute(AlertCommand alertCommand, HttpSession session){
    AlertDTO dto = new AlertDTO();
    dto.setAlertNum(alertCommand.getAlertNum());
    dto.setAlertContents(alertCommand.getAlertContents());
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        String adminId = auth.getUserId();
        String adminNum = adminMapper.getAdminNum(adminId);
        dto.setAdminNum(adminNum);

        alertMapper.alertInsert(dto);
    }
}
