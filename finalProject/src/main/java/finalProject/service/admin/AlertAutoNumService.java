package finalProject.service.admin;


import finalProject.command.AlertCommand;
import finalProject.command.TitleCommand;
import finalProject.mapper.AlertMapper;
import finalProject.mapper.TitleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AlertAutoNumService {
    @Autowired
    AlertMapper alertMapper;
    public void execute(Model model){
        // padding 값: 번호 자리수 설정 (2 → 01, 02, 03 ...)
        int padding = 2; // 번호 자리수 설정
        String alertNum = alertMapper.alertAutoNum("ALERT", "ALERT_NUM", "alert", padding);

        AlertCommand alertCommand = new AlertCommand();
        alertCommand.setAlertNum(alertNum);
        model.addAttribute("alertCommand", alertCommand);

    }
}
