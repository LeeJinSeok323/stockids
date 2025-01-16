package finalProject.controller;

import finalProject.command.AnswerCommand;
import finalProject.domain.AuthInfoDTO;
import finalProject.mapper.UserMapper;
import finalProject.service.AutoNumService;
import finalProject.service.inquire.AnswerDeleteService;
import finalProject.service.inquire.AnswerWriteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@Autowired
AnswerDeleteService answerDeleteService;

    @PostMapping("answerWrite")
    public String answerWrite(HttpSession session, AnswerCommand answerCommand) {
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        answerCommand.setAdminNum(userMapper.getUserNumById(auth.getUserId()));
        answerWriteService.execute(answerCommand);
        return "redirect:/inquire/inquireDetail/" + answerCommand.getInquireNum();
    }

    @GetMapping("answerDelete/{inquireNum}")
    public String answerDelete(@PathVariable("inquireNum") String inquireNum, Model model, HttpSession session){
        answerDeleteService.execute(inquireNum);
        return "redirect:/inquire/inquireDetail/" + inquireNum;
    }
}