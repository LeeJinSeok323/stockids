package finalProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("commentDTO")
public class CommentDTO {
    String commentNum;
    String commentAuthor;
    String commentAuthorId;
    String commentContents;
    Date commentTime;

    String postNum;
    String postAuthor;
}
