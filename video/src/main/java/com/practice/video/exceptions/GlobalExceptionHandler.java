package com.practice.video.exceptions;

import com.practice.video.exceptions.errorTypes.GameIsAlreadyExistException;
import com.practice.video.exceptions.responses.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorMessage response = new ErrorMessage();
        int numberOfErrors = ex.getBindingResult().getAllErrors().size();
        String errorCode = ex.getBindingResult().getFieldError().getCode();
        if (numberOfErrors == 1 && errorCode.equals("Size")) {
            response.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
            return ResponseEntity.status(406).body(response);
        } else if (numberOfErrors == 1 && errorCode.equals("NotNull") || errorCode.equals("NotBlank")) {
            response.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
            return ResponseEntity.status(400).body(response);
        }
        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(GameIsAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> handleGameIsAlreadyExistException(GameIsAlreadyExistException ex) {
        ErrorMessage response = new ErrorMessage();
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(403).body(response);
    }
}
