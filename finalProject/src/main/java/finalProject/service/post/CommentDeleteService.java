package finalProject.service.post;

import finalProject.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentDeleteService {
    @Autowired
    CommentMapper commentMapper;

    public void execute(String commentNum){
        commentMapper.commentDelete(commentNum);
    }
}
