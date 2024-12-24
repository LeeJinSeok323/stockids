package finalProject.command;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminCommand {
    @NotEmpty(message = "아이디는 필수입니다.")
    String adminId;
    @NotEmpty(message = "비밀번호는 필수입니다.")
    String adminPw;
    @NotEmpty(message = "비밀번호 확인은 필수입니다.")
    String adminPwCon;
    String adminName;
    String gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date birth;
    String position;

    public boolean isEqualPwAndPwCon(){
        if(adminPw.equals(adminPwCon))
            return true;
        else
            return false;
    }
    public boolean isSelectedPosition(){
        if(position.equals("select"))
            return false;
        else
            return true;
    }
}
