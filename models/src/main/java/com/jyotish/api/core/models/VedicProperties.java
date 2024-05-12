package com.jyotish.api.core.models;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public abstract class VedicProperties {

    private VedicProperties() {
        throw new RuntimeException("Cannot instantiate properties class");
    }

    public static final String EMPTY = "";

    public static final String LAGNA = "Lagna";
    public static final String SUN = "Sun";
    public static final String MOON = "Moon";
    public static final String MARS = "Mars";
    public static final String MERCURY = "Mercury";
    public static final String JUPITER = "Jupiter";
    public static final String VENUS = "Venus";
    public static final String SATURN = "Saturn";
    public static final String RAHU = "Rahu";
    public static final String KETU = "Ketu";

    public static final String ARIES = "Aries";
    public static final String TAURUS = "Taurus";
    public static final String GEMINI = "Gemini";
    public static final String CANCER = "Cancer";
    public static final String LEO = "Leo";
    public static final String VIRGO = "Virgo";
    public static final String LIBRA = "Libra";
    public static final String SCORPIO = "Scorpio";
    public static final String SAGITTARIUS = "Sagittarius";
    public static final String CAPRICORN = "Capricorn";
    public static final String AQUARIUS = "Aquarius";
    public static final String PISCES = "Pisces";

    public static final String ASHWINI = "Ashwini";
    public static final String BHARANI = "Bharani";
    public static final String KRITTIKA = "Krittika";
    public static final String ROHINI = "Rohini";
    public static final String MRIGASHIRA = "Mrigashira";
    public static final String ARDRA = "Ardra";
    public static final String PUNARVASU = "Punarvasu";
    public static final String PUSHYA = "Pushya";
    public static final String ASLESHA = "Aslesha";
    public static final String MAGHA = "Magha";
    public static final String PURVA_PHALGUNI = "Purva Phalguni";
    public static final String UTTARA_PHALGUNI = "Uttara Phalguni";
    public static final String HASTA = "Hasta";
    public static final String CHITRA = "Chitra";
    public static final String SWATI = "Swati";
    public static final String VISHAKHA = "Vishakha";
    public static final String ANURADHA = "Anuradha";
    public static final String JYESHTA = "Jyeshta";
    public static final String MOOLA = "Moola";
    public static final String PURVASHADHA = "Purvashadha";
    public static final String UTTARASHADHA = "Uttarashadha";
    public static final String SHRAVANA = "Shravana";
    public static final String DHANISHTA = "Dhanishta";
    public static final String SATABHISHA = "Satabhisha";
    public static final String PURVA_BHADRA = "Purva Bhadra";
    public static final String UTTARA_BHADRA = "Uttara Bhadra";
    public static final String REVATI = "Revati";

    public static final String EXALTATION = "Exaltation";
    public static final String DEBILITATION = "Debilitation";
    public static final String OWN = "Own";
    public static final String MOOLATRIKONA = "Moolatrikona";

    public static final int[][] HOUSE_RULERS_INDEX = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12},
            {12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
            {11, 12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            {10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8, 9},
            {9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8},
            {8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7},
            {7, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6},
            {6, 7, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5},
            {5, 6, 7, 8, 9, 10, 11, 12, 1, 2, 3, 4},
            {4, 5, 6, 7, 8, 9, 10, 11, 12, 1, 2, 3},
            {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1, 2},
            {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1}
    };

    public static final int[][] HOUSE_REFERENCE_INDEX = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12},
            {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1},
            {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1, 2},
            {4, 5, 6, 7, 8, 9, 10, 11, 12, 1, 2, 3},
            {5, 6, 7, 8, 9, 10, 11, 12, 1, 2, 3, 4},
            {6, 7, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5},
            {7, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6},
            {8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7},
            {9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8},
            {10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8, 9},
            {11, 12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            {12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}
    };

    public static final String[] NATURAL_MALEFICS = {SATURN, MARS, RAHU, KETU};
    public static final String[] NATURAL_BENEFICS = {JUPITER, VENUS, MERCURY, MOON};

    public static final String[] JAIMINI_CHARKARAKAS = {SUN, MOON, MARS, JUPITER, MERCURY, VENUS, SATURN, RAHU};
    public static final boolean JAIMINI_CONSIDER_RAHU_KETU_DEGREE_REVERSE = true;

    public static final List<Integer> KENDRA_HOUSES = Arrays.asList(1, 4, 7, 10);
    public static final List<Integer> TRIKONA_HOUSES = Arrays.asList(1, 5, 9);
    public static final List<Integer> DUSTHANA_HOUSES = Arrays.asList(6, 8, 12);
    public static final List<Integer> UPACHAYA_HOUSES = Arrays.asList(3, 6, 10, 11);

    public static String getActualString(final String identifier) {
        if (identifier == null) {
            return null;
        }
        try {
            for (Field field : VedicProperties.class.getDeclaredFields()) {
                if (field != null && field.getDeclaringClass().getName().equalsIgnoreCase("String")) {
                    if (field.getName().equalsIgnoreCase(identifier)) {
                        return field.get(VedicProperties.class).toString();
                    }
                }
            }
        } catch (final Exception ignored) {
        }
        return identifier;
    }

}
