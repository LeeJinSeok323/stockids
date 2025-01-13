package finalProject.service.Stock;

import finalProject.domain.stock.ChartDTO;
import finalProject.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockChartDataService {
    @Autowired
    StockMapper stockMapper;

    public List<ChartDTO> execute(){
        List<ChartDTO> dto = stockMapper.stockChart();
        return dto;
    }
}
