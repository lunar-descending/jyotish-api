package com.jyotish.api.core.models.input;

import com.jyotish.api.core.models.entity.House;
import com.jyotish.api.core.models.entity.Longitude;
import com.jyotish.api.core.models.entity.Planet;
import com.jyotish.api.core.models.signs.Sign;

import java.util.*;

import static com.jyotish.api.core.models.calc.Calculations.*;
import static com.jyotish.api.core.models.VedicProperties.*;

public class FlatPlanetData {

    public PlanetData planetData;

    public Planet lagna;
    public Planet sun;
    public Planet moon;
    public Planet mars;
    public Planet venus;
    public Planet jupiter;
    public Planet mercury;
    public Planet saturn;
    public Planet rahu;
    public Planet ketu;

    public House house1;
    public House house2;
    public House house3;
    public House house4;
    public House house5;
    public House house6;
    public House house7;
    public House house8;
    public House house9;
    public House house10;
    public House house11;
    public House house12;

    public Planet atmakaraka;
    public Planet amatyakaraka;
    public Planet bhratrukaraka;
    public Planet matrukaraka;
    public Planet putrakaraka;
    public Planet pitrikaraka;
    public Planet gnatikaraka;
    public Planet darakaraka;

    public House arudha1;
    public House arudha2;
    public House arudha3;
    public House arudha4;
    public House arudha5;
    public House arudha6;
    public House arudha7;
    public House arudha8;
    public House arudha9;
    public House arudha10;
    public House arudha11;
    public House arudha12;

