package finalProject.service.move;

import org.springframework.stereotype.Service;

@Service
public class MoveService {

    public String execute(String moveWord){
        if(moveWord.equals("news")){
            return "news";
        } else if (moveWord.equals("home")) {
            return "home";
        } else if (moveWord.equals("stock")) {
            return "stock";
        } else if (moveWord.equals("rank")) {
            return "rank";
        } else {
            return "error";
        }
    }
}
