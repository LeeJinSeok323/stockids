package finalProject.service.gpt;

import com.fasterxml.jackson.databind.ObjectMapper;
import finalProject.API.GPT.ChatGPTRequest;
import finalProject.API.GPT.ChatGPTResponse;
import finalProject.API.GPT.GPTApiClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticlePredictionService {
    @Value("#{secret['gpt-api-key']}")
    private String apikey;
    private final String model;
    ObjectMapper mapper;
    public ArticlePredictionService(){
        if(apikey == null){
            System.out.println("apikey를 찾을 수 없습니다.");
        }
        model = "gpt-4o-mini";
        mapper = new ObjectMapper();
    }

    @Autowired
    PreTrainingService preTrainingService;
    public void execute() {
        GPTApiClient client = new GPTApiClient(apikey);
        List<Map<String, String>> messages = preTrainingService.execute(GPTType.Article);
        Map<String, String> message = new HashMap<>();
        message.put("role","user");
        message.put("content","[CES 2025]삼성전자, 구글과 개발한 '3D 오디오 기술' 탑재 TV 공개\n");

        messages.add(message);
        System.out.println("messages: " + messages.toString());

        ChatGPTRequest request = new ChatGPTRequest(model, messages, 0.7f);
        JSONObject json = client.sendChatCompletionRequest(request);
        ChatGPTResponse response = new ChatGPTResponse();
        try {
            response = mapper.readValue(json.toString(), ChatGPTResponse.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("결과값은"+response.getChoices().get(0).getMessage().getContent().substring(4));
    }


}
