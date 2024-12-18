package finalProject.domian.member;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("auth")
public class AuthInfoDTO {
    String UserId;
    String UserPw;
    String UserName;
    String title;
}
