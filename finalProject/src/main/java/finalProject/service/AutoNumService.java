package finalProject.service;


import finalProject.mapper.AutoNumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoNumService {
    @Autowired
    AutoNumMapper autoNumMapper;

    public String execute(String sep, String columnName, Integer len
            ,String tableName) {
        String autoNum = autoNumMapper.autoNumSelect(sep, columnName, len, tableName);
        return autoNum;
    }
}
