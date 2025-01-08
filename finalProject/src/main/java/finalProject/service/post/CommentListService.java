package finalProject.service.post;

import finalProject.domain.AuthInfoDTO;
import finalProject.domain.CommentDTO;
import finalProject.mapper.CommentMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.xml.stream.events.Comment;
import java.util.List;

@Service
public class CommentListService {
    @Autowired
    CommentMapper commentMapper;

    public void execute(Model model, String postNum){
        List<CommentDTO> list = commentMapper.commentSelectList(postNum);
        model.addAttribute("list", list);
    }
}
