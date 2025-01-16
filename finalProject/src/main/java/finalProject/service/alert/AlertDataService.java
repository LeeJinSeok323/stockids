package finalProject.service.alert;

import finalProject.domain.AlertDTO;
import finalProject.domain.AlertListDTO;
import finalProject.domain.AuthInfoDTO;
import finalProject.mapper.AlertMapper;
import finalProject.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertDataService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    AlertMapper alertMapper;

    public List<AlertListDTO> execute(HttpSession session){
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        String memberNum = userMapper.getUserNumById(auth.getUserId());
        List<AlertListDTO> dto = alertMapper.getAlertList(memberNum);
        return dto;
    }
}
