package com.jyotish.api.core.models.input;

import lombok.Data;

@Data
public class PersonalData {
    private String id;
    private String name;
    private Gender gender;
    private String remarks;

    public enum Gender {
        MALE, FEMALE, OTHERS;
    }
}
