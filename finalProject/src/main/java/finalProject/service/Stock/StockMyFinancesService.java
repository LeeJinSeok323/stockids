package finalProject.service.Stock;

import finalProject.domain.AuthInfoDTO;
import finalProject.domain.stock.CumulativeStockDTO;
import finalProject.domain.stock.PurchaseDTO;
import finalProject.domain.stock.StockDTO;
import finalProject.mapper.StockMapper;
import finalProject.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockMyFinancesService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    StockMapper stockMapper;

    public Integer execute(HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        String memberNum = userMapper.getUserNumById(auth.getUserId());

        Integer count = 0;
        List<CumulativeStockDTO> dto = stockMapper.cumulativeStockSelectAll(memberNum);
        List<StockDTO> stockDTO = stockMapper.stockInfo();
        for(CumulativeStockDTO p : dto) {
            for(StockDTO d : stockDTO) {
                if(d.getStockCode().equals(p.getStockCode())) {
                    Integer plus = p.getCountStock() * d.getClosePrice();
                    count += plus;
                }
            }
        }
        return count;
    }

}
