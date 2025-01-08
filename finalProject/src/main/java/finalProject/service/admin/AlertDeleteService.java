package finalProject.service.admin;

import finalProject.mapper.AlertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertDeleteService {

    @Autowired
    AlertMapper alertMapper;
    public void execute(String alert[]){
        alertMapper.alertDelete(alert);

    }
}
