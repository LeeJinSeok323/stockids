package finalProject.controller;

import finalProject.domain.craw.CrawDTO;
import finalProject.domain.craw.CrawItem;
import finalProject.service.craw.CrawDbSaveService;
import finalProject.service.craw.CrawPredictionScoreService;
import finalProject.service.craw.CrawPredictionService;
import finalProject.service.craw.CrawServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class CrawController {

    @Autowired
    private CrawServices crawServices;

    @Autowired
    private CrawDbSaveService crawDbSaveService;

    @Autowired
    CrawPredictionService crawPredictionService;

    @Autowired
    CrawPredictionScoreService crawPredictionScoreService;

    @GetMapping("/craw/news")
    public List<CrawItem> getNews(@RequestParam String query) throws IOException {
        List<CrawItem> filteredItems = crawServices.fetchNews(query);
        crawDbSaveService.execute(filteredItems, query);
        return filteredItems;
    }

    @GetMapping("/craw/news/prediction")
    public ResponseEntity<List<CrawDTO>> getPredictionNews(){
        List<CrawDTO> dto = crawPredictionService.execute();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/craw/news/prediction/score")
    public ResponseEntity<List<CrawDTO>> getPredictionNewsScore(){
        List<CrawDTO> dto = crawPredictionScoreService.execute();
        return ResponseEntity.ok(dto);
    }
}
