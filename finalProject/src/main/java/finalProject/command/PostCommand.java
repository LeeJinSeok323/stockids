package finalProject.command;

import lombok.Data;

import java.util.Date;

@Data
public class PostCommand {
    String postNum;       // 게시글 번호
    String postAuthor;    // 작성자
    String category;      // 카테고리
    String postSubject;   // 제목
    Integer viewCount;    // 조회수
    Integer likeCount;    // 좋아요 수
    String postContents;  // 내용
    Date postTime;        // 작성 시간
}