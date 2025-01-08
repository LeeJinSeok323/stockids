package finalProject.domain.stock;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("purchaseDTO")
public class PurchaseDTO {
    String virtualDealNum;
    String memberNum;
    String stockCode;
    Integer virtualDealPrice;
    Integer virtualDealVolume;
    Date virtualDealTime;
}
