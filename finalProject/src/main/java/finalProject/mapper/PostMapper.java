package finalProject.mapper;

import finalProject.domain.StartEndPageDTO;
import finalProject.domain.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    public void postInsert(PostDTO dto);
    public List<PostDTO> postSelectList(StartEndPageDTO sepDTO);
    public int postCount();
    public PostDTO postSelectOne(String postNum);
    public void postUpdate(PostDTO dto);
    public void postDelete(String postNum);
    public int postViewCountUpdate(String goodsNum);
}