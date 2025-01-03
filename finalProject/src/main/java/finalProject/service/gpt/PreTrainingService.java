package finalProject.service.gpt;

import com.fasterxml.jackson.databind.ObjectMapper;
import finalProject.API.GPT.GPTMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PreTrainingService {
    public List<Map<String, String>> execute(GPTType gptType) {
        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> message = new HashMap<>();
        ClassPathResource resource;
        switch (gptType){
            case Article:
                resource = new ClassPathResource("static/prompts/article-prompt.json");
                break;
            case Stock:
                resource = new ClassPathResource("static/prompts/stock-prompt.json");
                break;
            default:
                resource = new ClassPathResource("static/prompts/default-prompt.json");
                break;
        }

        ObjectMapper mapper = new ObjectMapper();
        try{
            String preTrainPrompt = new String(resource.getInputStream().readAllBytes(), "UTF-8");
            GPTMessage message1 = mapper.readValue(preTrainPrompt, GPTMessage.class);
            message.put("role", message1.getRole());
            message.put("content", message1.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        messages.add(message);
        return messages;
    }

}
