package finalProject.command;

import lombok.Data;

import java.util.Date;

@Data
public class PostCommand {
    String postNum;
    String postAuthor;
    String postAuthorId;
    String category;
    String postSubject;
    Integer viewCount;
    Integer likeCount;
    String postContents;
    Date postTime;
}