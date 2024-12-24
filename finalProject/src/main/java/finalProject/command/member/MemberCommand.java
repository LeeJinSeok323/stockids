package finalProject.command.member;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class MemberCommand {
String memberId;
String memberPw;
String memberPwCon;
String memberName;
String nickName;
String gender;
@DateTimeFormat(pattern="yyy-MM-dd")
Date memberBirth;

    public boolean isMemberPwEqualMemberPwCon() {
        return memberPw.equals(memberPwCon);
    }


}
