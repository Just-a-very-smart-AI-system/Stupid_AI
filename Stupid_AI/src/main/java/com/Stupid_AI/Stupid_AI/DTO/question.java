package com.Stupid_AI.Stupid_AI.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class question {
    private String role;
    private String content;
}
