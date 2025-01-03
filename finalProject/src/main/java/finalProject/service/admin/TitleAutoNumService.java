package finalProject.service.admin;

import finalProject.command.TitleCommand;
import finalProject.mapper.TitleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class TitleAutoNumService {
    @Autowired
    TitleMapper titleMapper;
    public void execute(Model model){
        // padding 값: 번호 자리수 설정 (2 → 01, 02, 03 ...)
        int padding = 2; // 번호 자리수 설정
        String titleNum = titleMapper.titleAutoNum("TITLE", "TITLE_NUM", "title", padding);

        TitleCommand titleCommand = new TitleCommand();
        titleCommand.setTitleNum(titleNum);
        model.addAttribute("titleCommand", titleCommand);

    }
}
