package com.online.ecommerce.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class ApiResponse {
    private final Boolean success;
    private String message;

    public String getTimeStamp(){
        return LocalDateTime.now().toString();
    }
}
