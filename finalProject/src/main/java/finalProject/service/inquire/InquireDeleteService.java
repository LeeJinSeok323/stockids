package finalProject.service.inquire;

import finalProject.mapper.InquireMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InquireDeleteService {
    @Autowired
    InquireMapper inquireMapper;
    public void execute(String inquireNum){
        inquireMapper.inquireDelete(inquireNum);
    }

}
