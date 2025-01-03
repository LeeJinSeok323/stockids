package finalProject.mapper;

import finalProject.domain.AdminDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    public Integer insertAdmin(AdminDTO adminDTO);
    public List<AdminDTO> selectAdminList();
    public AdminDTO selectAdminOne(String adminNum);
   public String getAdminNum(String adminId);
    public Integer deleteAdmin(String adminNum);
}
