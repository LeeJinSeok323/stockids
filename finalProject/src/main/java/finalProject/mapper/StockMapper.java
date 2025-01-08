package finalProject.mapper;

import finalProject.domain.stock.CumulativeStockDTO;
import finalProject.domain.stock.PurchaseDTO;
import finalProject.domain.stock.StockDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StockMapper {
    int isStockExist(String stockCode);
    void updateStockInfo(StockDTO stockDTO);
    void insertStockInfo(StockDTO stockDTO);
    void purchase(PurchaseDTO purchaseDTO);
    Integer cumulativeStockSelect(@Param ("memberNum")String memberNum, @Param("stockCode") String stockCode);
    void cumulativeStockUpdate(@Param ("memberNum")String memberNum, @Param("stockCode") String stockCode, @Param("count") Integer count);
    void cumulativeStockInsert(@Param ("memberNum")String memberNum, @Param("stockCode") String stockCode, @Param("countStock") Integer countStock);
}
