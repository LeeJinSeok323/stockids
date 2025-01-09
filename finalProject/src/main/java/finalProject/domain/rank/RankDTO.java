package finalProject.domain.rank;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("rankDTO")
public class RankDTO {
    String rank;
    String memberNum;
    Integer assetValue;
    String nickName;
}
