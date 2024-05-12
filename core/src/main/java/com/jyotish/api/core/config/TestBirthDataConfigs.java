package com.jyotish.api.core.config;

import com.jyotish.api.core.models.input.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestBirthDataConfigs {
    
    @Bean("person1")
    public BirthData testBirthData1() {
        BirthData birthData = new BirthData();
        birthData.setPersonalData(getPersonalData("Person1", PersonalData.Gender.MALE));
        birthData.setPlaceOfBirth(getDefaultPlaceOfBirth());
        birthData.setTimeOfBirth(getTimeOfBirth(1, 47, 0, 5.5d));
        birthData.setDateOfBirth(getDateOfBirth(1999, 4, 19));
        return birthData;
    }

    @Bean("person2")
    public BirthData testBirthData2() {
        BirthData birthData = new BirthData();
        birthData.setPersonalData(getPersonalData("Person2", PersonalData.Gender.FEMALE));
        birthData.setPlaceOfBirth(getDefaultPlaceOfBirth());
        birthData.setTimeOfBirth(getTimeOfBirth(14, 30, 0, 5.5d));
        birthData.setDateOfBirth(getDateOfBirth(1998, 1, 2));
        return birthData;
    }

    @Bean("person3")
    public BirthData testBirthData3() {
        BirthData birthData = new BirthData();
        birthData.setPersonalData(getPersonalData("Person3", PersonalData.Gender.MALE));
        birthData.setPlaceOfBirth(getDefaultPlaceOfBirth());
        birthData.setTimeOfBirth(getTimeOfBirth(0, 30, 0, 5.5d));
        birthData.setDateOfBirth(getDateOfBirth(1969, 10, 29));
        return birthData;
    }

    private PlaceOfBirth getDefaultPlaceOfBirth() {
        //https://cities-latlon.onrender.com/search/subdivision/IN/NEW%20DELHI%20CENTRAL
        PlaceOfBirth placeOfBirth = new PlaceOfBirth();
        placeOfBirth.setTimeZone(5.5d);
        placeOfBirth.setPlace("Test-Place");
        placeOfBirth.setCountry("Test-Country");
        placeOfBirth.setLatitude(28.6453d);
        placeOfBirth.setLongitude(77.2456d);
        return placeOfBirth;
    }

    private TimeOfBirth getTimeOfBirth(int hour, int minute, int seconds, double timeZone) {
        TimeOfBirth timeOfBirth = new TimeOfBirth();
        timeOfBirth.setHour(hour);
        timeOfBirth.setMinute(minute);
        timeOfBirth.setSecond(seconds);
        timeOfBirth.setTimeZone(timeZone);
        timeOfBirth.set24HourFormat(true);
        return timeOfBirth;
    }

    private DateOfBirth getDateOfBirth(int year, int month, int day) {
        DateOfBirth dateOfBirth = new DateOfBirth();
        dateOfBirth.setYear(year);
        dateOfBirth.setMonth(month);
        dateOfBirth.setDate(day);
        return dateOfBirth;
    }

    private PersonalData getPersonalData(String personName, PersonalData.Gender personGender) {
        PersonalData personalData = new PersonalData();
        personalData.setId(personName);
        personalData.setName(personName);
        personalData.setRemarks(personName);
        personalData.setGender(personGender);
        return personalData;
    }
}
