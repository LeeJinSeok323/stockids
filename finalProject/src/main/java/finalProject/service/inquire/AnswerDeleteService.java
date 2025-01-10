package finalProject.service.inquire;

import finalProject.mapper.AnswerMapper;
import finalProject.mapper.InquireMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerDeleteService {
@Autowired
AnswerMapper answerMapper;
public void execute(String inquireNum){
answerMapper.answerDelete(inquireNum);
}
}
