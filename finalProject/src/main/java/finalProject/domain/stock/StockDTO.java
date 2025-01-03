package finalProject.domain.stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("stockDTO")
public class StockDTO {
    Integer stockCode;
    String stockName;
    Integer openPrice;
    Integer closePrice;
    Integer lowPrice;
    Integer highPrice;
}
