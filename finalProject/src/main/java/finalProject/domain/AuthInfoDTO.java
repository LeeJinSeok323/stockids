package finalProject.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("authInfo")
public class AuthInfoDTO {
    String userId;
    String userPw;
    String userName;
}
