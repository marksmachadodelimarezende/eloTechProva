package com.elotech.scp.useful;

import java.time.LocalDate;

public class DateUseful {

    public static LocalDate getNow() {
        return LocalDate.now();
    }

    public static boolean isDateAfterNow(LocalDate date) {
        return date.isAfter(getNow());
    }

    public static boolean isValidDate(LocalDate value) {
        return value != null;
    }

}
