package finalProject.service.post;

import finalProject.domain.PostDTO;
import finalProject.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class PostDetailService {
    @Autowired
    PostMapper postMapper;
    public void execute(Model model, String postNum) {
        postMapper.postViewCountUpdate(postNum);
        PostDTO postDTO = postMapper.postSelectOne(postNum);
        model.addAttribute("postCommand", postDTO);
    }
}
