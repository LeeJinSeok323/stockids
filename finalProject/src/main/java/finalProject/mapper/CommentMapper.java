package finalProject.mapper;

import finalProject.domain.CommentDTO;
import finalProject.domain.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    public void commentInsert(CommentDTO commentDTO);
    public List<CommentDTO> commentSelectList(String postNum);
    public String getPostAuthorByPostNum(String postNum);
}
