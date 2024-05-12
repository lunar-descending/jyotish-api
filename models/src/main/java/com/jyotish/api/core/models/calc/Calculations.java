package com.jyotish.api.core.models.calc;

import com.jyotish.api.core.models.entity.Longitude;
import com.jyotish.api.core.models.entity.Planet;

import static com.jyotish.api.core.models.VedicProperties.*;

public class Calculations {

    /**
     * returns house distance between passed longitudes from 1-12
     * house distance from referenceLongitude to planetLongitude
     *
     * @param referenceLongitude longitude of planet from where houses has to be counted
     * @param planetLongitude    longitude of planet upto where houses has to be counted
     * @return house distance between passed longitudes from 1-12
     */
    public static int getHouseNumberByLongitude(Longitude referenceLongitude, Longitude planetLongitude) {
        int fromSign = getSignNumberByLongitude(referenceLongitude);
        int toSign = getSignNumberByLongitude(planetLongitude);
        return HOUSE_RULERS_INDEX[fromSign - 1][toSign - 1];
    }

    /**
     * returns sign number by longitude from 1-12 based on 360° zodiac
     * each sign is considered to be a span of 30°
     * Example: 71° = 30°(1) + 30°(1) + 11°(1) = signnumber: 3
     *
     * @param longitude longitude whose zodiac sign number has to be calculated
     * @return sign number by longitude from 1-12 based on 360° zodiac
     */
    public static int getSignNumberByLongitude(Longitude longitude) {
        int degrees = longitude.getDegree();
        int sign = (int) Math.ceil(degrees / 30d);
        if (degrees % 30 == 0) sign++;
        return sign;
    }

    /**
     * returns nakshatra number by longitude from 1-27 based on 360° zodiac
     * each nakshatra is considered to be a span of 13°20"
     * Example: 51°19" = 13°20"(1) + 13°20"(1) + 13°20"(1) + 11°19"(1) = nakshatranumber: 4
     *
     * @param longitude longitude whose zodiac nakshatra number has to be calculated
     * @return nakshatra number by longitude from 1-27 based on 360° zodiac
     */
    public static int getNakshatraNumberByLongitude(Longitude longitude) {
        long a = longitude.getNormaliseValue();
        long b = Longitude.NAKSHTRA_SPAN.getNormaliseValue();
        int nakshtraNumber = (int) Math.ceil((double) a / b);
        if (a % b == 0) nakshtraNumber++;
        return nakshtraNumber;
    }

    /**
     * returns pada number by longitude from 1-4 based on 360° zodiac
     * each nakshatra is considered to be a span of 3°20"
     * Example: 9°19" = 3°20"(1) + 3°20"(1) + 2°39"(1) = padanumber: 3
     *
     * @param longitude longitude whose zodiac pada number has to be calculated
     * @return pada number by longitude from 1-4 based on 360° zodiac
     */
    public static int getPlanetPadaNumber(Longitude longitude) {
        long a = longitude.getNormaliseValue();
        long b = Longitude.NAKSHTRA_SPAN.getNormaliseValue();
        long c = Longitude.PADA_SPAN.getNormaliseValue();
        return (int) (((a % b) / c) + 1);
    }

    /**
     * returns sign number of given houseNumber counted from lagnaSign
     *
     * @param lagnaSign   starting sign from where counting start
     * @param houseNumber number of houses to be counted
     * @return sign number of given houseNumber counted from lagnaSign
     */
    public static int getSignNumberOfHouseByLagnaSign(final int lagnaSign, final int houseNumber) {
        return HOUSE_REFERENCE_INDEX[lagnaSign - 1][houseNumber - 1];
    }

    /**
     * return house distance from fromHouse to toHouse
     *
     * @param fromHouse house number to start counting
     * @param toHouse   house number to stop counting
     * @return house distance from fromHouse to toHouse
     */
    public static int getHouseDistance(int fromHouse, int toHouse) {
        int startHouse = 1;
        while (fromHouse != toHouse) {
            fromHouse++;
            if (fromHouse >= 13) fromHouse = 1;
            startHouse++;
        }
        return startHouse;
    }

    /**
     * returns arudha pada of referenced house, houses counted from lagna
     * Example: If referenceHouse=2 and lord of 2nd house in 4th house, 6 is returned as pada number from lagna
     *
     * @param referenceHouse house whose pada is to calculated
     * @param lordPlanet     lord of above house
     * @return arudha pada of referenced house, houses counted from lagna
     */
    public static int getHousePada(final int referenceHouse, final Planet lordPlanet) {
        int planetHouse = lordPlanet.house.number;
        int houseDistance = getHouseDistance(referenceHouse, planetHouse);
        if (houseDistance == 1 || houseDistance == 7) {
            int effectiveHouseNumber = (referenceHouse + 10 - 1) % 12;
            effectiveHouseNumber = effectiveHouseNumber == 0 ? 12 : effectiveHouseNumber;
            return effectiveHouseNumber;
        } else if (houseDistance == 4 || houseDistance == 10) {
            int effectiveHouseNumber = (referenceHouse + 4 - 1) % 12;
            effectiveHouseNumber = effectiveHouseNumber == 0 ? 12 : effectiveHouseNumber;
            return effectiveHouseNumber;
        } else {
            int effectiveHouseNumber = (planetHouse + houseDistance - 1) % 12;
            effectiveHouseNumber = effectiveHouseNumber == 0 ? 12 : effectiveHouseNumber;
            return effectiveHouseNumber;
        }
    }

}
