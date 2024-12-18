package finalProject.domian.admin;


import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("adm")
public class AdminDTO {
    String admNum;
    String admId;
    String admPw;
    String admName;
    String gender;
    Date admBrith;
    String position;
}
