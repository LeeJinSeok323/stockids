package finalProject.service.post;

import finalProject.command.PostCommand;
import finalProject.domain.AuthInfoDTO;
import finalProject.domain.PostDTO;
import finalProject.mapper.AdminMapper;
import finalProject.mapper.MemberMapper;
import finalProject.mapper.PostMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostWriteService {
    @Autowired
    PostMapper postMapper;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    private AdminMapper adminMapper;

    public void execute(PostCommand postCommand, HttpSession session) {
        PostDTO postDTO = new PostDTO();
        postDTO.setPostNum(postCommand.getPostNum());
        postDTO.setCategory(postCommand.getCategory());
        postDTO.setPostSubject(postCommand.getPostSubject());
        postDTO.setPostAuthor(postCommand.getPostAuthor());
        postDTO.setPostContents(postCommand.getPostContents());
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        String postAuthor = memberMapper.getMemberNum(auth.getUserId());
        postDTO.setPostAuthor(postAuthor);
        postMapper.postInsert(postDTO);
    }
}