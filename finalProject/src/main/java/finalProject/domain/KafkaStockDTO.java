package finalProject.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@NoArgsConstructor
@Alias("kafkaStockDTO")
public class KafkaStockDTO {
    String timestamp;
    String stockCode;
    int dealVolume;
    int dealPrice;
    String timeCode;
    int totalDealVolume;
    int max;
    int min;
    int start;
}
