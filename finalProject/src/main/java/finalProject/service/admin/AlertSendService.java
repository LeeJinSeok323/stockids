package finalProject.service.admin;

import finalProject.domain.AlertListDTO;
import finalProject.mapper.AlertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertSendService {

    @Autowired
    AlertMapper alertMapper;

    public void execute(String alertNum ,List<String> selectedMembers){
        for (String memberNum : selectedMembers) {
            AlertListDTO alert = new AlertListDTO();
            System.out.println(alertNum);
            System.out.println(memberNum);
            alert.setAlertNum(alertNum);
            alert.setMemberNum(memberNum);
            alertMapper.alertSend(alert);
        }
    }
}
