package finalProject.controller;

import finalProject.service.account.AccountPointChargeService;
import finalProject.service.account.AccountPointCheckService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    AccountPointChargeService accountPointChargeService;

    @Autowired
    AccountPointCheckService accountPointCheckService;

    @PostMapping("/point/charge")
    public void chargePoint(@RequestBody Map<String, Integer> body, HttpSession session) {
        Integer point = body.get("point");
        if (point == null) {
            throw new IllegalArgumentException("Point value cannot be null");
        }
        System.out.println("Point: " + point);
        accountPointChargeService.execute(point, session);
    }

    @GetMapping("point/check")
    public Integer checkPoint(HttpSession session) {
        Integer checkPoint = accountPointCheckService.execute(session);
        return checkPoint;
    }
}