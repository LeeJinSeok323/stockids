package finalProject.controller;

import finalProject.service.move.MoveService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoveController {

    @Autowired
    MoveService moveService;

    @GetMapping("/move")
    public void moveNews(@RequestParam("moveWord") String moveWord, HttpSession session) {
        String m = moveService.execute(moveWord);
        session.setAttribute("moveData", m);
    }

    @GetMapping("/move/data")
    public String moveData(HttpSession session) {
        String moveData = (String) session.getAttribute("moveData");
        return moveData;
    }
}
