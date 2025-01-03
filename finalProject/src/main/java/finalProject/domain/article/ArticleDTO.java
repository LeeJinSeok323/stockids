package finalProject.domain.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("articleDTO")
public class ArticleDTO {
    String articleNum;
    String articleSubject;
    String articleSummary;
    String originalUrl;
    Date articleStoreDate;
    String articleContents;
}
