package com.jyotish.api.core;

import com.jyotish.api.core.models.entity.Longitude;
import com.jyotish.api.core.models.input.BirthData;
import com.jyotish.api.core.models.input.PlanetData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissEph;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.jyotish.api.core.models.VedicProperties.*;

@Component
public class ComputeHoroscope {

    private static final Logger logger = LoggerFactory.getLogger(ComputeHoroscope.class);

    private static String EPHE_DATA_FILES = null;

    public static final int[] planetIndex = {
            SweConst.SE_SUN, SweConst.SE_MOON, SweConst.SE_MARS,
            SweConst.SE_MERCURY, SweConst.SE_VENUS, SweConst.SE_SATURN,
            SweConst.SE_JUPITER, SweConst.SE_MEAN_NODE, SweConst.SE_MEAN_NODE
    };

    public static final String[] planetName = {
            SUN, MOON, MARS,
            MERCURY, VENUS, SATURN,
            JUPITER, RAHU, KETU
    };

    static {
        try {
            URL resource = ComputeHoroscope.class.getClassLoader().getResource("ephe-data");
            if (resource != null) {
                URI uri = resource.toURI();
                EPHE_DATA_FILES = uri.getPath();
            }
        } catch (final Exception exception) {
            logger.error("Error while initialising EPHE_DATA_FILES: {}", exception.getMessage());
        }
    }

    public PlanetData getPlanetData(BirthData birthData) {
        SweDate sweDate = getSweDate(birthData);
        SwissEph swissEph = getSwissEph();
        Longitude lagnaLongitude = computeLagna(swissEph, sweDate, birthData);
        Map<String, Longitude> planetLongitudeMap = getPlanetLongitudeMap(swissEph, sweDate);
        planetLongitudeMap.put(LAGNA, lagnaLongitude);
        return new PlanetData(birthData, lagnaLongitude, planetLongitudeMap);
    }

    public PlanetData convertToNavamshaPlanetData(PlanetData planetData) {
        Map<String, Longitude> navamshaPlanetLongitudeMap = new HashMap<>();
        for(String planetName: planetData.getPlanetLongitudeMap().keySet()) {
            Longitude navamshaLongitude = convertToNavamshaLongitude(planetData.getPlanetLongitudeMap().get(planetName));
            navamshaPlanetLongitudeMap.put(planetName, navamshaLongitude);
        }
        Longitude navamshaLongitude = navamshaPlanetLongitudeMap.getOrDefault(LAGNA, new Longitude());
        return new PlanetData(planetData.getBirthData(), navamshaLongitude, navamshaPlanetLongitudeMap);
    }

    public static Longitude convertToNavamshaLongitude(final Longitude longitude) {
        Longitude divisionFactor = Longitude.PADA_SPAN;
        long divisor = longitude.getNormaliseValue() / divisionFactor.getNormaliseValue();
        long remainder = longitude.getNormaliseValue() % divisionFactor.getNormaliseValue();
        long houseDegreeNormalised = (long) (Longitude.DEGREE_30.getNormaliseValue() * remainder * 1.0d / divisionFactor.getNormaliseValue());
        long navamshaSign = divisor % 12;
        long normalisedValue = Longitude.DEGREE_30.getNormaliseValue() * navamshaSign + houseDegreeNormalised;
        Longitude navamshaLongitude = Longitude.reverseNormalise(normalisedValue);
        navamshaLongitude.setRetro(longitude.isRetro());
        return navamshaLongitude;
    }

    private SweDate getSweDate(BirthData birthData) {
        int year = birthData.getDateOfBirth().getYear();
        int month = birthData.getDateOfBirth().getMonth();
        int day = birthData.getDateOfBirth().getDate();

        double hour = birthData.getTimeOfBirth().getHour() + birthData.getTimeOfBirth().getMinute() / 60.0 - birthData.getTimeOfBirth().getTimeZone();

        return new SweDate(year, month, day, hour);
    }

    private SwissEph getSwissEph() {
        SwissEph swissEph = new SwissEph(ComputeHoroscope.EPHE_DATA_FILES);
        swissEph.swe_set_sid_mode(SweConst.SE_SIDM_LAHIRI, 0, 0);
        return swissEph;
    }

    private Longitude computeLagna(SwissEph swissEph, SweDate sweDate, BirthData birthData) {
        double longitude = birthData.getPlaceOfBirth().getLongitude();
        double latitude = birthData.getPlaceOfBirth().getLatitude();
        double[] cusps = new double[13];
        double[] asc = new double[10];
        swissEph.swe_houses(sweDate.getJulDay(), SweConst.SEFLG_SIDEREAL, latitude, longitude, 'P', cusps, asc);
        return toLongitude(asc[0]);
    }


    private Map<String, Longitude> getPlanetLongitudeMap(SwissEph swissEph, SweDate sweDate) {
        Map<String, Longitude> planetLongitudeMap = new HashMap<>();
        for(int i = 0 ; i < planetIndex.length ; i++) {
            Longitude longitude = getLongitude(swissEph, sweDate, planetIndex[i]);
            planetLongitudeMap.put(planetName[i], longitude);
        }

        //REMEDIATE KETU LONGITUDE
        Longitude ketuLongitude = planetLongitudeMap.getOrDefault(planetName[planetName.length - 1], null);
        if(ketuLongitude != null) {
            Longitude ketuNewLongitude = ketuLongitude.add(Longitude.DEGREE_180, Longitude.DEGREE_360);
            planetLongitudeMap.put(planetName[planetName.length - 1], ketuNewLongitude);
        }

        return planetLongitudeMap;
    }

    private Longitude getLongitude(SwissEph swissEph, SweDate sweDate, int planetIndex) {
        double[] xp = new double[6];
        swissEph.swe_calc_ut(sweDate.getJulDay(), planetIndex, getCalculationsFlags(), xp, new StringBuffer());
        Longitude degree = toLongitude(xp[0]);
        degree.setRetro(xp[3] < 0);
        return degree;
    }

    private Longitude toLongitude(double arcAngle) {
        arcAngle += 0.5 / 3600.0 / 10000.0;
        int degree = (int) arcAngle;
        arcAngle = (arcAngle - degree) * 60;
        int min = (int) arcAngle;
        arcAngle = (arcAngle - min) * 60;
        int sec = (int) arcAngle;
        return new Longitude(degree, min, sec);
    }

    private int getCalculationsFlags() {
        return SweConst.SEFLG_SWIEPH |
                SweConst.SEFLG_SIDEREAL |
                SweConst.SEFLG_NONUT |
                SweConst.SEFLG_SPEED;
    }

}

