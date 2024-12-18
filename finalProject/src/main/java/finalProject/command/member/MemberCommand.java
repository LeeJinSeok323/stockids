package finalProject.command.member;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
@Data
public class MemberCommand {
        @NotEmpty(message = "아이디를 입력해주세요. ")
        @Size(min = 8, max = 12)
        String memberId;
        @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
                message = "영문자와 숫자 그리고 특수문자가 포함된 8글자 이상")
        String memberPw;
        String memberPwCon;
        @NotBlank(message = "이름을 입력하여 주세요.")
        String memberName;
        @NotBlank(message = "별명을 입력하여 주세요.")
        String memberNickName;
        String gender;
        @NotNull(message = "생년월일을 입력해주세요.")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date memberBirth;
    public boolean isMemberPwEqualMemberPwCon() {
        return memberPw.equals(memberPwCon);
    }
}
