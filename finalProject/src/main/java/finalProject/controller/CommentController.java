//package finalProject.controller;
//
//import ch.qos.logback.core.model.Model;
//import finalProject.command.CommentCommand;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("comment")
//public class CommentController {
//    @Autowired
//    CommentListService commentListService;
//    @Autowired
//    CommentWriteService commentWriteService;
//    @Autowired
//    CommentDeleteService commentDeleteService;
//
//    @GetMapping("commentList/{postNum}")
//    public String commentList(@PathVariable("postNum") String postNum, Model model) {
//        commentListService.execute(postNum, model);
//        return "thymeleaf/comment/commentList";
//    }
//
//    @PostMapping("commentWrite")
//    public String commentWrite(CommentCommand commentCommand) {
//        commentWriteService.execute(commentCommand);
//        return "redirect:/post/postDetail/" + commentCommand.getPostNum();
//    }
//
//    @GetMapping("commentDelete/{commentNum}")
//    public String commentDelete(@PathVariable("commentNum") String commentNum,
//                                @RequestParam("postNum") String postNum) {
//        commentDeleteService.execute(commentNum);
//        return "redirect:/post/postDetail/" + postNum;
//    }
//}
