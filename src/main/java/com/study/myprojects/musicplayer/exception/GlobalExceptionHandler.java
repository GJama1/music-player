package com.study.myprojects.musicplayer.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionBody> handleException(Exception e, HttpServletRequest request) {

        log.error("Request: {}, threw an exception. Message: {}.", request.getRequestURI(), e.getMessage());
        e.printStackTrace();

        ExceptionBody exceptionBody = new ExceptionBody(
                Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                e.getMessage(),
                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionBody);

    }

}
