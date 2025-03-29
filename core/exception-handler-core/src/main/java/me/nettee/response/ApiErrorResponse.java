package me.nettee.response;

import lombok.Builder;

import java.util.Map;

@Builder
public record ApiErrorResponse(
        int status,
        String code,
        String message,
        Map<String, Object> payload
) {
    public ApiErrorResponse {
        if (payload != null && payload.isEmpty()) {
            payload = null;
        }
    }
}