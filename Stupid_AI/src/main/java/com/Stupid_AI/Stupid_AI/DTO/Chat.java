package com.Stupid_AI.Stupid_AI.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    private String model;
    private List<mess> messages;
    private int Max_token;
    private double temperature;
}
