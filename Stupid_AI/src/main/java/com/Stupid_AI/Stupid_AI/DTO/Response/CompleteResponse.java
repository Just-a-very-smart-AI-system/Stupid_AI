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
public class CompleteResponse {
    private String id;
    private String object;
    private String model;
    private long created;
    private List<Choice> choices;
    private Usage usage;
}
