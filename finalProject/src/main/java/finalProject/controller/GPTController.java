package finalProject.controller;

import finalProject.service.gpt.ArticlePredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gpt")
public class GPTController {
    @Autowired
    ArticlePredictionService articlePredictionService;
    @RequestMapping("test")
    public void test() {
        articlePredictionService.execute();
    }
}
