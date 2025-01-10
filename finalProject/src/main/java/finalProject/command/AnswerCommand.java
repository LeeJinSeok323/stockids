package finalProject.command;

import lombok.Data;

import java.util.Date;

@Data
public class AnswerCommand {
    String inquireNum;
    String memberNum;
    String adminNum;
    String answerContents;
    Date answerTime;
}
