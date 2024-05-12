package com.jyotish.api.core.util;

import com.jyotish.api.core.ComputeHoroscope;
import com.jyotish.api.core.config.TestBirthDataConfigs;
import com.jyotish.api.core.models.calc.ObjectUtils;
import com.jyotish.api.core.models.entity.House;
import com.jyotish.api.core.models.entity.Planet;
import com.jyotish.api.core.models.input.FlatPlanetData;

import java.util.*;

public class MatchMakingRules {

    public static void main(String[] args) {
        ComputeHoroscope computeHoroscope = new ComputeHoroscope();
        TestBirthDataConfigs testBirthDataConfigs = new TestBirthDataConfigs();
        FlatPlanetData maleFlatPlanetData = new FlatPlanetData(computeHoroscope.getPlanetData(testBirthDataConfigs.testBirthData1()));
        FlatPlanetData femaleFlatPlanetData = new FlatPlanetData(computeHoroscope.getPlanetData(testBirthDataConfigs.testBirthData2()));
        new MatchMakingRules().start(
                maleFlatPlanetData,
                femaleFlatPlanetData
        );
    }

    public void start(
            final FlatPlanetData malePlanetData,
            final FlatPlanetData femalePlanetData
    ) {
        ComputeHoroscope computeHoroscope = new ComputeHoroscope();
        System.out.println(matchRule1(malePlanetData, femalePlanetData));
        System.out.println(matchRule2(
                malePlanetData, new FlatPlanetData(computeHoroscope.convertToNavamshaPlanetData(malePlanetData.planetData)),
                femalePlanetData, new FlatPlanetData(computeHoroscope.convertToNavamshaPlanetData(femalePlanetData.planetData))
        ));
    }

    /**
     * Relative Moon Sign Positions
     */
    private String matchRule1(
            final FlatPlanetData male,
            final FlatPlanetData female
    ) {
        String outcome = "";
        int maleMoonSign = male.moon.sign.signData.number;
        int femaleMoonSign = female.moon.sign.signData.number;
        int displacement = Math.abs(maleMoonSign - femaleMoonSign) + 1;
        switch (displacement) {
            case 1:
            case 7:
                outcome = "1-7 Moon Sign - Average Harmony at emotional and mental level";
                break;
            case 2:
            case 12:
                outcome = "2-12 Moon Sign - Good/Nearly Perfect Harmony at emotional and mental level";
                break;
            case 3:
            case 11:
                outcome = "3-11 Moon Sign - Difficult to balance relationship, efforts are required to obtain balance";
                break;
            case 4:
            case 10:
                outcome = "4-10 Moon Sign - Excellent emotional balance - Made to balance - Karmic Bondage";
                break;
            case 5:
            case 9:
                outcome = "5-9 Moon Sign - Excellent emotional balance - Made to balance - Karmic Bondage";
                break;
            case 6:
            case 8:
                outcome = "6-8 Moon Sign - Difficult to balance relationship, efforts are required to obtain balance";
                break;
        }
        return outcome;
    }

    /**
     * Darakaraka Connectivity
     */
    private String matchRule2(
            final FlatPlanetData maleD1,
            final FlatPlanetData maleD9,
            final FlatPlanetData femaleD1,
            final FlatPlanetData femaleD9
    ) {
        Planet maleDarakaraka = maleD1.darakaraka;
        Planet femaleDarakaraka = femaleD1.darakaraka;

        Map<String, Integer> maleEffectivityMap = new HashMap<>();
        for (Planet maleEffectivePlanet : getEffectivePlanets(maleD1, maleD9)) {
            if (maleEffectivityMap.containsKey(maleEffectivePlanet.name)) {
                maleEffectivityMap.put(maleEffectivePlanet.name, maleEffectivityMap.getOrDefault(maleEffectivePlanet.name, 0) + 1);
            } else {
                maleEffectivityMap.put(maleEffectivePlanet.name, 1);
            }
        }

        Map<String, Integer> femaleEffectivityMap = new HashMap<>();
        for (Planet femaleEffectivePlanet : getEffectivePlanets(femaleD1, femaleD9)) {
            if (femaleEffectivityMap.containsKey(femaleEffectivePlanet.name)) {
                femaleEffectivityMap.put(femaleEffectivePlanet.name, femaleEffectivityMap.getOrDefault(femaleEffectivePlanet.name, 0) + 1);
            } else {
                femaleEffectivityMap.put(femaleEffectivePlanet.name, 1);
            }
        }

        return String.join("\n",
                String.format("Male DK: %s, effectivity in Female: %s", maleDarakaraka.name, femaleEffectivityMap.getOrDefault(maleDarakaraka.name, 0)),
                String.format("Female DK: %s, effectivity in Male: %s", femaleDarakaraka.name, maleEffectivityMap.getOrDefault(femaleDarakaraka.name, 0))
        );
    }

    private List<Planet> getEffectivePlanets(
            final FlatPlanetData d1,
            final FlatPlanetData d9
    ) {
        List<Planet> planetsAffectingD1Lagna = ObjectUtils.joinList(
                Arrays.asList(d1.lagna.sign.ruler, d1.lagna.nakshatra.ruler),
                d1.house1.planets,
                d1.house5.planets,
                d1.house9.planets,
                getPlanetsAspectingHouse(d1, d1.house1)
        );
        planetsAffectingD1Lagna.remove(d1.lagna);

        List<Planet> planetsAffectingD9Lagna = ObjectUtils.joinList(
                Collections.singletonList(d9.house1.sign.ruler),
                d9.house1.planets,
                d9.house5.planets,
                d9.house9.planets,
                getPlanetsAspectingHouse(d9, d9.house1)
        );
        planetsAffectingD9Lagna.remove(d9.lagna);

        List<Planet> atmakarakaRelations = ObjectUtils.joinList(
                Collections.singletonList(d1.atmakaraka),
                Collections.singletonList(d1.atmakaraka.sign.ruler),
                d1.atmakaraka.house.planets
        );

        List<Planet> planets = new ArrayList<>();
        planets.addAll(planetsAffectingD1Lagna);
        planets.addAll(planetsAffectingD9Lagna);
        planets.addAll(atmakarakaRelations);
        return planets;
    }

    private List<Planet> getPlanetsAspectingHouse(final FlatPlanetData planetData, final House house) {
        List<Planet> planets = new ArrayList<>();
        for (Planet planet : new Planet[]{
                planetData.sun, planetData.moon, planetData.mars,
                planetData.mercury, planetData.jupiter, planetData.venus,
                planetData.saturn, planetData.rahu, planetData.ketu
        }) {
            if (planet.isAspectingByPlanetDrishti(house)) {
                planets.add(planet);
            }
        }
        return planets;
    }

}
