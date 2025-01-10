package finalProject.service.inquire;

import finalProject.domain.AnswerDTO;
import finalProject.mapper.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class AnswerListService {
    @Autowired
    AnswerMapper answerMapper;
    public void execute(Model model, String inquireNum){
        List<AnswerDTO> list = answerMapper.answerSelectList(inquireNum);
        model.addAttribute("answerList",list);

    }

}
