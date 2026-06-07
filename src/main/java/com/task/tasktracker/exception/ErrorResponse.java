package com.task.tasktracker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(int status,String message){
        this.status=status;
        this.message=message;
        this.timestamp = LocalDateTime.now();
    }
}
