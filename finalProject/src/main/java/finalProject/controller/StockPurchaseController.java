package finalProject.controller;

import finalProject.domain.AuthInfoDTO;
import finalProject.domain.stock.PurchaseDTO;
import finalProject.mapper.UserMapper;
import finalProject.service.Stock.StockPurchaseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockPurchaseController {

    @Autowired
    StockPurchaseService stockPurchaseService;

    @PostMapping("/stock/purchase")
    public ResponseEntity<String> purchaseStock(@RequestBody PurchaseDTO purchaseDTO, HttpSession session) {
        stockPurchaseService.execute(purchaseDTO, session);
        return ResponseEntity.ok("구매 성공");
    }
}