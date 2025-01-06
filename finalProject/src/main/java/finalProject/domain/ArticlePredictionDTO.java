package finalProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("articlePredictionDTO")
public class ArticlePredictionDTO {
    String articlePredictionNum;
    String gptModel;
    Integer articlePredictionValue;
    Date articlePredictionDate;
    String articlePredictionContents;
}
