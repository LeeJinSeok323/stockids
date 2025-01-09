package finalProject.service.rank;

import finalProject.domain.AuthInfoDTO;
import finalProject.domain.rank.RankDTO;
import finalProject.mapper.RankMapper;
import finalProject.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankInsertService {

    @Autowired
    RankMapper rankMapper;

    public Integer execute(){
        int i = rankMapper.rankInsert();
        return i;
    }
}
