package finalProject.service.Stock;

import finalProject.domain.AuthInfoDTO;
import finalProject.domain.stock.CumulativeStockDTO;
import finalProject.mapper.StockMapper;
import finalProject.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockPurchaseInfoService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    StockMapper stockMapper;

    public CumulativeStockDTO execute(String stockCode, HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        String memberNum = userMapper.getUserNumById(auth.getUserId());
        CumulativeStockDTO dto = stockMapper.cumulativeStockSelect(memberNum, stockCode);
        return dto;
    }
}
