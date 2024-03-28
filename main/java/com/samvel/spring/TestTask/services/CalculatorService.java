package com.samvel.spring.TestTask.services;

import com.samvel.spring.TestTask.exceptions.NegativeSalaryException;
import com.samvel.spring.TestTask.exceptions.StartDateIsAfterEndDateException;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Service
public class CalculatorService {
    private final double avgDaysInOneMonth = 29.3;

    public String countHolidayPay(double avgSalary, String startDay, String endDay) {
        if (avgSalary < 0) throw new NegativeSalaryException("Вы ввели отрицательную зарплату");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate startDayDate = LocalDate.parse(startDay, dateFormat);
        LocalDate endDayDate = LocalDate.parse(endDay, dateFormat);
        if(startDayDate.isAfter(endDayDate)) throw new StartDateIsAfterEndDateException("Start date больше End date");

        return String.format(
                Locale.US,
                "%.2f",
                (avgSalary / this.avgDaysInOneMonth) * countVacationDays(startDayDate, endDayDate)
        );
    }

    public int countVacationDays(LocalDate start, LocalDate end) {
        int totalDays = 0;
        List<MonthDay> holdiays = returnAllHolidays();

        while(!start.isAfter(end)) {
            if((start.getDayOfWeek() == DayOfWeek.SATURDAY || start.getDayOfWeek() == DayOfWeek.SUNDAY) || (holdiays.contains(MonthDay.of(start.getMonthValue(), start.getDayOfMonth())))) {
                start = start.plusDays(1);
                continue;
            }

            totalDays += 1;
            start = start.plusDays(1);
        }

        return totalDays;
    }

    private List<MonthDay> returnAllHolidays() {
        List<MonthDay> holdiays = new ArrayList<>();

        // Новогодние праздники
        holdiays.add(MonthDay.of(1, 1));
        holdiays.add(MonthDay.of(1, 2));
        holdiays.add(MonthDay.of(1, 3));
        holdiays.add(MonthDay.of(1, 4));
        holdiays.add(MonthDay.of(1, 5));
        holdiays.add(MonthDay.of(1, 6));
        holdiays.add(MonthDay.of(1, 8));

        // Рождество Христово
        holdiays.add(MonthDay.of(1, 7));

        // День защитника отечества
        holdiays.add(MonthDay.of(2, 23));

        // Международный женский день
        holdiays.add(MonthDay.of(3, 8));

        // Праздник Весны и Труда
        holdiays.add(MonthDay.of(5, 1));

        // День Победы
        holdiays.add(MonthDay.of(5, 9));

        // День России
        holdiays.add(MonthDay.of(6, 12));

        // День народного единства
        holdiays.add(MonthDay.of(11, 4));

        return holdiays;
    }
}
