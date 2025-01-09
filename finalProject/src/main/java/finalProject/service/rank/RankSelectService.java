package finalProject.service.rank;

import finalProject.domain.rank.RankDTO;
import finalProject.mapper.RankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankSelectService {

    @Autowired
    RankMapper rankMapper;

    public List<RankDTO> execute(){
        List<RankDTO> rankDTO = rankMapper.rankSelect();
        return rankDTO;
    }
}
