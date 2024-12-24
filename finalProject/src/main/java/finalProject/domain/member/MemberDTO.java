package finalProject.domain.member;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;
@Data
@Alias("mem")
public class MemberDTO {
    String memberNum;
    String memberId;
    String memberPw;
    String memberPwCon;
    String memberName;
    String nickName;
    String gender;
    Date memberBirth;

}
