package finalProject.API.StockInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class StockInfoAPI {
    private static final String BASE_URL = "";
    private static final String SERVICE_KEY = "";

    private String numOfRows;
    private String pageNo;
    private String resultType = "json"; // Default XML
    private String basDt;
    private String itmsNm;

    public void setItmsNm(String itmsNm) {
        this.itmsNm = itmsNm;
    }

    public void setBasDt(String basDt) { this.basDt = basDt; }
    public void setNumOfRows(String numOfRows) { this.numOfRows = numOfRows; }
    public void setPageNo(String pageNo) { this.pageNo = pageNo; }

    private String buildQuery() {
        try{
            StringBuilder query = new StringBuilder(BASE_URL + "?serviceKey=" + URLEncoder.encode(SERVICE_KEY, "UTF-8"));
            if (numOfRows != null) query.append("&numOfRows=").append(numOfRows);
            if (pageNo != null) query.append("&pageNo=").append(pageNo);
            query.append("&resultType=").append(resultType);
            if (basDt != null) query.append("&basDt=").append(URLEncoder.encode(basDt, "UTF-8"));
            if (itmsNm != null) query.append("&itmsNm=").append(URLEncoder.encode(itmsNm, "UTF-8"));
            return query.toString();
        } catch (Exception e){
            System.out.println("API call failed with response code: " + e.getMessage());
            return null;
        }
    }

    public StringBuilder executeApi() {
        try {
            String query = buildQuery();
            URL url = new URL(query);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();
                return response;
            } else {
                throw new Exception("API call failed with response code: " + responseCode);
            }
        } catch (Exception e){
            System.out.println("API call failed with response code: " + e.getMessage());
            return null;
        }
    }
}