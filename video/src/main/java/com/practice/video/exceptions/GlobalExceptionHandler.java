package com.practice.video.exceptions;

import com.practice.video.exceptions.errorTypes.GameIsAlreadyExistException;
import com.practice.video.exceptions.errorTypes.GameNotFoundException;
import com.practice.video.exceptions.responses.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessage>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> validationErrors = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        List<ErrorMessage> responses = validationErrors.stream()
                .map(ErrorMessage::new)
                .collect(Collectors.toList());
        return ResponseEntity.status(400).body(responses);
    }

    @ExceptionHandler(GameIsAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> handleGameIsAlreadyExistException(GameIsAlreadyExistException ex) {
        ErrorMessage response = new ErrorMessage();
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(403).body(response);
    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleGameNotFoundException(GameNotFoundException ex) {
        ErrorMessage response = new ErrorMessage();
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(404).body(response);
    }
}
