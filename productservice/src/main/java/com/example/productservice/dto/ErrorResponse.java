package com.example.productservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;
    private String path;
    private long timestamp;
    private String correlationId;

    public ErrorResponse(String message, int status, String path, long timestamp, String corrId) {
        this.message = message;
        this.status = status;
        this.path = path;
        this.timestamp = timestamp;
        this.correlationId= corrId;
    }
}
