package com.samvel.spring.TestTask.controllers;

import com.samvel.spring.TestTask.exceptions.NegativeSalaryException;
import com.samvel.spring.TestTask.exceptions.StartDateIsAfterEndDateException;
import com.samvel.spring.TestTask.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.Date;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorResponse> dateException(DateTimeParseException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getParsedString() + " - некорректная дата", new Date()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NegativeSalaryException.class)
    public ResponseEntity<ErrorResponse> salaryException(NegativeSalaryException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), new Date()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(StartDateIsAfterEndDateException.class)
    public ResponseEntity<ErrorResponse> startDateException(StartDateIsAfterEndDateException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), new Date()), HttpStatus.FORBIDDEN);
    }
}
