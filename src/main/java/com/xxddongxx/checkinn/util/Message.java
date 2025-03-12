package com.xxddongxx.checkinn.util;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Message<T> {
    private final HttpStatus status;
    private final String message;
    private final T data;

    @Builder
    public Message(HttpStatus status, String message, T data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> Message<T> success(T data){
        return Message.<T>builder()
                .status(HttpStatus.OK)
                .message("요청이 성공적으로 처리되었습니다.")
                .data(data)
                .build();
    }

    public static <T> Message<T> success(String message, T data) {
        return Message.<T>builder()
                .status(HttpStatus.OK)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> Message<T> error(HttpStatus status, String message) {
        return Message.<T>builder()
                .status(status)
                .message(message)
                .build();

    }
}
