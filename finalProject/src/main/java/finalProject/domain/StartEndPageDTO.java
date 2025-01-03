package finalProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@Alias("startEndPageDTO")
 public class StartEndPageDTO {
    int startRow;
    int endRow;
    String searchWord;

}
