package ru.javawebinar.topjava.web.functions;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dates {

    private Dates() {}

    /**
     * Function for formatting LocalDateTime object.
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}
