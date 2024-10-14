package com.Stupid_AI.Stupid_AI.DTO.Request;

import com.Stupid_AI.Stupid_AI.DTO.question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequest {
    private String model;
    private List<question> messages;
    private int Max_token;
    private double temperature;
}
