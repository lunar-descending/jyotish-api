package com.jyotish.api.core.models.input;

import lombok.Data;

@Data
public class TimeOfBirth {
    private double timeZone;
    private int hour;
    private int minute;
    private int second;
    private boolean is24HourFormat;
}
