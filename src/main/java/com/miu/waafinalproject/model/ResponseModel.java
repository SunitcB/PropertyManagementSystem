package com.miu.waafinalproject.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseModel {
    private Object data;
    private HttpStatus status;
    private String message;
}
