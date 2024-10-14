package com.Stupid_AI.Stupid_AI.Service;

import com.Stupid_AI.Stupid_AI.DTO.Chat;
import com.Stupid_AI.Stupid_AI.DTO.mess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiService {

    private final String BASE_URL = "http://localhost:1234"; // Thay đổi nếu cần

    @Autowired
    private RestTemplate restTemplate;

    // Gọi API /v1/models
    public Object callModels() {
        String url = BASE_URL + "/v1/models";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // Gọi API
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, Object.class).getBody();
    }
    // Gọi API /v1/chat/completions
    public Object callChatCompletions(String prompt) {
        String url = BASE_URL + "/v1/chat/completions";

        // Tạo headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Tạo body với các trường cần thiết
        Chat requestBody = new Chat();
        requestBody.setMax_token(100);
        requestBody.setModel("llama-3.2-1b-instruct");
        mess system = new mess("system", "You are a helpful assistant.");
        mess user = new mess("user", prompt);
        List<mess> messes = new ArrayList<>();
        messes.add(system);
        messes.add(user);
        requestBody.setMessages(messes);
        requestBody.setTemperature(1);

        HttpEntity<Chat> requestEntity = new HttpEntity<>(requestBody, headers);

        // Gọi API
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class).getBody();
    }


    // Gọi API /v1/completions
    public String callCompletions(String prompt) {
        String url = BASE_URL + "/v1/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{ \"prompt\": \"" + prompt + "\", \"max_tokens\": 50 }";

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }

    // Gọi API /v1/embeddings
    public String callEmbeddings(String inputText) {
        String url = BASE_URL + "/v1/embeddings";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{ \"input\": \"" + inputText + "\" }";

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }
}
