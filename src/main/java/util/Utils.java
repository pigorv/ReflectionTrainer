package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String convertDateToString(LocalDate date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        return date.format(formatter);
    }
}
