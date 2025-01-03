package finalProject.controller;

import finalProject.service.account.AccountPointChargeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    AccountPointChargeService accountPointChargeService;

    @PostMapping
    public void chargePoint(Integer point, HttpSession session) {
        accountPointChargeService.execute(point, session);
    }
}