package com.example.reward_program.dto;

import java.time.LocalDateTime;

public class ApiResponse {

    private Object content;
    private int businessCode;
    private LocalDateTime timestamp;

    public ApiResponse(Object content, int businessCode) {
        this.content = content;
        this.businessCode = businessCode;
        this.timestamp = LocalDateTime.now();
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public int getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(int businessCode) {
        this.businessCode = businessCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}