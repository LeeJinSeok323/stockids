package finalProject.controller;

import finalProject.domain.rank.RankDTO;
import finalProject.service.rank.RankInsertService;
import finalProject.service.rank.RankProfitService;
import finalProject.service.rank.RankSelectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RankController {

    @Autowired
    RankInsertService rankInsertService;

    @Autowired
    RankSelectService rankSelectService;

    @Autowired
    RankProfitService rankProfitService;

//    @GetMapping("/rank/insert")
//    public ResponseEntity<List<RankDTO>> setRank(HttpSession session) {
//        List<RankDTO> rankDTO = rankInsertService.execute(session);
//        return ResponseEntity.ok(rankDTO);
//    }

    @GetMapping("/rank/insert")
    public Integer rankInsert() {
        int i = rankInsertService.execute();
        return i;
    }

    @GetMapping("/rank/data")
    public ResponseEntity<List<RankDTO>> rankSelect() {
        List<RankDTO> dto = rankSelectService.execute();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/profit")
    public ResponseEntity<Integer> profit(HttpSession session) {
        Integer i = rankProfitService.execute(session);
        return ResponseEntity.ok(i);
    }
}
