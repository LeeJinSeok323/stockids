package finalProject.service.account;

import finalProject.domain.AuthInfoDTO;
import finalProject.mapper.AccountMapper;
import finalProject.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountPointCheckService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AccountMapper accountMapper;

    public Integer execute(HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        String userNum = userMapper.getUserNumById(auth.getUserId());
        Integer checkPoint = accountMapper.checkPoint(userNum);
        return checkPoint;
    }

}
