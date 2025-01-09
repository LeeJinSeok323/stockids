package finalProject.domain.stock;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("cumulativeStockDTO")
public class CumulativeStockDTO {
    String memberNum;
    String stockCode;
    Integer countStock;
    Integer avgStock;
}
