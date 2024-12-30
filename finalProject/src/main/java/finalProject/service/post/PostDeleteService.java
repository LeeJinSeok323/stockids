package finalProject.service.post;

import finalProject.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostDeleteService {
    @Autowired
    PostMapper postMapper;
    public void execute(String postNum) {
        postMapper.postDelete(postNum);
    }
}
