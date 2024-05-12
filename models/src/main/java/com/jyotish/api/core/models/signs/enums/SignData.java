package com.jyotish.api.core.models.signs.enums;

import lombok.Getter;

import static com.jyotish.api.core.models.VedicProperties.*;

@Getter
public enum SignData {

    SIGN_ARIES(1, ARIES, MARS, SignNature.MOVABLE, SignElement.FIRE),
    SIGN_TAURUS(2, TAURUS, VENUS, SignNature.FIXED, SignElement.EARTH),
    SIGN_GEMINI(3, GEMINI, MERCURY, SignNature.DUAL, SignElement.AIR),
    SIGN_CANCER(4, CANCER, MOON, SignNature.MOVABLE, SignElement.WATER),
    SIGN_LEO(5, LEO, SUN, SignNature.FIXED, SignElement.FIRE),
    SIGN_VIRGO(6, VIRGO, MERCURY, SignNature.DUAL, SignElement.EARTH),
    SIGN_LIBRA(7, LIBRA, VENUS, SignNature.MOVABLE, SignElement.AIR),
    SIGN_SCORPIO(8, SCORPIO, MARS, SignNature.FIXED, SignElement.WATER),
    SIGN_SAGITTARIUS(9, SAGITTARIUS, JUPITER, SignNature.DUAL, SignElement.FIRE),
    SIGN_CAPRICORN(10, CAPRICORN, SATURN, SignNature.MOVABLE, SignElement.EARTH),
    SIGN_AQUARIUS(11, AQUARIUS, SATURN, SignNature.FIXED, SignElement.AIR),
    SIGN_PISCES(12, PISCES, JUPITER, SignNature.DUAL, SignElement.WATER);

    public final int number;
    public final String signName;
    public final String signRuler;
    public final SignNature signNature;
    public final SignElement signElement;

    SignData(
            final int number,
            final String signName,
            final String signRuler,
            final SignNature signNature,
            final SignElement signElement
    ) {
        this.number = number;
        this.signName = signName;
        this.signRuler = signRuler;
        this.signNature = signNature;
        this.signElement = signElement;
    }

    /**
     * returns true if invoked sign is a fixed sign
     * @return true if invoked sign is a fixed sign
     */
    public boolean isFixed() {
        return SignNature.FIXED.name().trim().equalsIgnoreCase(signNature.name().trim());
    }

    /**
     * returns true if invoked sign is a movable sign
     * @return true if invoked sign is a movable sign
     */
    public boolean isMovable() {
        return SignNature.MOVABLE.name().trim().equalsIgnoreCase(signNature.name().trim());
    }

    /**
     * returns true if invoked sign is a dual sign
     * @return true if invoked sign is a dual sign
     */
    public boolean isDual() {
        return SignNature.DUAL.name().trim().equalsIgnoreCase(signNature.name().trim());
    }

    /**
     * returns static sign data by sign number [1-12]
     * @param number sign number from 1 to 12
     * @return static sign data
     */
    public static SignData getSignDataByNumber(final int number) {
        for (SignData signData : values()) {
            if (signData.getNumber() == number) {
                return signData;
            }
        }
        return null;
    }
}
