package finalProject.service.admin;

import finalProject.mapper.TitleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TitleDeleteService {
   @Autowired
   TitleMapper titleMapper;
    public void execute(String title[]){
      titleMapper.titleDelete(title);

  }

}
