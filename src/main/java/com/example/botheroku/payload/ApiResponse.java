package com.example.botheroku.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class ApiResponse {

    private String message;

    private boolean success;

    private Object data;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
