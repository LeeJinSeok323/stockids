package finalProject.controller;

import finalProject.domain.craw.CrawItem;
import finalProject.service.craw.CrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class CrawController {

    @Autowired
    private CrawService crawService;

    @GetMapping("/craw/news")
    public List<CrawItem> getNews(@RequestParam String query) throws IOException {
        List<CrawItem> filteredItems = crawService.fetchNews(query);
        return filteredItems;
    }
}
