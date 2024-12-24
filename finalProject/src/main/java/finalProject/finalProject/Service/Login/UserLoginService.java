package finalProject.finalProject.Service.Login;

import finalProject.finalProject.command.login.LoginCommand;
import finalProject.finalProject.domain.login.AuthInfoDTO;
import finalProject.finalProject.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class UserLoginService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    public void execute(LoginCommand loginCommand, HttpSession session, BindingResult result){
    String userId = loginCommand.getUserId();
    String userPw = loginCommand.getUserPw();
    AuthInfoDTO dto = userMapper.loginSelect(userId);
    if(userId != "" && userId != null){
        if (dto != null) {
             if(passwordEncoder.matches(userPw,dto.getUserPw())){
                 System.out.println("비밀번호가 일치");
                 session.setAttribute("auth",dto);
             }else{
                 System.out.println("비밀번호가 일치하지 않을 떄");
                 result.rejectValue("userPw", "loginCommand.userPw",
                         "비밀번호가 틀렸습니다.");
             }

        }else{

               System.out.println("아이디가 존재하지 않을 떄");
               result.rejectValue("userId","loginCommand.userId","아이디가 존재하지 않는다.");
        }
    }
    }
}
