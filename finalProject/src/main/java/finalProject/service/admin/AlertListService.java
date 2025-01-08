package finalProject.service.admin;

import finalProject.domain.AlertDTO;
import finalProject.domain.MemberDTO;
import finalProject.domain.StartEndPageDTO;
import finalProject.domain.TitleDTO;
import finalProject.mapper.AlertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
        return alertMapper.getAllAlerts();}

}
