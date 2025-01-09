package finalProject.mapper;

import finalProject.domain.InquireDTO;
import finalProject.domain.StartEndPageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface InquireMapper {
    public List<InquireDTO> inquireSelectList(StartEndPageDTO sepDTO);
    public int inquireCount();
    public InquireDTO inquireSelectOne(String inquireNum);


}
