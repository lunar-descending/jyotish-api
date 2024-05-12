package com.jyotish.api.core.models.input;

import lombok.Data;

@Data
public class PlaceOfBirth {
    private String country;
    private String place;
    private double timeZone;
    private double latitude;
    private double longitude;
}
