package finalProject.domain.alert;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("alertMemberDTO")
public class AlertMemberDTO {
    String memberNum;
    String memberName;
    Integer account;
    Integer stockAccount;
    Integer totalAccount;
}
