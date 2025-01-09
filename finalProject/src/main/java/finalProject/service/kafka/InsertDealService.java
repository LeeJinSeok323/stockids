package finalProject.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import finalProject.domain.KafkaStockDTO;
import finalProject.mapper.KafkaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class InsertDealService {
    @Autowired
    KafkaMapper kafkaMapper;
    public Integer execute(String jsonString){
        ObjectMapper objectMapper = new ObjectMapper();
        KafkaStockDTO dto = new KafkaStockDTO();
        try{
            dto = objectMapper.readValue(jsonString, KafkaStockDTO.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        String timeCode = getTimeCode(dto.getTimestamp());
        dto.setTimeCode(timeCode);
        kafkaMapper.updateTimeCode(timeCode);
        kafkaMapper.insertDealOne(dto);
        return 1;
    }
    public static String getTimeCode(String timestamp){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String date = today.format(formatter);
        return "T"+date+timestamp;
    }
}
