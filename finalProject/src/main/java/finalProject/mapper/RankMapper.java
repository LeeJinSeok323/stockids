package finalProject.mapper;

import finalProject.domain.rank.RankDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RankMapper {
    Integer rankInsert();
    List<RankDTO> rankSelect();
}
