package finalProject.service.Stock;

import finalProject.domain.AuthInfoDTO;
import finalProject.domain.stock.MyStockDTO;
import finalProject.mapper.StockMapper;
import finalProject.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockMyService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    StockMapper stockMapper;

    public List<MyStockDTO> execute(HttpSession session){
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        String memberNum = userMapper.getUserNumById(auth.getUserId());
        List<MyStockDTO> dto = stockMapper.fetchMyStock(memberNum);
        return dto;
    }
}