    public FlatPlanetData(PlanetData planetData) {
        this.planetData = planetData;

        this.lagna = new Planet(LAGNA, planetData);
        this.sun = new Planet(SUN, planetData);
        this.moon = new Planet(MOON, planetData);
        this.mars = new Planet(MARS, planetData);
        this.venus = new Planet(VENUS, planetData);
        this.jupiter = new Planet(JUPITER, planetData);
        this.mercury = new Planet(MERCURY, planetData);
        this.saturn = new Planet(SATURN, planetData);
        this.rahu = new Planet(RAHU, planetData);
        this.ketu = new Planet(KETU, planetData);

        house1 = new House();
        house2 = new House();
        house3 = new House();
        house4 = new House();
        house5 = new House();
        house6 = new House();
        house7 = new House();
        house8 = new House();
        house9 = new House();
        house10 = new House();
        house11 = new House();
        house12 = new House();

        List<Planet> planets = Arrays.asList(
                this.lagna,
                this.sun, this.moon, this.mars,
                this.venus, this.jupiter, this.mercury,
                this.saturn, this.rahu, this.ketu
        );

        List<House> houses = Arrays.asList(
                house1, house2, house3, house4,
                house5, house6, house7, house8,
                house9, house10, house11, house12
        );

        Map<Integer, List<Planet>> planetHouseNumberMap = preparePlanetHouseMap(planets);

        //OBJECT COMPOSITION
        for (Planet planet : planets) {
            String signRulerNameString = planet.getSign().getSignData().getSignRuler();
            Planet signRulerObject = getPlanetByName(signRulerNameString);
            planet.getSign().setRuler(signRulerObject);

            String nakshatraRulerNameString = planet.getNakshatra().getNakshatraData().getNakshatraRuler();
            Planet nakshatraRulerObject = getPlanetByName(nakshatraRulerNameString);
            planet.getNakshatra().setRuler(nakshatraRulerObject);

            planet.getHouse().setPlanets(new ArrayList<>(planetHouseNumberMap.get(planet.getHouse().getNumber())));
        }


        for (int houseNumber = 1; houseNumber <= houses.size(); houseNumber++) {
            int lagnaSign = lagna.getSign().getSignData().getNumber();
            int houseSignNumber = getSignNumberOfHouseByLagnaSign(lagnaSign, houseNumber);

            Sign houseSign = new Sign(houseSignNumber);
            String houseSignRulerNameString = houseSign.signData.signRuler;
            Planet houseSignRulerObject = getPlanetByName(houseSignRulerNameString);
            houseSign.setRuler(houseSignRulerObject);

            List<Planet> planetsInHouse = planetHouseNumberMap.get(houseNumber);

            House house = houses.get(houseNumber - 1);
            house.setNumber(houseNumber);
            house.setSign(houseSign);
            house.setPlanets(planetsInHouse);
        }

        for (Planet planet : planets) {
            planet.house2 = getHouseByNumber(planet.house.number + 1);
            planet.house3 = getHouseByNumber(planet.house.number + 2);
            planet.house4 = getHouseByNumber(planet.house.number + 3);
            planet.house5 = getHouseByNumber(planet.house.number + 4);
            planet.house6 = getHouseByNumber(planet.house.number + 5);
            planet.house7 = getHouseByNumber(planet.house.number + 6);
            planet.house8 = getHouseByNumber(planet.house.number + 7);
            planet.house9 = getHouseByNumber(planet.house.number + 8);
            planet.house10 = getHouseByNumber(planet.house.number + 9);
            planet.house11 = getHouseByNumber(planet.house.number + 10);
            planet.house12 = getHouseByNumber(planet.house.number + 11);
        }

        for (House house : houses) {
            house.house1 = house;
            house.house2 = getHouseByNumber(house.number + 1);
            house.house3 = getHouseByNumber(house.number + 2);
            house.house4 = getHouseByNumber(house.number + 3);
            house.house5 = getHouseByNumber(house.number + 4);
            house.house6 = getHouseByNumber(house.number + 5);
            house.house7 = getHouseByNumber(house.number + 6);
            house.house8 = getHouseByNumber(house.number + 7);
            house.house9 = getHouseByNumber(house.number + 8);
            house.house10 = getHouseByNumber(house.number + 9);
            house.house11 = getHouseByNumber(house.number + 10);
            house.house12 = getHouseByNumber(house.number + 11);
        }

        List<String> descSortedLongitudesPlanetsList = descSortedLongitudesPlanetsList(JAIMINI_CHARKARAKAS);

        if (descSortedLongitudesPlanetsList.size() != JAIMINI_CHARKARAKAS.length) {
            throw new RuntimeException("Jaimini Karakas not initialized appropriately");
        }

        this.atmakaraka = getPlanetByName(descSortedLongitudesPlanetsList.get(0));
        this.amatyakaraka = getPlanetByName(descSortedLongitudesPlanetsList.get(1));
        this.bhratrukaraka = getPlanetByName(descSortedLongitudesPlanetsList.get(2));
        this.matrukaraka = getPlanetByName(descSortedLongitudesPlanetsList.get(3));
        this.putrakaraka = getPlanetByName(descSortedLongitudesPlanetsList.get(4));
        this.pitrikaraka = getPlanetByName(descSortedLongitudesPlanetsList.get(5));
        this.gnatikaraka = getPlanetByName(descSortedLongitudesPlanetsList.get(6));
        this.darakaraka = getPlanetByName(descSortedLongitudesPlanetsList.get(7));

        arudha1 = getHouseByNumber(getHousePada(1, house1.sign.ruler));
        arudha2 = getHouseByNumber(getHousePada(2, house2.sign.ruler));
        arudha3 = getHouseByNumber(getHousePada(3, house3.sign.ruler));
        arudha4 = getHouseByNumber(getHousePada(4, house4.sign.ruler));
        arudha5 = getHouseByNumber(getHousePada(5, house5.sign.ruler));
        arudha6 = getHouseByNumber(getHousePada(6, house6.sign.ruler));
        arudha7 = getHouseByNumber(getHousePada(7, house7.sign.ruler));
        arudha8 = getHouseByNumber(getHousePada(8, house8.sign.ruler));
        arudha9 = getHouseByNumber(getHousePada(9, house9.sign.ruler));
        arudha10 = getHouseByNumber(getHousePada(10, house10.sign.ruler));
        arudha11 = getHouseByNumber(getHousePada(11, house11.sign.ruler));
        arudha12 = getHouseByNumber(getHousePada(12, house12.sign.ruler));
    }

