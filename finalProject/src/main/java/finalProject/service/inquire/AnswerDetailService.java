package finalProject.service.inquire;

import finalProject.domain.AnswerDTO;
import finalProject.mapper.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AnswerDetailService {
    @Autowired
    AnswerMapper answerMapper;
    public void execute(Model model, String inquireNum){
        AnswerDTO answerDTO = answerMapper.answerSelectOne(inquireNum);
        String answerAuthorld = answerMapper.findAuthByAnswerNum(inquireNum);
        model.addAttribute("answerCommand", answerDTO);
        model.addAttribute("answerAuthorld", answerAuthorld);
    }
}
