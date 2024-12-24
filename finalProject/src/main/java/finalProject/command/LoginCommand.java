package finalProject.command;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@RequestMapping("login")
public class LoginCommand {

String userId;


String userPw;

}
