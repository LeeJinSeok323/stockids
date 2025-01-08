package finalProject.service.Stock;

import finalProject.domain.AuthInfoDTO;
import finalProject.domain.stock.CumulativeStockDTO;
import finalProject.domain.stock.PurchaseDTO;
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
        List<PurchaseDTO> dto = stockMapper.cumulativeStockSelectAll(memberNum);
        for(PurchaseDTO p : dto) {
            int plus = p.getVirtualDealPrice() * p.getVirtualDealVolume();
            count += plus;
        }
        return count;
    }

}
