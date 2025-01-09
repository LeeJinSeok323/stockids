package finalProject.mapper;

import finalProject.domain.InquireDTO;
import finalProject.domain.MemberDTO;
import finalProject.domain.StartEndPageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface InquireMapper {
    public List<InquireDTO> inquireSelectList(StartEndPageDTO sepDTO);
    public int inquireCount(StartEndPageDTO sepDTO);
    public InquireDTO inquireSelectOne(String inquireNum);
    MemberDTO getMemberInfo(String userId);
    MemberDTO getMemberInfoByNum(String memberNum);
    // 등록 처리 메서드 추가
    public void insertInquire(InquireDTO inquireDTO);
    public String findAuthorldByInquireNum(String inquireNum);
}
