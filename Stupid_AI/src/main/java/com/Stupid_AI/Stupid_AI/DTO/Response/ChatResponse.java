package com.Stupid_AI.Stupid_AI.DTO.Response;

import com.Stupid_AI.Stupid_AI.DTO.Choice;
import com.Stupid_AI.Stupid_AI.DTO.Usage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;
}