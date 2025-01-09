package finalProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("answerDTO")
public class AnswerDTO {
    String inquireNum;
    String memberNum;
    String adminNum;
    String answerContents;
    Date answerTime;
}
