package com.samvel.spring.TestTask.services;

import com.samvel.spring.TestTask.exceptions.StartDateIsAfterEndDateException;
import com.samvel.spring.TestTask.models.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {
    private CalculatorService calculatorService = new CalculatorService();

    @Test
    void testDateFormatError() {
        assertThrows(DateTimeParseException.class, () -> {
            calculatorService.countHolidayPay(222, "asda", "asdasd");
        });
    }

    @Test
    void testVacationsDaysWithHolidays() {
        int vacationDays = calculatorService.countVacationDays(
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 22));
        assertEquals(10, vacationDays);
    }

    @Test
    void testVacationDaysWithoutHolidays() {
        int vacationDays = calculatorService.countVacationDays(
                LocalDate.of(2024, 2, 1),
                LocalDate.of(2024, 2, 10));
        assertEquals(7, vacationDays);
    }

    @Test
    void testStartAndAfterDate() {
        assertThrows(StartDateIsAfterEndDateException.class, () -> {
            calculatorService.countHolidayPay(1000, "01-02-2024", "01-02-2023");
        });
    }
}