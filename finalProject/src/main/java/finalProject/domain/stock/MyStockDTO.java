package finalProject.domain.stock;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("myStockDTO")
public class MyStockDTO {
    StockDTO stockDTO;
    CumulativeStockDTO cumulativeStockDTO;
}
