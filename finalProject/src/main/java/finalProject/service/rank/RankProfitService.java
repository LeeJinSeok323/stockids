package finalProject.service.rank;

import finalProject.domain.AuthInfoDTO;
import finalProject.mapper.RankMapper;
import finalProject.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankProfitService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RankMapper rankMapper;

    public Integer execute(HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        String memberNum = userMapper.getUserNumById(auth.getUserId());
        Integer i = rankMapper.rankProfit(memberNum);
        return i;
    }
}
