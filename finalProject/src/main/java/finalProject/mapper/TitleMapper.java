package finalProject.mapper;

import finalProject.domain.StartEndPageDTO;
import finalProject.domain.TitleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TitleMapper {
    String titleAutoNum(@Param("tableName") String tableName,
                        @Param("columnName") String columnName,
                        @Param("sep") String sep,
                        @Param("padding") int padding);

    int titleInsert(TitleDTO dto);
    public List<TitleDTO> allSelect(StartEndPageDTO sepDTO);
    public int titleCount(String searchWord);
    public int titleDelete(@Param("title") String title[]);
    public List<TitleDTO> getAllTitles();
}
