package finalProject.domian.member;


import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("userChangePassword")
public class UserChangePasswordDTO {
    String userId;
    String userPhone;
    String userPw;

    String tableName;
    String pwColumName;
    String userIdColumName;
}
