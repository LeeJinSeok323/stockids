package finalProject.mapper;

import finalProject.domain.KafkaStockDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KafkaMapper {
    public Integer insertDealOne(KafkaStockDTO dto);
    public Integer updateTimeCode(String timeCode);
}
