
package finalProject.controller;


import finalProject.domain.stock.MyStockDTO;
import finalProject.service.Stock.StockDBService;
import finalProject.service.Stock.StockInfoService;
import finalProject.service.Stock.StockMyFinancesService;
import finalProject.service.Stock.StockMyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StockInfoController {

    @Autowired
    private StockInfoService stockInfoService;

    @Autowired
    private StockDBService stockDBService;

    @Autowired
    StockMyService stockMyService;

    @Autowired
    StockMyFinancesService stockMyFinancesService;

    @GetMapping("/api/stock-info")
    public String getStockInfo(
            @RequestParam(required = false) String basDt,
            @RequestParam(defaultValue = "1") String numOfRows,
            @RequestParam(defaultValue = "1") String pageNo,
            @RequestParam(required = false) String item
    ) {
        String[] defaultItems = {
                "삼성전자", "SK하이닉스", "LG에너지솔루션", "삼성바이오로직스",
                "현대차", "셀트리온", "기아", "KB금융", "NAVER", "신한지주"
        };

        String[] items = (item != null && !item.isEmpty())
                ? new String[]{item}
                : defaultItems;

        try {
            return stockInfoService.fetchStockInfo(basDt, numOfRows, pageNo, items);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/api/stock-db")
    public String getStock(
            @RequestParam(required = false) String basDt,
            @RequestParam(defaultValue = "1") String numOfRows,
            @RequestParam(defaultValue = "1") String pageNo,
            @RequestParam(required = false) String item
    ) {
        String[] defaultItems = {
                "삼성전자", "SK하이닉스", "LG에너지솔루션", "삼성바이오로직스",
                "현대차", "셀트리온", "기아", "KB금융", "NAVER", "신한지주"
        };

        String[] items = (item != null && !item.isEmpty())
                ? new String[]{item}
                : defaultItems;

        try {
            return stockDBService.fetchStockInfo(basDt, numOfRows, pageNo, items);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/stock/mypage")
    public List<MyStockDTO> getMyStock(HttpSession session){
        List<MyStockDTO> myStockDTO = stockMyService.execute(session);
        return myStockDTO;
    }

    @GetMapping("/stock/myFinances")
    public Integer getMyFinances(HttpSession session){
        Integer MyFinances = stockMyFinancesService.execute(session);
        return MyFinances;
    }
}
