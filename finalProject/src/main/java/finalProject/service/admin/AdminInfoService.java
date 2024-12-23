package finalProject.service.admin;

import finalProject.domain.AdminDTO;
import finalProject.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AdminInfoService {
    @Autowired
    AdminMapper adminMapper;
    public AdminDTO execute(String adminNum){
        AdminDTO dto = adminMapper.selectAdminOne(adminNum);
        Date birth = dto.getBirth();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            birth = sdf.parse(sdf.format(birth));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        dto.setBirth(birth);

        return dto;
    }
}
