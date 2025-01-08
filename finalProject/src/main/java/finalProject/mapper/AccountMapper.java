package finalProject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {
    public void pointCharge(@Param("point") Integer point, @Param("userNum") String uesrNum);
    public Integer checkPoint (String userNum);
    public Integer pointUpdate (@Param("point") Integer point, @Param("userNum") String uesrNum);
    public void purchasePointUpdate (@Param("point") Integer point, @Param("userNum") String uesrNum);
}
