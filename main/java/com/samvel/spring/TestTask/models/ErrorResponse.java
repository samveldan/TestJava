package com.samvel.spring.TestTask.models;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponse {
    private String message;
    private Date date;

    public ErrorResponse(String m, Date d) {
        this.message = m;
        this.date = d;
    }
}
