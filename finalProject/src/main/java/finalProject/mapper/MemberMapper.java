package finalProject.mapper;

import finalProject.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    public void memberInsert(MemberDTO dto);
    public String memberAutoNum();
    public void memberUpdate(MemberDTO dto);
    public List<MemberDTO> selectAll(String searchWord);
    public List<MemberDTO> selectAllWithTitle(String searchWord);
    public void updateMemberTitle(@Param("memberNum") String memberNum, @Param("titleNum") String titleNum);
    void updateMemberInMemberTable(String memberNum, String titleNum);
}
