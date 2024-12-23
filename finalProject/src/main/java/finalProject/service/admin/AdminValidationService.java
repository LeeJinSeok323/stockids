package finalProject.service.admin;

import finalProject.command.AdminCommand;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.View;

@Service
public class AdminValidationService {
    private final View error;

    public AdminValidationService(View error) {
        this.error = error;
    }

    public String execute(AdminCommand adminCommand, BindingResult bindingResult, Model model){
        if(!adminCommand.isEqualPwAndPwCon()){
            bindingResult.rejectValue("adminPwCon", "differentPw" ,"비밀번호가 일치하지 않습니다.");
        }
        if(!adminCommand.isSelectedPosition()){
            bindingResult.rejectValue("position", "selectPosition","직무를 선택해주세요");
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("adminCommand", adminCommand);
            return "thymeleaf/admin/adminForm";
        }
        return "pass";
    }
}
