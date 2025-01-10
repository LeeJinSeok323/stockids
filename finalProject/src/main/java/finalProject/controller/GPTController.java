package finalProject.controller;

import finalProject.service.gpt.ArticlePredictionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gpt")
public class GPTController {
    @Autowired
    ArticlePredictionService articlePredictionService;
    @RequestMapping("test")
    public String test(HttpSession session) {
//        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
//        if(auth == null) {
//            return "0";
//        }
//        else if(auth.isAdmin()){
//            boolean isChanged = articlePredictionService.execute();
//            if(isChanged)
//                return "200";
//            else
//                return "404";
//        }
//        else {
//            return "401";
//        }
        articlePredictionService.execute();
        return "success";
    }
}
