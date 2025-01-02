package finalProject.controller;

import finalProject.command.PostCommand;
import finalProject.service.AutoNumService;
import finalProject.service.post.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("postList")
    public String postList(@RequestParam(value = "searchWord", required = false) String searchWord,
                       @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                       Model model) {
        postListService.execute(page, searchWord, model);
        return "thymeleaf/post/postList";
    }

    @GetMapping("postWrite")
    public String postWrite(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        // 세션에서 사용자 정보를 확인
        Object auth = session.getAttribute("auth"); // 세션에 저장된 로그인 정보
        if (auth == null) {
            redirectAttributes.addFlashAttribute("alertMessage", "로그인 후 글 작성을 할 수 있습니다.");
            return "redirect:/login";
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
    public String postDetail(@PathVariable("postNum") String postNum
            ,Model model) {
        postDetailService.execute(model, postNum);
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
}