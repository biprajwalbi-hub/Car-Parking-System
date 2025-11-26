package com.smartparking.app;

import java.time.*;

public final class TimeUtils {
    private TimeUtils() {}
    public static LocalDateTime now() { return LocalDateTime.now(); }
    public static long minutesBetween(LocalDateTime a, LocalDateTime b) {
        return Duration.between(a, b).toMinutes();
    }
}