package com.Stupid_AI.Stupid_AI.Controller;

import com.Stupid_AI.Stupid_AI.Service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @PostMapping("/chat")
    public Object chat(@RequestBody String prompt) {
        return apiService.callChatCompletions(prompt);
    }

    @PostMapping("/completions")
    public String completions(@RequestBody String prompt) {
        return apiService.callCompletions(prompt);
    }

    @PostMapping("/embeddings")
    public String embeddings(@RequestBody String inputText) {
        return apiService.callEmbeddings(inputText);
    }
    @GetMapping("/models")
    public Object models() {
        Object chat = apiService.callModels();

        return apiService.callModels();
    }
}
