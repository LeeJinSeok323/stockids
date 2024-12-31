package finalProject.service.craw;

import com.fasterxml.jackson.databind.ObjectMapper;
import finalProject.domain.craw.CrawInformation;
import finalProject.domain.craw.CrawItem;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CrawService {

    private static final String CLIENT_ID = "id";
    private static final String CLIENT_SECRET = "key";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<CrawItem> fetchNews(String query) throws IOException {
        List<CrawItem> allFilteredItems = new ArrayList<>();
        String encodedQuery = URLEncoder.encode(query, "UTF-8");

        for (int start = 1; start <= 10; start += 100) {
            String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + encodedQuery + "&display=100&start=" + start;

            Map<String, String> requestHeaders = new HashMap<>();
            requestHeaders.put("X-Naver-Client-Id", CLIENT_ID);
            requestHeaders.put("X-Naver-Client-Secret", CLIENT_SECRET);

            String responseBody = get(apiURL, requestHeaders);

            CrawInformation info = objectMapper.readValue(responseBody, CrawInformation.class);

            for (CrawItem item : info.items) {
                if (item.link.startsWith("https://n.news.naver.com/mnews/article")) {
                    String body = CrawBodyService(item.link);
                    item.body = body;
                    allFilteredItems.add(item);
                }
            }
        }
        return allFilteredItems;
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return readBody(con.getInputStream());
            } else {
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

    public String CrawBodyService(String urlLink) throws IOException {
        Document doc = Jsoup.connect(urlLink).get();
        Element contentElement = doc.getElementsByClass("newsct_article").first();

        if (contentElement != null) {
            String content = contentElement.text();
            return content;
        } else {
            System.out.println("에러 기사내용 없음");
            return null;
        }
    }
}