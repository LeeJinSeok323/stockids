package finalProject.controller;

import finalProject.domain.AuthInfoDTO;
import finalProject.domain.stock.CumulativeStockDTO;
import finalProject.domain.stock.PurchaseDTO;
import finalProject.domain.stock.SellDTO;
import finalProject.domain.stock.StockDTO;
import finalProject.mapper.UserMapper;
import finalProject.service.Stock.StockPurchaseInfoService;
import finalProject.service.Stock.StockPurchaseService;
import finalProject.service.Stock.StockSellService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockPurchaseController {

    @Autowired
    StockPurchaseService stockPurchaseService;

    @Autowired
    StockPurchaseInfoService stockPurchaseInfoService;

    @Autowired
    StockSellService stockSellService;

    @PostMapping("/stock/purchase")
    public ResponseEntity<String> purchaseStock(@RequestBody PurchaseDTO purchaseDTO, HttpSession session) {
        stockPurchaseService.execute(purchaseDTO, session);
        return ResponseEntity.ok("구매 성공");
    }

    @GetMapping("/stock/purchase/info")
    public ResponseEntity<CumulativeStockDTO> purchaseInfo(
            @RequestParam("stockCode") String stockCode, HttpSession session) {
        CumulativeStockDTO dto = stockPurchaseInfoService.execute(stockCode, session);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/stock/sell")
    public void sellStock(@RequestBody SellDTO sellDTO, HttpSession session) {
        stockSellService.execute(sellDTO, session);
    }
}