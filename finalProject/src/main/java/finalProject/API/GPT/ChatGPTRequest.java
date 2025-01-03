package finalProject.API.GPT;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGPTRequest {
    String model;
    List<Map<String, String>> messages;
    float temperature;

    public String toJSONString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            throw new RuntimeException("Error converting ChatGPTRequest to JSON string", e);
        }
    }
}
