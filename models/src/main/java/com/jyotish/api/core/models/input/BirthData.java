package com.jyotish.api.core.models.input;

import lombok.Data;

@Data
public class BirthData {
    private PersonalData personalData;
    private DateOfBirth dateOfBirth;
    private TimeOfBirth timeOfBirth;
    private PlaceOfBirth placeOfBirth;
}
