package Helper;

import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class CounterApi {
    private String apiKey;
    private String apiUrl;

    public  CounterApi(String apiKey, String apiUrl) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
    }

    public int increaseCounter(String counterId) {
        try {
            URL url = new URL(apiUrl + "/counter?id=" + counterId + "&hit=true");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Api-Key", apiKey);
            connection.setRequestProperty("accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                InputStream responseStream = connection.getInputStream();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode responseJson = mapper.readTree(responseStream);
                return responseJson.get("value").asInt();
            } else {
                System.out.println("Error: HTTP " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }


    public static void increaseCounter() { //usa quesat per increasare
        String apiKey = "SqX+VG70uOWwJ6wIX2Qrrg==T5fNB0NDZIlvjWw6";
        String apiUrl = "https://api.api-ninjas.com/v1/counter?id=test_id&hit=true";
        CounterApi counterAPI = new  CounterApi(apiKey, apiUrl);

        String counterId = "COUNTERMIO324534";

        int increasedValue = counterAPI.increaseCounter(counterId);
        System.out.println("Increased Value: " + increasedValue);

    }
}