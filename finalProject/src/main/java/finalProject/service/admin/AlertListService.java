package finalProject.service.admin;

import finalProject.domain.*;
import finalProject.domain.alert.AlertMemberDTO;
import finalProject.mapper.AlertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AlertListService {
    @Autowired
    AlertMapper alertMapper;
    @Autowired
    StartEndPageService startEndPageService;

    String searchWord;
    public void execute(String searchWord, Model model, int page){
        if(searchWord!=null){
            this.searchWord = searchWord.trim();
        }
        StartEndPageDTO sepDTO = startEndPageService.execute(page, 10, this.searchWord);
        List<AlertDTO> list = alertMapper.allSelect(sepDTO);
        int count = alertMapper.alertCount(this.searchWord);
        startEndPageService.execute(page,10,count,this.searchWord,list,model);
    }

    public List<AlertDTO> getAllAlerts() {
        return alertMapper.getAllAlerts();
    }

    public String getAlertContent(String alertNum){
        return alertMapper.getAlertContent(alertNum);
    }

    public List<AlertMemberDTO> getAllMembers(String alertNum) {
        List<AlertListDTO> alDTO = alertMapper.getAllAlertList(alertNum);
        List<AlertMemberDTO> memDTO = alertMapper.getAllMembers();
        List<AlertMemberDTO> memberDTO = new ArrayList<>();

        for(AlertMemberDTO mdto : memDTO) {
            boolean matchFound = false;
            for(AlertListDTO dto : alDTO) {
                if(mdto.getMemberNum().equals(dto.getMemberNum())) {
                    matchFound = true;
                    break;
                }
            }
            if (!matchFound) {
                memberDTO.add(mdto);
            }
        }
        return memberDTO;
    }

}
