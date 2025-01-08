package finalProject.controller;

import finalProject.command.CommentCommand;
import finalProject.domain.AuthInfoDTO;
import finalProject.domain.CommentDTO;
import finalProject.mapper.CommentMapper;
import finalProject.mapper.UserMapper;
import finalProject.service.AutoNumService;
import finalProject.service.post.CommentDeleteService;
import finalProject.service.post.CommentWriteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("comment")
public class CommentController {
    @Autowired
    AutoNumService autoNumService;
    @Autowired
    CommentWriteService commentWriteService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CommentDeleteService commentDeleteService;
    @Autowired
    private CommentMapper commentMapper;

    @PostMapping("commentWrite")
    public String commentWrite(HttpSession session, CommentCommand commentCommand) {
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        if (auth == null) {
            try {
                String message = URLEncoder.encode("로그인이 필요합니다.", "UTF-8");
                return "redirect:/login?message=" + message;
            } catch (UnsupportedEncodingException e) {
                // 예외 처리
                e.printStackTrace();
                return "redirect:/login";
            }
        }
        String autoNum = autoNumService.execute("comment_", "comment_num", 9, "\"COMMENT\"");
        commentCommand.setCommentNum(autoNum);
        commentCommand.setCommentAuthor(userMapper.getUserNumById(auth.getUserId()));
        commentWriteService.execute(commentCommand);
        return "redirect:/post/postDetail/" + commentCommand.getPostNum();
    }

    @GetMapping("commentDelete/{commentNum}")
    public String postDelete(@PathVariable("commentNum") String commentNum,
                             @RequestParam("postNum") String postNum
            , Model model, HttpSession session) {
        commentDeleteService.execute(commentNum);
        return "redirect:/post/postDetail/" + postNum;
    }
}
