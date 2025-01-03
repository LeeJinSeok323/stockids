package finalProject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface PostLikeMapper {
    int checkPostLike(@Param("postNum") String postNum
            , @Param("memberNum") String memberNum);
    void addPostLike(@Param("postNum") String postNum
            , @Param("memberNum") String memberNum);
    void removePostLike(@Param("postNum") String postNum
            , @Param("memberNum") String memberNum);
    void updateLikeCount(@Param("postNum") String postNum);
    void cancelLikeCount(@Param("postNum") String postNum);
    String getLikeCount(@Param("postNum") String postNum);
}
