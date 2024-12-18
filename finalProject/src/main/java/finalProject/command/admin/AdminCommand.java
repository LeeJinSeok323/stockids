package finalProject.command.admin;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Data
public class AdminCommand {
    String AdminNum;
    @NotEmpty(message = "아이디를 입력해주세요")
    String AdminId;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
            message = "영문자와 숫자 그리고 특수문자가 포함된 8글자 이상")
    String AdminPw;
    @NotBlank(message = "비밀번호 확인을 입력해주세요.")
    String AdminPwCon;
    @NotEmpty(message = "이름을 입력해주세요")
    String AdminName;
    String gender;
    @NotNull(message="생년월일을 입력해주세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date AdminBirth;
    @NotEmpty(message = "직책을 입력하세요")
    String Adminposition;
    public boolean isMemberPwEqualMemberPwCon() {
        return AdminPw.equals(AdminPwCon);
    }
}
