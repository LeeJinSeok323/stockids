package finalProject.service.admin;

import finalProject.domain.TitleDTO;
import finalProject.mapper.TitleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import finalProject.domain.StartEndPageDTO;
import java.util.List;

@Service
public class TitleListService {
    @Autowired
    TitleMapper titleMapper;
    @Autowired
    StartEndPageService startEndPageService;

    String searchWord;
    public void execute(String searchWord, Model model, int page) {
        if (searchWord != null) {
            this.searchWord = searchWord.trim();
        }
        StartEndPageDTO sepDTO = startEndPageService.execute(page, 10, this.searchWord);  // limit을 10으로 설정
        List<TitleDTO> list = titleMapper.allSelect(sepDTO);
        int count = titleMapper.titleCount(this.searchWord);
        startEndPageService.execute(page, 10, count, this.searchWord, list, model);  // limit을 10으로 설정
    }

    public List<TitleDTO> getAllTitles() {
        return titleMapper.getAllTitles();
    }


}
