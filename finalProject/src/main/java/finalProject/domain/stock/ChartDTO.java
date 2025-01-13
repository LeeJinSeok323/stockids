package finalProject.domain.stock;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("chartDTO")
public class ChartDTO {
    String dealNum;
    String timeCode;
    String stockCode;
    Integer dealVolume;
    Integer dealPrice;
    Integer maxPrice;
    Integer minPrice;
    Integer startPrice;
    Integer totalDealVolume;
}
