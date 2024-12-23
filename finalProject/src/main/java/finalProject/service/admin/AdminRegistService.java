package finalProject.service.admin;

import finalProject.command.AdminCommand;
import finalProject.domain.AdminDTO;
import finalProject.mapper.AdminMapper;
import finalProject.mapper.AutoNumMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminRegistService {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    AutoNumMapper autoNumMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    public void execute(AdminCommand adminCommand) {
        AdminDTO dto = new AdminDTO();
        BeanUtils.copyProperties(adminCommand, dto);
        String adminNum = autoNumMapper.autoNumSelect("adm_", "admin_num", 5, "admin");
        dto.setAdminNum(adminNum);

        String encodePw = passwordEncoder.encode(adminCommand.getAdminPw());
        dto.setAdminPw(encodePw);

        adminMapper.insertAdmin(dto);
    }
}
