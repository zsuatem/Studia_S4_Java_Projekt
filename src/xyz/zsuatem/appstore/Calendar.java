package xyz.zsuatem.appstore;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Calendar {
    private LocalDate date = LocalDate.of(2020, 1, 1);

    public LocalDate getDate() {
        return date;
    }

    public String getFormattedDate() {
        String formattedDate = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        return formattedDate;
    }

    public void nextDay() {
        date = date.plusDays(1);
    }

    public DayOfWeek getDayOfWeek() {
        return DayOfWeek.from(date);
    }
}
