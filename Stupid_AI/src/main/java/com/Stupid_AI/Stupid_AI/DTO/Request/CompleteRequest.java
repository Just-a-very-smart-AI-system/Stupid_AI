package com.Stupid_AI.Stupid_AI.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteRequest {
    private String model;
    private String prompt;
    private int max_tokens;
    private double temperature;
    private int top_p;
    private int n;
    private Boolean stream;
    private String stop;
}
