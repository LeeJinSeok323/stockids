package finalProject.API.GPT;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GPTApiClient {
    private static final Logger logger = LoggerFactory.getLogger(GPTApiClient.class);
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private final String apiKey;
    private final ObjectMapper mapper;

    public GPTApiClient(String apikey) {
        this.apiKey = apikey;
        this.mapper = new ObjectMapper();
    }

    public JSONObject sendChatCompletionRequest(ChatGPTRequest chatGPTRequest) {
        try {
            String requestBody = chatGPTRequest.toJSONString();
            System.out.println("리퀘스트 바디"+requestBody);
            System.out.println("Request Body: " +
                    mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                            mapper.readTree(requestBody)
                    )
            );
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                logger.error("API 요청 실패. 상태 코드: {}, 응답 본문: {}",
                        response.statusCode(), response.body());
                return new JSONObject("{}");
            }

            return new JSONObject(response.body());
        } catch (Exception e) {
            logger.error("API 요청 중 오류 발생: ", e);
            return new JSONObject("{}");
        }
    }
}