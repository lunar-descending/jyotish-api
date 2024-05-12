package com.jyotish.api.core.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Longitude extends Number implements Comparable<Longitude> {

    public static final Longitude NAKSHTRA_SPAN = new Longitude(13, 20, 0);
    public static final Longitude PADA_SPAN = new Longitude(3, 20, 0);
    public static final Longitude DEGREE_30 = new Longitude(30, 0, 0);
    public static final Longitude DEGREE_180 = new Longitude(180, 0, 0);
    public static final Longitude DEGREE_360 = new Longitude(360, 0, 0);

    private long normaliseValue = 0;
    private int degree = 0;
    private int minutes = 0;
    private int seconds = 0;
    private boolean isRetro = false;

    public Longitude(int degree, int minutes, int seconds) {
        this.degree = degree;
        this.minutes = minutes;
        this.seconds = seconds;
        normaliseValue = normaliseValue(this);
    }

    public Longitude add(Longitude toAdd, Longitude modFactor) {
        long addition = normaliseValue(this) + normaliseValue(toAdd);
        long modAddition = addition % modFactor.getNormaliseValue();
        return reverseNormalise(modAddition);
    }

    public Longitude toHouseLongitude() {
        long normaliseValue = this.getNormaliseValue();
        long remainderByHouse = normaliseValue % DEGREE_30.getNormaliseValue();
        return Longitude.reverseNormalise(remainderByHouse);
    }

    public static long normaliseValue(Longitude l) {
        return (long) l.degree * 3600 + (long) l.minutes * 60 + (long) l.seconds;
    }

    public static Longitude reverseNormalise(long l) {
        if (l == 0) return new Longitude(0, 0, 0);
        int degree = (int) l / 3600;
        int minutes = (int) ((l % 3600) / 60);
        int seconds = (int) ((l % 3600) % 60);
        return new Longitude(degree, minutes, seconds);
    }

    @Override
    public int intValue() {
        return (int) normaliseValue;
    }

    @Override
    public long longValue() {
        return normaliseValue;
    }

    @Override
    public float floatValue() {
        return normaliseValue;
    }

    @Override
    public double doubleValue() {
        return normaliseValue;
    }

    @Override
    public String toString() {
        return String.format("%d°%d'%d\"",
                degree, minutes, seconds
        );
    }

    @Override
    public int compareTo(Longitude other) {
        return (int) (other.getNormaliseValue() - this.getNormaliseValue());
    }
}