    private Map<Integer, List<Planet>> preparePlanetHouseMap(List<Planet> planets) {
        Map<Integer, List<Planet>> planetHousMap = new HashMap<>();
        for (int i = 1; i <= 12; i++) {
            planetHousMap.put(i, new ArrayList<>());
        }
        for (Planet planet : planets) {
            int planetHouseNumber = planet.getHouse().getNumber();
            List<Planet> planetsInHouse = planetHousMap.get(planetHouseNumber);
            planetsInHouse.add(planet);
        }
        return planetHousMap;
    }

    public House getHouseByNumber(final int houseNumber) {
        if (houseNumber <= 0) {
            return null;
        }
        if (houseNumber >= 13) {
            return getHouseByNumber(houseNumber - 12);
        }
        return new House[]{null,
                house1, house2, house3, house4, house5, house6,
                house7, house8, house9, house10, house11, house12
        }[houseNumber];
    }

    public Planet getPlanetByName(final String planetName) {
        switch (getActualString(planetName)) {
            case LAGNA:
                return lagna;
            case SUN:
                return sun;
            case MOON:
                return moon;
            case MARS:
                return mars;
            case JUPITER:
                return jupiter;
            case VENUS:
                return venus;
            case MERCURY:
                return mercury;
            case SATURN:
                return saturn;
            case RAHU:
                return rahu;
            case KETU:
                return ketu;
        }
        return null;
    }

    public House getHouseBySign(final int signNumber) {
        for (House house : new House[]{
                house1, house2, house3, house4,
                house5, house6, house7, house8,
                house9, house10, house11, house12
        }) {
            if (house.sign.signData.number == signNumber) {
                return house;
            }
        }
        return null;
    }

    public House getHouseBySign(final Sign sign) {
        return getHouseBySign(sign.signData.number);
    }

    public List<String> descSortedLongitudesPlanetsList(final String... planetsToConsider) {
        if (planetsToConsider == null) {
            return Collections.emptyList();
        }

        //CONSIDER LONGITUDES
        Map<String, Longitude> longitudeMap = new HashMap<>();
        Map<String, Longitude> longitudeMapActual = planetData.getPlanetLongitudeMap();
        for (String planetToConsider : planetsToConsider) {
            if (planetToConsider != null && !planetToConsider.trim().isEmpty() && longitudeMapActual.containsKey(planetToConsider.trim())) {
                longitudeMap.put(planetToConsider.trim(), longitudeMapActual.get(planetToConsider.trim()));
            }
        }

        //REVERSE FOR RAHU KETU
        if (longitudeMap.containsKey(RAHU) && JAIMINI_CONSIDER_RAHU_KETU_DEGREE_REVERSE) {
            Longitude rahuLongitude = longitudeMap.get(RAHU);
            Longitude newRahuLongitude = Longitude.reverseNormalise(Longitude.DEGREE_30.getNormaliseValue() - rahuLongitude.getNormaliseValue());
            longitudeMap.put(RAHU, newRahuLongitude);
        }

        if (longitudeMap.containsKey(KETU) && JAIMINI_CONSIDER_RAHU_KETU_DEGREE_REVERSE) {
            Longitude ketuLongitude = longitudeMap.get(KETU);
            Longitude ketuRahuLongitude = Longitude.reverseNormalise(Longitude.DEGREE_30.getNormaliseValue() - ketuLongitude.getNormaliseValue());
            longitudeMap.put(KETU, ketuRahuLongitude);
        }

        //SORT MAP
        List<Map.Entry<String, Longitude>> mapListByLongitude = new ArrayList<>(longitudeMap.entrySet());
        mapListByLongitude.sort(Comparator.comparingInt(o -> o.getValue().intValue()));
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Longitude> entry : mapListByLongitude) {
            list.add(entry.getKey());
        }

        return list;
    }

}
