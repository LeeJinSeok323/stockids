package finalProject.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("alert")
public class AlertDTO {
    String alertNum;
    String alertContents;
    Date alertDate;
    String adminNum;
    String memberNum;
    String memberName;
}
