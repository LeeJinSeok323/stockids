package finalProject.command;

import lombok.Data;

import java.util.Date;

@Data
public class CommentCommand {
    String commentNum;
    String commentAuthor;
    String commentContents;
    Date commentTime;
    String postNum;
    String postAuthor;
}
