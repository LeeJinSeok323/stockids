package finalProject.service.admin;

import finalProject.mapper.AdminMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminDeleteService {
    @Autowired
    AdminMapper adminMapper;
    public String execute(String adminNum, HttpServletRequest req){
//        HttpSession session = request.getSession();
//        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("Auth");

        int i = 0;
        i = adminMapper.deleteAdmin(adminNum);
        if(i != 1)
            return "200";
        else
            return "400";
    }

}
