package finalProject.service.post;

import finalProject.command.CommentCommand;
import finalProject.domain.CommentDTO;
import finalProject.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentWriteService {
    @Autowired
    CommentMapper commentMapper;

    public void execute(CommentCommand commentCommand){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentNum(commentCommand.getCommentNum());
        commentDTO.setCommentContents(commentCommand.getCommentContents());
        commentDTO.setCommentAuthor(commentCommand.getCommentAuthor());
        commentDTO.setPostNum(commentCommand.getPostNum());
        commentDTO.setPostAuthor(commentMapper.getPostAuthorByPostNum(commentCommand.getPostNum()));
        commentMapper.commentInsert(commentDTO);
    }
}
