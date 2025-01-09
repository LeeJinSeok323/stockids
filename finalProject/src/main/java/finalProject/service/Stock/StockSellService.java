package finalProject.service.Stock;

import finalProject.domain.AuthInfoDTO;
import finalProject.domain.stock.CumulativeStockDTO;
import finalProject.domain.stock.PurchaseDTO;
import finalProject.domain.stock.SellDTO;
import finalProject.mapper.AccountMapper;
import finalProject.mapper.AutoNumMapper;
import finalProject.mapper.StockMapper;
import finalProject.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockSellService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    StockMapper stockMapper;

    @Autowired
    AutoNumMapper autoNumMapper;

    @Autowired
    AccountMapper accountMapper;

    public void execute(SellDTO sellDTO, HttpSession session){
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        String memberNum = userMapper.getUserNumById(auth.getUserId());
        String autoNum = autoNumMapper.autoNumSelect("SVDN_","VIRTUAL_DEAL_NUM",6,"SELL_VIRTURE_STOCK");
        sellDTO.setVirtualDealNum(autoNum);
        sellDTO.setMemberNum(memberNum);
        stockMapper.sell(sellDTO);

        CumulativeStockDTO dto = stockMapper.cumulativeStockSelect(memberNum, sellDTO.getStockCode());
        Integer stockCount = dto.getCountStock() - sellDTO.getVirtualDealVolume();
        Integer avg = 0;
        Integer count = 0;
        if(stockCount == 0){
            avg = 0;
            count = 0;
        } else {
            avg = ((dto.getCountStock() * dto.getAvgStock()) - (sellDTO.getVirtualDealPrice() * sellDTO.getVirtualDealVolume())) / ((dto.getCountStock() - sellDTO.getVirtualDealVolume()));
            count = dto.getCountStock() - sellDTO.getVirtualDealVolume();
        }
        stockMapper.cumulativeStockUpdate(memberNum, sellDTO.getStockCode(), count, avg);

        Integer userPoint = accountMapper.checkPoint(memberNum);
        userPoint = userPoint + (sellDTO.getVirtualDealPrice() * sellDTO.getVirtualDealVolume());
        accountMapper.purchasePointUpdate(userPoint, memberNum);

    }
}
