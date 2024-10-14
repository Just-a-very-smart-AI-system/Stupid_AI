package com.Stupid_AI.Stupid_AI.Service;

import com.Stupid_AI.Stupid_AI.DTO.*;
import com.Stupid_AI.Stupid_AI.DTO.Request.ChatRequest;
import com.Stupid_AI.Stupid_AI.DTO.Request.CompleteRequest;
import com.Stupid_AI.Stupid_AI.DTO.Response.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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

    public Choice callChatCompletions(String prompt) {
        String url = BASE_URL + "/v1/chat/completions";

        // Tạo headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Tạo body với các trường cần thiết
        ChatRequest requestBody = new ChatRequest();
        requestBody.setMax_token(100);
        requestBody.setModel("llama-3.2-1b-instruct");
        question system = new question("system", "You are a helpful assistant.");
        question user = new question("user", prompt);
        List<question> messes = new ArrayList<>();
        messes.add(system);
        messes.add(user);
        requestBody.setMessages(messes);
        requestBody.setTemperature(0.7);

        HttpEntity<ChatRequest> requestEntity = new HttpEntity<>(requestBody, headers);

        // Gọi API và ánh xạ JSON response vào ChatResponse
        ResponseEntity<ChatResponse> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ChatResponse.class);
        List<Choice> choices = response.getBody().getChoices();
        return choices.stream().toList().getFirst();
        // Trả về ChatResponse
//        return response.getBody();
    }


    // Gọi API /v1/completions
    public String callCompletions(String prompt) {
        String url = BASE_URL + "/v1/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        CompleteRequest requestBody = new CompleteRequest();
        requestBody.setModel("llama-3.1-8b-instruct");
        requestBody.setN(1);
        requestBody.setStop("\n");
        requestBody.setPrompt(prompt);
        requestBody.setStream(false);
        requestBody.setMax_tokens(50);
        requestBody.setTop_p(1);
        requestBody.setTemperature(1.0);


        HttpEntity<CompleteRequest> requestEntity = new HttpEntity<>(requestBody, headers);

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
