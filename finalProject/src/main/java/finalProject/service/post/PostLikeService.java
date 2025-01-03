package finalProject.service.post;

import finalProject.mapper.PostLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostLikeService {

    @Autowired
    PostLikeMapper postLikeMapper;

    public String execute(String postNum, String memberNum) {
        int likeStatus = postLikeMapper.checkPostLike(postNum, memberNum);

        if (likeStatus == 0) {
            postLikeMapper.addPostLike(postNum, memberNum);
            postLikeMapper.updateLikeCount(postNum);
            return postLikeMapper.getLikeCount(postNum);
        } else {
            postLikeMapper.removePostLike(postNum, memberNum);
            postLikeMapper.cancelLikeCount(postNum);
            return postLikeMapper.getLikeCount(postNum);
        }
    }
}


