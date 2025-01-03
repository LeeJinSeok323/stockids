package finalProject.service.post;

import finalProject.command.PostCommand;
import finalProject.domain.PostDTO;
import finalProject.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostWriteService {
    @Autowired
    PostMapper postMapper;

    public void execute(PostCommand postCommand) {
        PostDTO postDTO = new PostDTO();
        postDTO.setPostNum(postCommand.getPostNum());
        postDTO.setCategory(postCommand.getCategory());
        postDTO.setPostSubject(postCommand.getPostSubject());
        postDTO.setPostAuthor(postCommand.getPostAuthor());
        postDTO.setPostContents(postCommand.getPostContents());
        postMapper.postInsert(postDTO);
    }
}