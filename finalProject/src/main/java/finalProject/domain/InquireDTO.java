package finalProject.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("inquire")
public class InquireDTO {
    String inquireNum;
    String memberNum;
    String inquireSubject;
    String inquireContents;
    Date inquireTime;
    String memberName;

    String status; //답변상태
}
