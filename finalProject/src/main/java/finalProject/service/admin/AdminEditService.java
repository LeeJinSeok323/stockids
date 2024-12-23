package finalProject.service.admin;

import finalProject.command.AdminCommand;
import finalProject.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminEditService {
    @Autowired
    AdminMapper adminMapper;
    public String execute(AdminCommand adminCommand){
        return "";
    }
}
