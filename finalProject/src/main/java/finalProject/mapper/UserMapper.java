package finalProject.mapper;

import finalProject.domain.AuthInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public AuthInfoDTO loginSelect(String userId);
}
