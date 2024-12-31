package finalProject.service.admin;

import finalProject.domain.AdminDTO;
import finalProject.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminListService {
    @Autowired
    AdminMapper adminMapper;
    public List<AdminDTO> execute(){
        List<AdminDTO> list = adminMapper.selectAdminList();
        return list;
    }


}
