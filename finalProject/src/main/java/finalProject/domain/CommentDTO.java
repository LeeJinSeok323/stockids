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
    String commentNum;        // 댓글 번호
    String commentAuthor;     // 댓글 작성자
    String commentAuthorId;
    String commentContents;   // 댓글 내용
    Date commentTime;         // 댓글 작성 시간

    String postNum;       // 게시글 번호
    String postAuthor;    // 작성자
}
