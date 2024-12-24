package finalProject.finalProject.command.login;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@RequestMapping("login")
public class LoginCommand {

String userId;


String userPw;

}
