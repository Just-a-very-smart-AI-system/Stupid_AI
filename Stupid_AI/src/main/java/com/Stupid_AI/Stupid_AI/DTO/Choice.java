package com.Stupid_AI.Stupid_AI.DTO;

import lombok.Data;

@Data
public class Choice {
    private int index;
    private question message;
    private String text;
    private String finish_reason;
}