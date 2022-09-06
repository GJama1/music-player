package com.study.myprojects.musicplayer.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

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

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionBody> handleResponseStatusException(ResponseStatusException e, HttpServletRequest request) {

        ExceptionBody exceptionBody = new ExceptionBody(
                Integer.toString(e.getStatus().value()),
                e.getReason(),
                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                request.getRequestURI()
        );

        return ResponseEntity.status(e.getStatus()).body(exceptionBody);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionBody> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {

        String message = e.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));

        ExceptionBody exceptionBody = new ExceptionBody(
                Integer.toString(HttpStatus.BAD_REQUEST.value()),
                message,
                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionBody);

    }

}
