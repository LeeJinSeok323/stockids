package finalProject.controller;

import finalProject.command.PostCommand;
import finalProject.domain.AuthInfoDTO;
import finalProject.mapper.MemberMapper;
import finalProject.service.AutoNumService;
import finalProject.service.post.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("post")
public class PostController {
    @Autowired
    PostWriteService postWriteService;
    @Autowired
    AutoNumService autoNumService;
    @Autowired
    PostListService postListService;
    @Autowired
    PostDetailService postDetailService;
    @Autowired
    PostUpdateService postUpdateService;
    @Autowired
    PostDeleteService postDeleteService;
    @Autowired
    PostLikeService postLikeService;
    @Autowired
    MemberMapper memberMapper;

    @GetMapping("postList")
    public String postList(@RequestParam(value = "searchWord", required = false) String searchWord,
                       @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                       Model model) {
        postListService.execute(page, searchWord, model);
        return "thymeleaf/post/postList";
    }

    @GetMapping("postWrite")
    public String postWrite(Model model, HttpSession session) {
        // 세션에서 사용자 정보를 확인
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
        String autoNum = autoNumService.execute("post_", "post_num", 6, "post");
        PostCommand postCommand = new PostCommand();
        postCommand.setPostNum(autoNum);
        model.addAttribute("postCommand", postCommand);
        return "thymeleaf/post/postForm";
    }

    @PostMapping("postRegist")
    public String postRegist(PostCommand postCommand, HttpSession session) {
        postWriteService.execute(postCommand, session);
        return "redirect:/post/postList";
    }

    @GetMapping("postDetail/{postNum}")
    public String postDetail(@PathVariable("postNum") String postNum, Model model, HttpSession session) {
        postDetailService.execute(model, postNum);
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        model.addAttribute("auth", auth);
        return "thymeleaf/post/postInfo";
    }

    @GetMapping("postUpdate")
    public String postUpdate(String postNum, Model model) {
        postDetailService.execute(model, postNum);
        return "thymeleaf/post/postModify";
    }

    @PostMapping("postUpdate")
    public String postupdate(PostCommand postCommand) {
        postUpdateService.execute(postCommand);
        return "redirect:postDetail/"+postCommand.getPostNum();
    }

    @GetMapping("postDelete/{postNum}")
    public String postDelete(@PathVariable("postNum") String postNum) {
        postDeleteService.execute(postNum);
        return "redirect:../postList";
    }

    @PostMapping("postLike")
    public String postLike(HttpSession session, PostCommand postCommand) {
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
        } else {
            String memberNum = memberMapper.getMemberNum(auth.getUserId());
            String likeCount = postLikeService.execute(postCommand, memberNum);
            return "redirect:postDetail/"+postCommand.getPostNum();
        }
    }

}