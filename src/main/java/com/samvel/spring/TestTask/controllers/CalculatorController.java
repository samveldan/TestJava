package com.samvel.spring.TestTask.controllers;

import com.samvel.spring.TestTask.models.CalculationResultResponse;
import com.samvel.spring.TestTask.services.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("api/calculator/")
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/calculate")
    public ResponseEntity<CalculationResultResponse> calculate(@RequestParam("avg_salary") double avgSalary,
                                                               @RequestParam("start_day") String startDay,
                                                               @RequestParam("end_day") String endDay) {
        String holidayPayResult = calculatorService.countHolidayPay(avgSalary, startDay, endDay);
        return new ResponseEntity<>(new CalculationResultResponse(holidayPayResult), HttpStatus.OK);
    }
}
