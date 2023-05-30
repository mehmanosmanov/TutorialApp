package org.tutorial.app.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tutorial.app.dto.response.ErrorMessage;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(NotFoundException ex) {
        log.info(ex.getMessage(),ex);
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setDate(LocalDateTime.now());
        errorMessage.setStatus(ex.getStatus());
        errorMessage.setErrorCode(ex.getStatus().value());
        errorMessage.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(errorMessage);
    }

    @ExceptionHandler(TutorialAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleException(TutorialAlreadyExistsException ex) {
        log.info(ex.getMessage(),ex);
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setDate(LocalDateTime.now());
        errorMessage.setStatus(ex.getStatus());
        errorMessage.setErrorCode(ex.getStatus().value());
        errorMessage.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(errorMessage);
    }
}
