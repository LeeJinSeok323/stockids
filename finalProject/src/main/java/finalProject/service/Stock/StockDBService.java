package finalProject.service.Stock;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import finalProject.API.StockInfoAPI;
import finalProject.domain.stock.StockDTO;
import finalProject.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockDBService {

    private final StockInfoAPI stockInfoApi = new StockInfoAPI();

    @Autowired
    StockMapper stockMapper;

    public void execute(ArrayNode data) {
        for (JsonNode node : data) {
            JsonNode items = node.at("/response/body/items/item");

            for (JsonNode item : items) {
                Integer stockCode = item.get("srtnCd").asInt();
                String stockName = item.get("itmsNm").asText();
                Integer openPrice = item.get("mkp").asInt();
                Integer closePrice = item.get("clpr").asInt();
                Integer lowPrice = item.get("lopr").asInt();
                Integer highPrice = item.get("hipr").asInt();

                StockDTO stockInfo = new StockDTO(stockCode, stockName, openPrice, closePrice, lowPrice, highPrice);

                if (stockMapper.isStockExist(stockCode) > 0) {
                    stockMapper.updateStockInfo(stockInfo);
                } else {
                    stockMapper.insertStockInfo(stockInfo);
                }
            }
        }
    }

    public String fetchStockInfo(String basDt, String numOfRows, String pageNo, String[] items) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        for (String item : items) {
            stockInfoApi.setBasDt(basDt);
            stockInfoApi.setNumOfRows(numOfRows);
            stockInfoApi.setPageNo(pageNo);
            stockInfoApi.setItmsNm(item);

            StringBuilder result = stockInfoApi.executeApi();
            if (result != null) {
                ObjectNode node = mapper.readValue(result.toString(), ObjectNode.class);
                arrayNode.add(node);
            }
        }

        execute(arrayNode);

        return arrayNode.toString();
    }
}
