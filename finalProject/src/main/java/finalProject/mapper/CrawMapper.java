package finalProject.mapper;

import finalProject.domain.craw.CrawDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Mapper
public interface CrawMapper {
    public void craw(@Param("autuNum") String autuNum,
                    @Param("title") String title,
                     @Param("description") String description,
                     @Param("link") String link,
                     @Param("body") String body,
                     @Param("name") String name);

    public List<CrawDTO> crawList();
}
