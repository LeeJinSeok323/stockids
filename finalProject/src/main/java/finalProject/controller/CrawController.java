package finalProject.controller;

import finalProject.domain.craw.CrawItem;
import finalProject.service.craw.CrawDbSaveService;
import finalProject.service.craw.CrawServices;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/craw/news")
    public List<CrawItem> getNews(@RequestParam String query) throws IOException {
        List<CrawItem> filteredItems = crawServices.fetchNews(query);
        crawDbSaveService.execute(filteredItems, query);
        return filteredItems;
    }
}
