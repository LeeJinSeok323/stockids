package finalProject.service.Stock;

import finalProject.domain.AuthInfoDTO;
import finalProject.domain.stock.CumulativeStockDTO;
import finalProject.domain.stock.PurchaseDTO;
import finalProject.mapper.AccountMapper;
import finalProject.mapper.AutoNumMapper;
import finalProject.mapper.StockMapper;
import finalProject.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockPurchaseService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    StockMapper stockMapper;

    @Autowired
    AutoNumMapper autoNumMapper;

    @Autowired
    AccountMapper accountMapper;

    public void execute(PurchaseDTO purchaseDTO, HttpSession session){
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        String memberNum = userMapper.getUserNumById(auth.getUserId());
        String autoNum = autoNumMapper.autoNumSelect("VDN_","VIRTUAL_DEAL_NUM",5, "virture_stock");
        purchaseDTO.setMemberNum(memberNum);
        purchaseDTO.setVirtualDealNum(autoNum);
        stockMapper.purchase(purchaseDTO);

        Integer userPoint = accountMapper.checkPoint(memberNum);
        userPoint = userPoint - purchaseDTO.getVirtualDealPrice();
        accountMapper.purchasePointUpdate(userPoint, memberNum);

        Integer count = stockMapper.cumulativeStockSelect(memberNum, purchaseDTO.getStockCode());
        if(count != null) {
            count = count + purchaseDTO.getVirtualDealVolume();
            stockMapper.cumulativeStockUpdate(memberNum, purchaseDTO.getStockCode(), count);
        } else {
            stockMapper.cumulativeStockInsert(memberNum, purchaseDTO.getStockCode(), purchaseDTO.getVirtualDealVolume());
        }
    }
}
