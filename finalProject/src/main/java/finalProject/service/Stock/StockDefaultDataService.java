package finalProject.service.Stock;

import finalProject.domain.stock.StockDTO;
import finalProject.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockDefaultDataService {

    @Autowired
    StockMapper stockMapper;

    public List<StockDTO> execute(){
        List<StockDTO> dto = stockMapper.stockInfo();
        return dto;
    }
}
