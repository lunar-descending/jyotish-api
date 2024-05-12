package com.jyotish.api.core.models.nakshtras.enums;

import lombok.Getter;

import static com.jyotish.api.core.models.VedicProperties.*;

@Getter
public enum NakshatraData {

    NAKSHATRA_ASHWINI(1, ASHWINI, KETU),
    NAKSHATRA_BHARANI(2, BHARANI, VENUS),
    NAKSHATRA_KRITTIKA(3, KRITTIKA, SUN),
    NAKSHATRA_ROHINI(4, ROHINI, MOON),
    NAKSHATRA_MRIGASHIRA(5, MRIGASHIRA, MARS),
    NAKSHATRA_ARDRA(6, ARDRA, RAHU),
    NAKSHATRA_PUNARVASU(7, PUNARVASU, JUPITER),
    NAKSHATRA_PUSHYA(8, PUSHYA, SATURN),
    NAKSHATRA_ASLESHA(9, ASLESHA, MERCURY),

    NAKSHATRA_MAGHA(10, MAGHA, KETU),
    NAKSHATRA_PURVA_PHALGUNI(11, PURVA_PHALGUNI, VENUS),
    NAKSHATRA_UTTARA_PHALGUNI(12, UTTARA_PHALGUNI, SUN),
    NAKSHATRA_HASTA(13, HASTA, MOON),
    NAKSHATRA_CHITRA(14, CHITRA, MARS),
    NAKSHATRA_SWATI(15, SWATI, RAHU),
    NAKSHATRA_VISHAKHA(16, VISHAKHA, JUPITER),
    NAKSHATRA_ANURADHA(17, ANURADHA, SATURN),
    NAKSHATRA_JYESHTA(18, JYESHTA, MERCURY),

    NAKSHATRA_MOOLA(19, MOOLA, KETU),
    NAKSHATRA_PURVASHADHA(20, PURVASHADHA, VENUS),
    NAKSHATRA_UTTARASHADHA(21, UTTARASHADHA, SUN),
    NAKSHATRA_SHRAVANA(22, SHRAVANA, MOON),
    NAKSHATRA_DHANISHTA(23, DHANISHTA, MARS),
    NAKSHATRA_SATABHISHA(24, SATABHISHA, RAHU),
    NAKSHATRA_PURVA_BHADRA(25, PURVA_BHADRA, JUPITER),
    NAKSHATRA_UTTARA_BHADRA(26, UTTARA_BHADRA, SATURN),
    NAKSHATRA_REVATI(27, REVATI, MERCURY);

    public final int number;
    public final String nakshatraName;
    public final String nakshatraRuler;

    NakshatraData(
            final int number,
            final String nakshatraName,
            final String nakshatraRuler
    ) {
        this.number = number;
        this.nakshatraName = nakshatraName;
        this.nakshatraRuler = nakshatraRuler;
    }

    /**
     * returns static nakshatra data by nakshatra number [1-27]
     *
     * @param number nakshatra number from 1 to 12
     * @return static nakshatra data
     */
    public static NakshatraData getNakshatraDataByNumber(final int number) {
        for (NakshatraData nakshatraData : values()) {
            if (nakshatraData.getNumber() == number) {
                return nakshatraData;
            }
        }
        return null;
    }
}
