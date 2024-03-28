package com.samvel.spring.TestTask.models;

import com.samvel.spring.TestTask.services.CalculatorService;
import lombok.Data;

import java.util.Date;

@Data
public class CalculationResultResponse {
    private String sum;
    private Date created_at;

    public CalculationResultResponse(String sum) {
        this.sum = sum;
        this.created_at = new Date();
    }
}
