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
    String postNum;
    String postAuthor;
    String category;
    String postSubject;
    String postContents;
    Integer viewCount;
    Integer likeCount;
    Date postTime;
}
