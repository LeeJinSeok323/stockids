package finalProject.domian.member;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;
@Alias("memberDTO")
@Data
public class MemberDTO {
    String memberNum;
    String memberId;
    String memberPw;
    String memberName;
    String gender;
    Date memberBirth;
    String titleNum;
}
