package finalProject.mapper;

import finalProject.domain.article.ArticleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    public List<ArticleDTO> selectUnexpectedArticle();
}
