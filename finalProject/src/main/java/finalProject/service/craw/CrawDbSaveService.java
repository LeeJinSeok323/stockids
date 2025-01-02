package finalProject.service.craw;

import finalProject.domain.craw.CrawDTO;
import finalProject.domain.craw.CrawItem;
import finalProject.mapper.CrawMapper;
import finalProject.service.AutoNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CrawDbSaveService {

    @Autowired
    CrawMapper crawMapper;

    @Autowired
    AutoNumService autoNumService;

    public void execute(List<CrawItem> filteredItems) {
        List<CrawDTO> existingItems = crawMapper.crawList();
        Set<String> existingTitles = new HashSet<>();
        for (CrawDTO dto : existingItems) {
            existingTitles.add(dto.getArticleSubject());
        }

        for (CrawItem crawItem : filteredItems) {
            if (existingTitles.contains(crawItem.title)) {
                continue;
            }

            String autuNum = autoNumService.execute("article_", "ARTICLE_NUM", 9, "article");
            crawMapper.craw(autuNum, crawItem.title, crawItem.description, crawItem.link, crawItem.body);

            existingTitles.add(crawItem.title);
        }
    }
}
