package finalProject.mapper;

import finalProject.domain.stock.StockDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockMapper {
    int isStockExist(Integer stockCode);
    void updateStockInfo(StockDTO stockDTO);
    void insertStockInfo(StockDTO stockDTO);
}
