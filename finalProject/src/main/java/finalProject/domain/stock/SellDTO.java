package finalProject.domain.stock;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("sellDTO")
public class SellDTO {
    String virtualDealNum;
    String memberNum;
    String stockCode;
    Integer virtualDealPrice;
    Integer virtualDealVolume;
    Integer profit;
    Date virtualDealTime;
}
