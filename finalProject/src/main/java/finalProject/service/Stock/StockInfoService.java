package finalProject.service.Stock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import finalProject.API.StockInfoAPI;
import org.springframework.stereotype.Service;

@Service
public class StockInfoService {

    private final StockInfoAPI stockInfoApi = new StockInfoAPI();

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

        return arrayNode.toString(); // JSON 배열 반환
    }
}