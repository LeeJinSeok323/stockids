package finalProject.service.account;
import finalProject.domain.AuthInfoDTO;
import finalProject.mapper.AccountMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountPointChargeService {
    @Autowired
    AccountMapper accountMapper;

    public void execute(Integer point, HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        String userId = auth.getUserId();
//        accountMapper.pointCharge(point, userId);
    }
}