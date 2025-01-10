package finalProject.service.gpt;

import com.fasterxml.jackson.databind.ObjectMapper;
import finalProject.API.GPT.ChatGPTRequest;
import finalProject.API.GPT.ChatGPTResponse;
import finalProject.API.GPT.GPTApiClient;
import finalProject.domain.ArticlePredictionDTO;
import finalProject.domain.article.ArticleDTO;
import finalProject.mapper.ArticleMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticlePredictionService {
    private String model;
    ObjectMapper mapper;
    GPTApiClient client;
    public ArticlePredictionService(@Value("#{secret['gpt-api-key']}") String apikey){
        if(apikey == null){
            System.out.println("apikey를 찾을 수 없습니다.");
        }
        model = "gpt-4o-mini";
        mapper = new ObjectMapper();
        client = new GPTApiClient(apikey);
    }
    @Autowired
    ArticleMapper articleMapper;
    public boolean execute(){
        System.out.println("기사예측시작");
        List<ArticleDTO> list = articleMapper.selectUnexpectedArticle();
        if(list.isEmpty()){
            return false;
        }
        for(ArticleDTO dto : list){
            predictOne(dto);
        }
        System.out.println("예측 완료");
        return true;
    }

    @Autowired
    PreTrainingService preTrainingService;
    public void predictOne(ArticleDTO dto) {
        ArticlePredictionDTO pdto = new ArticlePredictionDTO();
        List<Map<String, String>> messages = preTrainingService.execute(GPTType.Article);
        Map<String, String> message = new HashMap<>();
        message.put("role","user");
        message.put("content", dto.getArticleSubject());

        messages.add(message);
        System.out.println("messages: " + messages.toString());

        ChatGPTRequest request = new ChatGPTRequest(model, messages, 0.7f);

        JSONObject json = client.sendChatCompletionRequest(request);
        ChatGPTResponse response = new ChatGPTResponse();
        System.out.println(json.toString());
        try {
            response = mapper.readValue(json.toString(), ChatGPTResponse.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        String result = response.getChoices().get(0).getMessage().getContent();
        System.out.println("result: " + result);
        pdto.setArticlePredictionValue(Integer.parseInt(result.substring(0,1)));
        pdto.setArticlePredictionContents(result.substring(2));
        pdto.setGptModel(model);
        pdto.setArticlePredictionNum(dto.getArticleNum());

        int i = articleMapper.insertArticlePrediction(pdto);
        System.out.println(i+"개의 행이 삽입되었습니다.");
    }


}
