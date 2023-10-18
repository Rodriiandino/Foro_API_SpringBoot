package com.one.foroapi.util;

import java.time.LocalDateTime;

public class TimeLimit {
    public static boolean isEditableWithinTimeLimit(LocalDateTime createdAt) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timeLimit = createdAt.plusMinutes(15);
        return now.isBefore(timeLimit);
    }
}
