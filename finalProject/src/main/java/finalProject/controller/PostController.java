package finalProject.controller;

import finalProject.command.PostCommand;
import finalProject.service.post.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("post")
public class PostController {
    @Autowired
    PostWriteService postWriteService;

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
    public String postWrite(Model model) {
        String autoNum = autoNumService.execute("post_", "post_num", 6, "post");
        PostCommand postCommand = new PostCommand();
        postCommand.setPostNum(autoNum);
        model.addAttribute("postCommand", postCommand);
        return "thymeleaf/post/postForm";
    }

    @PostMapping("postRegist")
    public String postRegist(PostCommand postCommand) {
        postWriteService.execute(postCommand);
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