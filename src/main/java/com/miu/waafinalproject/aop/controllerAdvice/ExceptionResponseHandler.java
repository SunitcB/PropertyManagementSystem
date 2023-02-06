package com.miu.waafinalproject.aop.controllerAdvice;

import com.miu.waafinalproject.model.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionResponseHandler {
    private ResponseModel responseModel;

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ResponseModel> handleErrorResponse(Exception e) {
        responseModel = new ResponseModel();
        responseModel.setMessage("The server is unable to respond properly.");
        responseModel.setData(e.getStackTrace());
        responseModel.setStatus(HttpStatus.BAD_REQUEST);
        System.out.println(e);
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
    }
}
