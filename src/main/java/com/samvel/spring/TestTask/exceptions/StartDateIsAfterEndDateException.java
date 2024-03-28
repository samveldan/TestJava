package com.samvel.spring.TestTask.exceptions;

public class StartDateIsAfterEndDateException extends RuntimeException{
    public StartDateIsAfterEndDateException(String message) {
        super(message);
    }
}
