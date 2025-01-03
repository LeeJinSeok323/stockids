package finalProject.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("title")
public class TitleDTO {
    String titleNum;
    String titleContents;
    String titleName;
    String adminNum;
}
