package finalProject.service.inquire;

import finalProject.mapper.InquireMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InquireDeleteService {
    @Autowired
    InquireMapper inquireMapper;
    public void execute(String inquireNum){
        inquireMapper.deleteAnswerByInquireNum(inquireNum);  // 답변 삭제
        inquireMapper.deleteInquireByInquireNum(inquireNum); // 문의글 삭제
    }

}
