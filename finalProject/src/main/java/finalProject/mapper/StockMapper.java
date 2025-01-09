package finalProject.mapper;

import finalProject.domain.stock.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StockMapper {
    int isStockExist(String stockCode);
    void updateStockInfo(StockDTO stockDTO);
    void insertStockInfo(StockDTO stockDTO);
    void purchase(PurchaseDTO purchaseDTO);
    CumulativeStockDTO cumulativeStockSelect(@Param ("memberNum")String memberNum, @Param("stockCode") String stockCode);
    void cumulativeStockUpdate(@Param ("memberNum")String memberNum, @Param("stockCode") String stockCode, @Param("count") Integer count, @Param("avg") Integer avg);
    void cumulativeStockInsert(@Param ("memberNum")String memberNum, @Param("stockCode") String stockCode, @Param("countStock") Integer countStock, @Param("price") Integer price);
    List<MyStockDTO> fetchMyStock(String memberNum);
    List<PurchaseDTO> cumulativeStockSelectAll(String memberNum);

    void sell(SellDTO sellDTO);
}
