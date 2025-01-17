package finalProject.service.alert;

import finalProject.domain.AuthInfoDTO;
import finalProject.mapper.AlertMapper;
import finalProject.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertCheckService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AlertMapper alertMapper;

    public void execute(String alertNum, HttpSession session){
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        String memberNum = userMapper.getUserNumById(auth.getUserId());
        alertMapper.alertCheck(memberNum, alertNum);
    }
}
