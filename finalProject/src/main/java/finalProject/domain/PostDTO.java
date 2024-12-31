package finalProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("postDTO")
public class PostDTO {
    String postNum;       // 게시글 번호
    String postAuthor;    // 작성자
    String category;      // 카테고리
    String postSubject;   // 제목
    String postContents;  // 내용
    Integer viewCount;    // 조회수
    Integer likeCount;    // 좋아요 수
    Date postTime;        // 작성 시간
}
