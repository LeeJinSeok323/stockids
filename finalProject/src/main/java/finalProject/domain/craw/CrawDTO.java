package finalProject.domain.craw;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("CrawDTO")
public class CrawDTO {
    String articleNum;
    String articleSubject;
    String articleSummary;
    String originalUrl;
    String articleStoreDate;
    String articleContents;
    String companyName;
}
