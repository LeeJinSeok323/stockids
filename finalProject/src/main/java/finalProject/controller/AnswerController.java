package finalProject.controller;

import finalProject.command.AnswerCommand;
import finalProject.domain.AuthInfoDTO;
import finalProject.mapper.UserMapper;
import finalProject.service.AutoNumService;
import finalProject.service.inquire.AnswerWriteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("answer")
public class AnswerController {
@Autowired
UserMapper userMapper;
@Autowired
AnswerWriteService answerWriteService;

    @PostMapping("answerWrite")
    public String answerWrite(HttpSession session, AnswerCommand answerCommand) {
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        if (auth == null || !auth.getUserId().equals("admin")) {
            try {
                String message = URLEncoder.encode("로그인이 필요합니다.", "UTF-8");

                return "redirect:/login?message=" + message;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "redirect:/login";
            }

        }
        answerCommand.setAdminNum(userMapper.getUserNumById(auth.getUserId()));
        answerWriteService.execute(answerCommand);
        return "redirect:/inquire/inquireDetail" + answerCommand.getInquireNum();
    }

}
