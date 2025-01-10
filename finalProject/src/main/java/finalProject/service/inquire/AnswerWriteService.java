package finalProject.service.inquire;

import finalProject.command.AnswerCommand;
import finalProject.domain.AnswerDTO;
import finalProject.mapper.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;

@Service
public class AnswerWriteService {
    @Autowired
    AnswerMapper answerMapper;
    @Autowired
    private StringHttpMessageConverter stringHttpMessageConverter;

    public void execute(AnswerCommand answerCommand){
       AnswerDTO answerDTO = new AnswerDTO();
       answerDTO.setInquireNum(answerCommand.getInquireNum());
       answerDTO.setMemberNum(answerMapper.getMemberNumByInquireNum(answerCommand.getInquireNum()));
       answerDTO.setAdminNum(answerCommand.getAdminNum());
       answerDTO.setAnswerContents(answerCommand.getAnswerContents());
       answerMapper.answerInsert(answerDTO);
    }

}
