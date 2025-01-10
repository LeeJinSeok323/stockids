package finalProject.mapper;

import finalProject.domain.AnswerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnswerMapper {
    public void answerInsert(AnswerDTO answerDTO);
    public List<AnswerDTO> answerSelectList(String inquireNum);
    void answerDelete(String answerNum);
    public String getMemberNumByInquireNum(String inquireNum);
}
