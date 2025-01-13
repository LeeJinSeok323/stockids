package finalProject.service.craw;

import finalProject.domain.craw.CrawDTO;
import finalProject.mapper.CrawMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrawPredictionService {

    @Autowired
    CrawMapper crawMapper;

    public List<CrawDTO> execute(){
        List<CrawDTO> dto = crawMapper.predictionCraw();
        return dto;
    }
}
