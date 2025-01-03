package finalProject.service.login;
import finalProject.domain.AuthInfoDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;


@Service
public class SessionCheckService {

    public boolean execute(HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");

        if (auth != null) {
            String userId = auth.getUserId();

            if (userId != null && !userId.isEmpty()) {
                return true;
            }
        }

        return false;
    }
}
