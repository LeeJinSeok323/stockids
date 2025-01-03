package finalProject.service.article;

import finalProject.domain.article.ArticleDTO;
import finalProject.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnexpectedArticleService {
    @Autowired
    ArticleMapper articleMapper;
    public List<ArticleDTO> execute(){
        return articleMapper.selectUnexpectedArticle();
    }
}
