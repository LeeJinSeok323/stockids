package finalProject.service.post;

import finalProject.domain.StartEndPageDTO;
import finalProject.domain.PostDTO;
import finalProject.mapper.PostMapper;
import finalProject.service.StartEndPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class PostListService {
    @Autowired
    PostMapper postMapper;
    @Autowired
    StartEndPageService startEndPageService;

    public void execute(Integer page, String searchWord, Model model) {
        Integer limit = 10; // 우선 10개로 설정
        StartEndPageDTO sepDTO = startEndPageService.execute(page, limit, searchWord);
        List<PostDTO> list = postMapper.postSelectList(sepDTO);
        Integer count = postMapper.postCount();
        startEndPageService.execute(page, limit, count, searchWord, list, model);

    }
}
