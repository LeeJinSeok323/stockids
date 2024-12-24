package finalProject.mapper;

import finalProject.domain.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public void memberInsert(MemberDTO dto);
    public String memberAutoNum();
}
