package finalProject.service.post;

import finalProject.command.PostCommand;
import finalProject.mapper.PostLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostLikeService {

    @Autowired
    PostLikeMapper postLikeMapper;

    public String execute(PostCommand postCommand, String memberNum) {
        String postNum = postCommand.getPostNum();
        System.out.println("Received postNum: " + postNum);
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


